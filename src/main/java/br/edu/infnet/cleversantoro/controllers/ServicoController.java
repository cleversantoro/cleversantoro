package br.edu.infnet.cleversantoro.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.cleversantoro.model.domain.Servico;
import br.edu.infnet.cleversantoro.model.service.ServicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

	private final ServicoService ServicoService;
	
	public ServicoController(ServicoService ServicoService) {
		this.ServicoService = ServicoService;
	}

	@PostMapping
	public ResponseEntity<Servico> incluir(@Valid @RequestBody Servico Servico) {
		Servico ServicoIncluido = ServicoService.incluir(Servico);
		return ResponseEntity.status(HttpStatus.CREATED).body(ServicoIncluido);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Servico> alterar(@PathVariable Integer id, @Valid @RequestBody Servico Servico) {
		Servico ServicoAlterado = ServicoService.alterar(id, Servico);
		return ResponseEntity.ok(ServicoAlterado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		ServicoService.excluir(id);
		return ResponseEntity.noContent().build(); // Retorna 204 No Content para exclusão bem-sucedida
	}
	
	@GetMapping
	public ResponseEntity<List<Servico>> obterLista() {
		List<Servico> lista = ServicoService.obterLista();
		if(lista.isEmpty()) {
			return ResponseEntity.noContent().build(); // Retorna 204 se a lista estiver vazia
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Servico> obterPorId(@PathVariable Integer id) {
		Servico ServicoObtido = ServicoService.obterPorId(id);
		return ResponseEntity.ok(ServicoObtido);
	}
}