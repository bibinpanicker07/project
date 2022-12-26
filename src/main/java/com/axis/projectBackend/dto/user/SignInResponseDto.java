package com.axis.projectBackend.dto.user;

public class SignInResponseDto {
    private String status;
    private String token;
    private String role;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	

	public SignInResponseDto(String status, String token, String role) {
		super();
		this.status = status;
		this.token = token;
		this.role = role;
	}

	public SignInResponseDto() {
		super();
	}
    
}
