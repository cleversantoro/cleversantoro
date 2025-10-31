package br.edu.infnet.cleversantoro.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.cleversantoro.model.domain.Mecanico;

@Repository
public interface MecanicoRepository extends JpaRepository<Mecanico, Integer> {

    Optional<Mecanico> findByCpf(String cpf);
}
