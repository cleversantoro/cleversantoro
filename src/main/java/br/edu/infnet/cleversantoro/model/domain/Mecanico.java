package br.edu.infnet.cleversantoro.model.domain;

public class Mecanico extends Pessoa {
	
	private Integer id;

	private int matricula;
	private double salario;
	private String especialidade;
	private boolean ativo;
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
