package com.maruszka.errorResponse;

public class CountryNotFoundException extends RuntimeException {

//	public CountryNotFoundException() {
//	}

	public CountryNotFoundException(String message) {
		super(message);
	}

	public CountryNotFoundException(Throwable cause) {
		super(cause);
	}

	public CountryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

//	public CountryNotFoundException(String message, Throwable cause, boolean enableSuppression , boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//	}
}
