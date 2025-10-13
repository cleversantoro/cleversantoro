package br.edu.infnet.cleversantoro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.cleversantoro.model.domain.Mecanico;
import br.edu.infnet.cleversantoro.model.domain.service.MecanicoService;

@Component
public class MecanicoLoader implements ApplicationRunner {
	
	private final MecanicoService MecanicoService;
	
	public MecanicoLoader(MecanicoService MecanicoService) {
		this.MecanicoService = MecanicoService;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		FileReader arquivo = new FileReader("mecanicos-listagem.csv");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();
		String[] campos = null;

		while(linha != null) {

			campos = linha.split(";");
			
			Mecanico Mecanico = new Mecanico();
			Mecanico.setNome(campos[0]);
			Mecanico.setEmail(campos[1]);
			Mecanico.setCpf(campos[2]);
			Mecanico.setTelefone(campos[3]);
			Mecanico.setMatricula(Integer.valueOf(campos[4]));
			Mecanico.setSalario(Double.valueOf(campos[5]));
			Mecanico.setEspecialidade(campos[6]);
			Mecanico.setAtivo(Boolean.valueOf(campos[7]));
			
			MecanicoService.incluir(Mecanico);
			
			linha = leitura.readLine();
		}

		Collection<Mecanico> Mecanicoes = MecanicoService.obterLista();
		
		Mecanicoes.forEach(System.out::println);
		
		leitura.close();
	}

}