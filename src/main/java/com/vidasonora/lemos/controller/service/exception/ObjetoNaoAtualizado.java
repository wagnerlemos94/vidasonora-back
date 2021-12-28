package com.vidasonora.lemos.controller.service.exception;

import java.util.NoSuchElementException;

public class ObjetoNaoAtualizado extends NoSuchElementException{
	private static final long serialVersionUID = 1L;

	public ObjetoNaoAtualizado(String msg) {
		super(msg);
	}

}
