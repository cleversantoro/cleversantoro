package br.edu.infnet.cleversantoro.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.cleversantoro.model.domain.Mecanico;
import br.edu.infnet.cleversantoro.model.service.MecanicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mecanicos")
public class MecanicoController {

	private final MecanicoService mecanicoService;

	public MecanicoController(MecanicoService mecanicoService) {
		this.mecanicoService = mecanicoService;
	}

	@PostMapping
	public ResponseEntity<Mecanico> incluir(@Valid @RequestBody Mecanico Mecanico) {

		Mecanico mecanicoIncluido = mecanicoService.incluir(Mecanico);

		return ResponseEntity.status(HttpStatus.CREATED).body(mecanicoIncluido);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Mecanico> alterar(@PathVariable Integer id, @RequestBody Mecanico Mecanico) {
		Mecanico mecanicoAlterado = mecanicoService.alterar(id, Mecanico);
		return ResponseEntity.ok(mecanicoAlterado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		mecanicoService.excluir(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<Mecanico>> obterLista() {
		
		List<Mecanico> lista = mecanicoService.obterLista();

		if(lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Mecanico> obterPorId(@PathVariable Integer id) {

		Mecanico mecanicoObtido = mecanicoService.obterPorId(id);

		return ResponseEntity.ok(mecanicoObtido);
	}

	@PatchMapping("/{id}/inativar")
	public ResponseEntity<Mecanico> inativar(@PathVariable Integer id) {

		Mecanico mecanicoInativado = mecanicoService.inativar(id);

		return ResponseEntity.ok(mecanicoInativado);
	}
}