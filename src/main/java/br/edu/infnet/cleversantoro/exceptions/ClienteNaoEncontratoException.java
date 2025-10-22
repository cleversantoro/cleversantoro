package br.edu.infnet.cleversantoro.exceptions;

public class MecanicoNaoEncontratoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MecanicoNaoEncontratoException(String mensagem) {
		super(mensagem);
	}
}