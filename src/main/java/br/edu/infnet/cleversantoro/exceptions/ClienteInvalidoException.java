package br.edu.infnet.cleversantoro.exceptions;

public class ClienteInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteInvalidoException(String mensagem) {
		super(mensagem);
	}
}