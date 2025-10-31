package br.edu.infnet.cleversantoro.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.cleversantoro.model.domain.Servico;
import br.edu.infnet.cleversantoro.model.domain.TipoServico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

	List<Servico> findByTipoServico(TipoServico tipoServico);
	List<Servico> findByTipoServicoAndValorGreaterThan(TipoServico tipoServico, Double valor);
	List<Servico> findByTipoServicoAndValorGreaterThanOrderByValorAsc(TipoServico tipoServico, Double valor);
}
