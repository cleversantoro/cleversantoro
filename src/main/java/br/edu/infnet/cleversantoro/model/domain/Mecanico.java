package br.edu.infnet.cleversantoro.model.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Mecanico extends Pessoa {
	
	@NotNull(message = "A matrícula é obrigatória.")
	@Min(value = 1, message = "A matrícula deve ser um número positivo maior q zero.")
	private int matricula;

	@NotNull(message = "A matrícula é obrigatória.")
	@Min(value = 1, message = "A matrícula deve ser um número positivo maior q zero.")
	private double salario;

	private String especialidade;
	private boolean ativo;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	@Valid
	private Endereco endereco;
	
	@Override
	public String toString() {
	    return String.format(
	        "%s | Matrícula: %05d | Salário: R$ %8.2f | Especialidade: %-50s | Ativo: %-3s | Endereco: %s",
	        super.toString(),
	        matricula,
	        salario,
	        especialidade,
	        ativo ? "Sim" : "Não",
	        endereco.getCep()
	    );
	}
	
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public double getSalario() {
		return salario;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
