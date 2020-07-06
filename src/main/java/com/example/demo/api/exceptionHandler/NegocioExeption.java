package com.example.demo.api.exceptionHandler;

public class NegocioExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NegocioExeption(String message) {
		super(message );
	}
	

}
