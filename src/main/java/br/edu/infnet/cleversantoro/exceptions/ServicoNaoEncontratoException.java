package br.edu.infnet.cleversantoro.exceptions;

public class ServicoNaoEncontratoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServicoNaoEncontratoException(String mensagem) {
		super(mensagem);
	}
}
