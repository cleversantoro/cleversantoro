package br.edu.infnet.cleversantoro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.cleversantoro.clients.ViaCepFeignClient;
import br.edu.infnet.cleversantoro.model.domain.Mecanico;
import br.edu.infnet.cleversantoro.model.service.MecanicoService;

@Order(1)
@Component
public class MecanicoLoader implements ApplicationRunner {
	
	private final MecanicoService MecanicoService;
	private final ViaCepFeignClient cepFeignClient;
	
	public MecanicoLoader(MecanicoService MecanicoService, ViaCepFeignClient cepFeignClient) {
		this.MecanicoService = MecanicoService;
		this.cepFeignClient = cepFeignClient;
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
			Mecanico.setEndereco(cepFeignClient.findByCep(campos[8]));
			
			MecanicoService.incluir(Mecanico);
			
			linha = leitura.readLine();
		}

		Collection<Mecanico> Mecanicos = MecanicoService.obterLista();
		
		Mecanicos.forEach(System.out::println);
		
		leitura.close();
	}

}