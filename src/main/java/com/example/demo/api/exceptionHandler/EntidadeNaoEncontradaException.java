package com.example.demo.api.exceptionHandler;

public class EntidadeNaoEncontradaException extends NegocioExeption {
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
		
	}

}
