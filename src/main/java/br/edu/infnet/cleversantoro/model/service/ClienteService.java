package br.edu.infnet.cleversantoro.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.cleversantoro.exceptions.ClienteInvalidoException;
import br.edu.infnet.cleversantoro.exceptions.ClienteNaoEncontratoException;
import br.edu.infnet.cleversantoro.model.domain.Cliente;
import br.edu.infnet.cleversantoro.model.repository.ClienteRepository;

@Service
public class ClienteService implements CrudService<Cliente, Integer> {

	private final ClienteRepository clienteRepository;	
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
    private void validar(Cliente cliente) {
        if(cliente == null) {
            throw new IllegalArgumentException("O Cliente não pode estar nulo!!");
        }
        
        if(cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new ClienteInvalidoException("O nome do Cliente é uma informação obrigatória!!!");
        }
    }
    
    @Override
    public Cliente incluir(Cliente cliente) {
		
		validar(cliente);
		
		if(cliente.getId() != null && cliente.getId() > 0) {
			throw new IllegalArgumentException("O novo cliente não pode ter um ID preenchido durante a inclusão!!");
		}

		return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> obterLista() {
        
        return clienteRepository.findAll();
    }

    @Override
    public Cliente alterar(Integer id, Cliente cliente) {
				
		validar(cliente);
		
		cliente.setId(id);
		
		return clienteRepository.save(cliente);
    }

    @Override
    public void excluir(Integer id) {
        Cliente cliente = obterPorId(id);

        clienteRepository.delete(cliente);
    }

    @Override
    public Cliente obterPorId(Integer id) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("O ID utilizado na busca do Cliente não pode ser nulo/zero/negativo");
        }
        
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontratoException("O Cliente com o ID ["+id+"] não foi encontrado!"));
    }
    
	public Cliente obterPorCpf(String cpf) {
		if(cpf == null || cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("O CPF utilizado na busca do Client não pode ser nulo ou vazio.");
		}
		
		return clienteRepository.findByCpf(cpf).orElseThrow(() -> new ClienteNaoEncontratoException("O Cliente com o CPF ["+cpf+"] não foi encontrado!"));
	}

	public Cliente obterPorFidelidade(String fidelidade) {
		if(fidelidade == null || fidelidade.trim().isEmpty()) {
			throw new IllegalArgumentException("A fidelidade utilizada na busca do Cliente não pode ser nulo ou vazio.");
		}
		
		return clienteRepository.findByFidelidade(fidelidade).orElseThrow(() -> new ClienteNaoEncontratoException("O Cliente com o fidelidade ["+fidelidade+"] não foi encontrado!"));
	}

}