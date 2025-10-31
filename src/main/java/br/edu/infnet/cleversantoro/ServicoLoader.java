package br.edu.infnet.cleversantoro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.cleversantoro.exceptions.MecanicoNaoEncontratoException;
import br.edu.infnet.cleversantoro.model.domain.Servico;
import br.edu.infnet.cleversantoro.model.domain.TipoServico;
import br.edu.infnet.cleversantoro.model.domain.Mecanico;
import br.edu.infnet.cleversantoro.model.service.ServicoService;
import br.edu.infnet.cleversantoro.model.service.MecanicoService;

@Order(2)
@Component
public class ServicoLoader implements ApplicationRunner {
	
	private final ServicoService ServicoService;
	private final MecanicoService MecanicoService;
	
	public ServicoLoader(ServicoService ServicoService, MecanicoService MecanicoService) {
		this.ServicoService = ServicoService;
		this.MecanicoService = MecanicoService;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("[ServicoLoader] Iniciando carregamento de Servicos...");

		try (FileReader arquivo = new FileReader("servicos-listagem.csv");
			 BufferedReader leitura = new BufferedReader(arquivo)) {
		
			String linha = leitura.readLine();

			if (linha == null || linha.isEmpty()) {
				System.out.println("[ServicoLoader] Arquivo CSV de Servicos vazio ou sem cabeçalho.");
				return;
			}
			
			while(linha != null) {

				String[] campos = linha.split(";");
				
				String cpfMecanico = campos[3];
				
				Mecanico MecanicoResponsavel = null;
				try {
					MecanicoResponsavel = MecanicoService.obterPorCpf(cpfMecanico);

					if(MecanicoResponsavel == null) {
						System.err.println("[ERRO] Mecanico não encontrado!!!");
						
						linha = leitura.readLine();
						
						continue;
					}
					
					Servico Servico = new Servico();
					Servico.setDescricao(campos[0]);
					Servico.setValor(Double.valueOf(campos[1]));
					Servico.setTipoServico(TipoServico.valueOf(campos[2]));
					
					Servico.setMecanico(MecanicoResponsavel);

					ServicoService.incluir(Servico);
					System.out.println("[ServicoLoader] Servico incluído: " + Servico.getDescricao() + ")");

				} catch (MecanicoNaoEncontratoException e) {
					System.err.println("[ERRO] " + e.getMessage());
				}
				

				linha = leitura.readLine();
			}

		} catch (IOException e) {
			System.err.println("[ServicoLoader] Erro ao ler o arquivo Servicos-listagem.csv: " + e.getMessage());
		}

		Collection<Servico> Servicos = ServicoService.obterLista();
		
		System.out.println("\n--- Servicos Carregados ---");
		if (Servicos.isEmpty()) {
			System.out.println("Nenhum Servico foi carregado.");
		} else {
			Servicos.forEach(System.out::println);
		}
		System.out.println("---------------------------\n");
	}
}