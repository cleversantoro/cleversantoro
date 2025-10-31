package br.edu.infnet.cleversantoro.model.domain;

public enum TipoServico {
	BASICO("Básico"),
	ELETRICO("Eletrico"),
	PESADO("Pesado"),
	CUSTOMIZACAO("Customização"),
	OUTRO("Outro");

	private String descricao;

	TipoServico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}