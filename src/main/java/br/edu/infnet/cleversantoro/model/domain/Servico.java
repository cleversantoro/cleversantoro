package br.edu.infnet.cleversantoro.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "A descrição do produto é obrigatória.")
	private String descricao;

	@NotNull(message = "O valor do produto é obrigatório.")
	@DecimalMin(value = "0.01", message = "O valor do produto deve ser positivo.")
	private Double valor;

	@NotNull(message = "O tipo do produto é obrigatório.")
	@Enumerated(EnumType.STRING)
	private TipoServico tipoServico;
	
	@ManyToOne
	@JoinColumn(name = "Mecanico_id", nullable = false)
	private Mecanico Mecanico;
	
    @Override
    public String toString() {

        return String.format(
            "Produto [ID: %d | Descrição: %s | Valor: R$ %.2f | Tipo: %s]",
            id,
            descricao,
            valor,
            tipoServico != null ? tipoServico.getDescricao() : "N/A"
        );
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Mecanico getMecanico() {
		return Mecanico;
	}

	public void setMecanico(Mecanico Mecanico) {
		this.Mecanico = Mecanico;
	}
}