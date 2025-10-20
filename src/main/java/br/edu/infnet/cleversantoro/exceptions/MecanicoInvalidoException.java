package br.edu.infnet.cleversantoro.exceptions;

public class MecanicoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MecanicoInvalidoException(String mensagem) {
		super(mensagem);
	}
}