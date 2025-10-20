package br.edu.infnet.cleversantoro.model.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.cleversantoro.exceptions.MecanicoInvalidoException;
import br.edu.infnet.cleversantoro.exceptions.MecanicoNaoEncontratoException;
import br.edu.infnet.cleversantoro.interfaces.CrudService;
import br.edu.infnet.cleversantoro.model.domain.Mecanico;

@Service
public class MecanicoService implements CrudService<Mecanico, Integer> {

	private final Map<Integer, Mecanico> mapa = new ConcurrentHashMap<Integer, Mecanico>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	

	private void validar(Mecanico Mecanico) {
		//rn1 - o Mecanico não pode estar nulo (Mecanico == null)
		if(Mecanico == null) {
			throw new IllegalArgumentException("O Mecanico não pode estar nulo!!");
		}
		
		//rn2 - o nome do Mecanico é uma informação obrigatória (Mecanico.nome == null OU Mecanico.nome.vazio)
		if(Mecanico.getNome() == null || Mecanico.getNome().trim().isEmpty()) {
			throw new MecanicoInvalidoException("O nome do Mecanico é uma informação obrigatória!!!");
		}
		
		//rn3 - o novo Mecanico não pode ter um ID preenchido durante a inclusão (Mecanico.id == null E Mecanico.id > 0)
		if(Mecanico.getId() != null && Mecanico.getId() > 0) {
			throw new IllegalArgumentException("O novo Mecanico não pode ter um ID preenchido durante a inclusão!!");
		}
	}
	
	@Override
	public Mecanico incluir(Mecanico Mecanico) {
		
		validar(Mecanico);
		
		Mecanico.setId(nextId.getAndIncrement());
		mapa.put(Mecanico.getId(), Mecanico);

		return Mecanico;
	}

	@Override
	public List<Mecanico> obterLista() {
		
		return new ArrayList<Mecanico>(mapa.values());
	}

	@Override
	public Mecanico alterar(Integer id, Mecanico Mecanico) {
				
		validar(Mecanico);
		
		mapa.put(Mecanico.getId(), Mecanico);

		return Mecanico;
	}

	@Override
	public void excluir(Integer id) {
		Mecanico Mecanico = obterPorId(id);

		mapa.remove(Mecanico.getId());
	}

	@Override
	public Mecanico obterPorId(Integer id) {
		//rn4 - o ID utilizado na busca do Mecanico não pode ser nulo/zero (o parâmetro ID = null OU ID = 0)
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("o ID utilizado na busca do Mecanico não pode ser nulo/zero/negativo");
		}
		
		Mecanico Mecanico = mapa.get(id);
		
		//rn5 - o Mecanico com o ID x não foi encontrado (Mecanico == null)
		if(Mecanico == null) {
			throw new MecanicoNaoEncontratoException("O Mecanico com o ID ["+id+"] não foi encontrado!");
		}
		
		return Mecanico;
	}

	public Mecanico inativar(Integer id) {
		
		Mecanico Mecanico = obterPorId(id);
		
		if(!Mecanico.isAtivo()) {
			System.err.println("O Mecanico "+Mecanico.getNome()+" não está inativo!");
			return Mecanico;
		}
		
		Mecanico.setAtivo(false);

		return Mecanico;
	}
}