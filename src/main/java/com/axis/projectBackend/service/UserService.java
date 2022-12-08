package com.axis.projectBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.axis.projectBackend.dto.user.*;
import com.axis.projectBackend.entity.AuthenticationToken;
import com.axis.projectBackend.entity.OrderDetail;
import com.axis.projectBackend.entity.User;
import com.axis.projectBackend.exceptions.AuthenticationFailException;
import com.axis.projectBackend.exceptions.CustomException;
import com.axis.projectBackend.repository.OrderDetailRepository;
import com.axis.projectBackend.repository.UserRepository;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;
    
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Transactional
    public ResponseEntity<String> signUp(SignupDto signupDto) {
        // check if user is already present
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            // we have an user
            throw new CustomException("user already present");
        }


        // hash the password

        String encryptedpassword = signupDto.getPassword();

        try {
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User(signupDto.getFirstName(), signupDto.getLastName(),
                signupDto.getEmail(), encryptedpassword);

        userRepository.save(user);

        // save the user

        // create the token

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);

        authenticationService.saveConfirmationToken(authenticationToken);
        
        //create empty order for user
        OrderDetail orderDetail = new OrderDetail(user);
        orderDetailRepository.save(orderDetail);
        
        
        return  new ResponseEntity<String>("user created succesfully",HttpStatus.CREATED);
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        // find user by email

        User user = userRepository.findByEmail(signInDto.getEmail());

        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user is not valid");
        }

        // hash the password

        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // compare the password in DB

        // if password match

        AuthenticationToken token = authenticationService.getToken(user);

        // retrive the token

        if (Objects.isNull(token)) {
            throw new CustomException("token is not present");
        }

        return new SignInResponseDto("sucess", token.getToken());

        // return response
    }
}