package br.edu.infnet.cleversantoro.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.cleversantoro.exceptions.ServicoInvalidoException;
import br.edu.infnet.cleversantoro.exceptions.ServicoNaoEncontratoException;
import br.edu.infnet.cleversantoro.model.domain.Servico;
import br.edu.infnet.cleversantoro.model.domain.TipoServico;
import br.edu.infnet.cleversantoro.model.repository.ServicoRepository;

@Service
public class ServicoService implements CrudService<Servico, Integer> {
	
	private final ServicoRepository servicoRepository;	

	public ServicoService(ServicoRepository servicoRepository) {
		this.servicoRepository = servicoRepository;
	}


	public List<Servico> obterPorTipo(TipoServico tipoServico){
		return servicoRepository.findByTipoServico(tipoServico);
	}
	
	public List<Servico> obterPorTipoEvalor(TipoServico tipoServico, Double valor){
		return servicoRepository.findByTipoServicoAndValorGreaterThan(tipoServico, valor);
	}

	public List<Servico> obterPorTipoEvalorOrdenado(TipoServico tipoServico, Double valor){
		return servicoRepository.findByTipoServicoAndValorGreaterThanOrderByValorAsc(tipoServico, valor);
	}

	private void validar(Servico servico) {
		
		
		
		if(servico == null) {
			throw new IllegalArgumentException("O servico não pode estar nulo!!");
		}		
		if(servico.getDescricao() == null || servico.getDescricao().trim().isEmpty()) {
			throw new ServicoInvalidoException("A descrição do servico é uma informação obrigatória!!!");
		}			
		if(servico.getValor() == null || servico.getValor() <= 0) {
			throw new ServicoInvalidoException("O valor do servico deve ser positivo e é obrigatório!!!");
		}			
		if(servico.getTipoServico() == null) {
			throw new ServicoInvalidoException("O tipo do servico é uma informação obrigatória!!!");
		}
	}
	
	@Override
	public Servico incluir(Servico servico) {
		
		validar(servico);

		if(servico.getId() != null && servico.getId() > 0) {
			throw new IllegalArgumentException("Um novo servico não pode ter um ID preenchido durante a inclusão!!");
		}		

		return servicoRepository.save(servico);
	}

	@Override
	public List<Servico> obterLista() {
		
		return servicoRepository.findAll();
	}

	@Override
	public Servico alterar(Integer id, Servico servico) {
				
		validar(servico);

		obterPorId(id); 
		
		servico.setId(id);

		return servicoRepository.save(servico);
	}

	@Override
	public void excluir(Integer id) {

		Servico servico = obterPorId(id); 

		servicoRepository.delete(servico);
	}

	@Override
	public Servico obterPorId(Integer id) {
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID utilizado na busca do servico não pode ser nulo/zero/negativo.");
		}

		return servicoRepository.findById(id).orElseThrow(() -> new ServicoNaoEncontratoException("O servico com o ID ["+id+"] não foi encontrado!"));
	}
}