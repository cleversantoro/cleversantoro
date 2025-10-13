package br.edu.infnet.cleversantoro.model.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.cleversantoro.interfaces.CrudService;
import br.edu.infnet.cleversantoro.model.domain.Mecanico;

@Service
public class MecanicoService implements CrudService<Mecanico, Integer> {

	private final Map<Integer, Mecanico> mapa = new ConcurrentHashMap<Integer, Mecanico>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	

	@Override
	public Mecanico incluir(Mecanico Mecanico) {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Integer id) {
		mapa.remove(id);
	}

}
