package br.edu.infnet.cleversantoro.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.cleversantoro.exceptions.ClienteInvalidoException;
import br.edu.infnet.cleversantoro.exceptions.ClienteNaoEncontratoException;
import br.edu.infnet.cleversantoro.model.domain.Cliente;

@Service
public class ClienteService implements CrudService<Cliente, Integer> {

    private final Map<Integer, Cliente> mapa = new ConcurrentHashMap<Integer, Cliente>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    private void validar(Cliente Cliente) {
        if(Cliente == null) {
            throw new IllegalArgumentException("O Cliente não pode estar nulo!!");
        }
        
        if(Cliente.getNome() == null || Cliente.getNome().trim().isEmpty()) {
            throw new ClienteInvalidoException("O nome do Cliente é uma informação obrigatória!!!");
        }

        if(Cliente.getId() != null && Cliente.getId() > 0) {
            throw new IllegalArgumentException("O novo Cliente não pode ter um ID preenchido durante a inclusão/alteração (use o método alterar com um ID válido)!!");
        }
    }
    
    @Override
    public Cliente incluir(Cliente Cliente) {
		
		validar(Cliente);
		
		Cliente.setId(nextId.getAndIncrement());
		mapa.put(Cliente.getId(), Cliente);

		return Cliente;
    }

    @Override
    public List<Cliente> obterLista() {
        
        return new ArrayList<Cliente>(mapa.values());
    }

    @Override
    public Cliente alterar(Integer id, Cliente Cliente) {
				
		validar(Cliente);
		
		mapa.put(Cliente.getId(), Cliente);
		
		return Cliente;
    }

    @Override
    public void excluir(Integer id) {
        Cliente Cliente = obterPorId(id);

        mapa.remove(Cliente.getId());
    }

    @Override
    public Cliente obterPorId(Integer id) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("O ID utilizado na busca do Cliente não pode ser nulo/zero/negativo");
        }
        
        Cliente Cliente = mapa.get(id);
        
        if(Cliente == null) {
            throw new ClienteNaoEncontratoException("O Cliente com o ID ["+id+"] não foi encontrado!");
        }
        
        return Cliente;
    }
}