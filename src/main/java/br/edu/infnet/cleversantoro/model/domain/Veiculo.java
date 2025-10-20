package br.edu.infnet.cleversantoro.model.domain;

public class Veiculo {
	
	  private String placa;
	  private String renavam;
	  private String chassi;
	  private int ano_fab;
	  private int ano_mod;
	  private String cor;
	  private String combustivel;  //'gasolina','etanol','flex','diesel','eletrico','hibrido','outro'),
	  private int km_atual;
	  private String observacoes;
	  
	  
    @Override
	public String toString() {
	    return String.format(
		        "%s | Placa: %-50s | Renavam: %-50s | Chassi: %-50s | Ano_Fabicação: %05d | Ano_Modelo: %05d | Cor: %-50s | Combustivel: %-50s | Km: %05d | Observações: %-50s",
		        super.toString(),
		        placa,
		        renavam,
		        chassi,
		        ano_fab,
		        ano_mod,
		        cor,
		        combustivel,
		        km_atual,
		        observacoes
	    	);    
    }
   
    
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getRenavam() {
		return renavam;
	}
	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}
	
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	
	public int getAno_fab() {
		return ano_fab;
	}
	
	public void setAno_fab(int ano_fab) {
		this.ano_fab = ano_fab;
	}
	public int getAno_mod() {
		return ano_mod;
	}	
	public void setAno_mod(int ano_mod) {
		this.ano_mod = ano_mod;
	}
	
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	
	public int getKm_atual() {
		return km_atual;
	}
	public void setKm_atual(int km_atual) {
		this.km_atual = km_atual;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


}
