package com.vidasonora.lemos.controller.service.exception;

import java.util.NoSuchElementException;

public class AutenticacaoException extends NoSuchElementException{
	private static final long serialVersionUID = 1L;

	public AutenticacaoException(String msg) {
		super(msg);
	}
	
	
}
