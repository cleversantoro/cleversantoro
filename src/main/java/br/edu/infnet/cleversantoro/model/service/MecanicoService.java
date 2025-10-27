package br.edu.infnet.cleversantoro.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.cleversantoro.exceptions.MecanicoInvalidoException;
import br.edu.infnet.cleversantoro.exceptions.MecanicoNaoEncontratoException;
import br.edu.infnet.cleversantoro.model.domain.Mecanico;
import br.edu.infnet.cleversantoro.model.repository.MecanicoRepository;

@Service
public class MecanicoService implements CrudService<Mecanico, Integer> {

	private final MecanicoRepository mecanicoRepository;	

	public MecanicoService(MecanicoRepository mecanicoRepository) {
		this.mecanicoRepository = mecanicoRepository;
	}

	private void validar(Mecanico mecanico) {
		if(mecanico == null) {
			throw new IllegalArgumentException("O Mecanico não pode estar nulo!!");
		}
		
		if(mecanico.getNome() == null || mecanico.getNome().trim().isEmpty()) {
			throw new MecanicoInvalidoException("O nome do Mecanico é uma informação obrigatória!!!");
		}		
	}
	
	@Override
	public Mecanico incluir(Mecanico mecanico) {
		
		validar(mecanico);

		if(mecanico.getId() != null && mecanico.getId() > 0) {
			throw new IllegalArgumentException("O novo mecanico não pode ter um ID preenchido durante a inclusão!!");
		}		
		
		return mecanicoRepository.save(mecanico);		
	}

	@Override
	public List<Mecanico> obterLista() {	
		return mecanicoRepository.findAll();
	}

	@Override
	public Mecanico alterar(Integer id, Mecanico mecanico) {
			
		validar(mecanico);
		
		mecanico.setId(id);
		
		return mecanicoRepository.save(mecanico);
	}

	@Override
	public void excluir(Integer id) {
		Mecanico mecanico = obterPorId(id);

		mecanicoRepository.delete(mecanico);
	}

	@Override
	public Mecanico obterPorId(Integer id) {
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("o ID utilizado na busca do vendedor não pode ser nulo/zero/negativo");
		}

		return mecanicoRepository.findById(id).orElseThrow(() -> new MecanicoNaoEncontratoException("O vendedor com o ID ["+id+"] não foi encontrado!"));
	}

	public Mecanico inativar(Integer id) {
		
		Mecanico mecanico = obterPorId(id);
		
		if(!mecanico.isAtivo()) {
			System.err.println("O vendedor "+mecanico.getNome()+" não está inativo!");
			return mecanico;
		}
		
		mecanico.setAtivo(false);

		return mecanicoRepository.save(mecanico);
	}
}