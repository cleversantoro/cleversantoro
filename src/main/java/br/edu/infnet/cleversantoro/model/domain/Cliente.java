package br.edu.infnet.cleversantoro.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotNull;

@Entity
public class Cliente extends Pessoa {

	private String fidelidade;
	private LocalDateTime dataCadastramento;

	@Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return String.format(
            "%s | Fidelidade: %s | Cadastrado em: %s",
            super.toString(),
            fidelidade != null ? fidelidade : "N/A",
            dataCadastramento != null ? dataCadastramento.format(formatter) : "N/A"
        );
    }	

	public Cliente() {
		this.dataCadastramento = LocalDateTime.now();
	}

	public String getFidelidade() {
		return fidelidade;
	}
	public void setFidelidade(String fidelidade) {
		this.fidelidade = fidelidade;
	}
	
	public LocalDateTime getDataCadastramento() {
		return dataCadastramento;
	}
	public void setDataCadastramento(LocalDateTime dataCadastramento) {
		this.dataCadastramento = dataCadastramento;
	}
	
}