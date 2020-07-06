package com.example.demo.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class EntidadeNaoEncontradaException extends NegocioExeption {
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String message) {
		super(message+ "Teste");
		
	}

}
