package br.edu.infnet.cleversantoro.model.domain;

public class Mecanico {
	
	private Integer id;

	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	private int matricula;
	private double salario;
	private String especialidade;
	private boolean ativo;

	
	@Override
	public String toString() {
	    return String.format(
	        "%5d | Nome: %-50s | Email: %-50s | CPF: %s | Telefone: %s | Matrícula: %05d | Salário: R$ %8.2f | Especialidade: %-50s | Ativo: %-3s",
	        id,
	        nome,
	        email,
	        cpf,
	        telefone,
	        matricula,
	        salario,
	        especialidade,
	        ativo ? "Sim" : "Não"
	    );
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

}
