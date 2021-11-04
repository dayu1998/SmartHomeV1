package com.antra.smart_home_v1.exception;

public class MovieNotExistExeption extends RuntimeException {
	public MovieNotExistExeption() {
	}

	public MovieNotExistExeption(String message) {
		super(message);
	}
}