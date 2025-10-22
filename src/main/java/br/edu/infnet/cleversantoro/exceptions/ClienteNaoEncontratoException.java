package br.edu.infnet.cleversantoro.exceptions;

public class ClienteNaoEncontratoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontratoException(String mensagem) {
		super(mensagem);
	}
}