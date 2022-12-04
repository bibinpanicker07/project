package com.axis.projectBackend.exceptions;

public class ProductNotExistsException extends IllegalArgumentException {
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = -775351994508662646L;

	public ProductNotExistsException(String msg) {
        super(msg);
    }
}
