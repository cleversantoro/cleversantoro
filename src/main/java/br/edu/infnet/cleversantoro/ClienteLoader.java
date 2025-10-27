package br.edu.infnet.cleversantoro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.cleversantoro.model.domain.Cliente;
import br.edu.infnet.cleversantoro.model.service.ClienteService;

@Component
public class ClienteLoader implements ApplicationRunner {
    
    private final ClienteService ClienteService;

    public ClienteLoader(ClienteService ClienteService) {
        this.ClienteService = ClienteService;
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("[ClienteLoader] Iniciando carregamento de Clientees...");

        try (FileReader arquivo = new FileReader("clientes-listagem.csv");
             BufferedReader leitura = new BufferedReader(arquivo)) {
        
            String linha = leitura.readLine(); // Lê o cabeçalho

            if (linha == null || linha.isEmpty()) {
                System.out.println("[ClienteLoader] Arquivo CSV de Clientees vazio ou sem cabeçalho.");
                return;
            }
            
            linha = leitura.readLine();

            while(linha != null) {

                String[] campos = linha.split(";");

                if (campos.length >= 5) {
                    Cliente Cliente = new Cliente();
                    Cliente.setNome(campos[0]);
                    Cliente.setEmail(campos[1]);
                    Cliente.setCpf(campos[2]);
                    Cliente.setTelefone(campos[3]);
                    Cliente.setFidelidade(campos[4]);
                    
                    ClienteService.incluir(Cliente);
                } else {
                    System.err.println("[ClienteLoader] Linha inválida no CSV (número de campos insuficiente): " + linha);
                }
                
                linha = leitura.readLine();
            }

        } catch (IOException e) {
            System.err.println("[ClienteLoader] Erro ao ler o arquivo Clientees-listagem.csv: " + e.getMessage());
        }

        Collection<Cliente> Clientes = ClienteService.obterLista();
        
        System.out.println("\n--- Clientes Carregados ---");
        if (Clientes.isEmpty()) {
            System.out.println("Nenhum Cliente foi carregado.");
        } else {
            Clientes.forEach(System.out::println);
        }
        System.out.println("----------------------------\n");
    }
}