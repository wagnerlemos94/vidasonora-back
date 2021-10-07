package com.vidasonora.lemos.controller.service;

import java.util.NoSuchElementException;

public class ObjetoNaoDeletado extends NoSuchElementException{
	private static final long serialVersionUID = 1L;
	
	public ObjetoNaoDeletado(String msg) {
		super(msg);
	}

}
