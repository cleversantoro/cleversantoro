package br.edu.infnet.cleversantoro.controllers;

import java.util.List;

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
import br.edu.infnet.cleversantoro.model.domain.service.MecanicoService;

@RestController
@RequestMapping("/api/mecanicos")
public class MecanicoController {

	private final MecanicoService MecanicoService;
	
	public MecanicoController(MecanicoService MecanicoService) {
		this.MecanicoService = MecanicoService;
	}

	@PostMapping
	public Mecanico incluir(@RequestBody Mecanico Mecanico) {
		
		Mecanico MecanicoIncluido = MecanicoService.incluir(Mecanico);

		return MecanicoIncluido;
	}

	@PutMapping("/{id}")
	public Mecanico alterar(@PathVariable Integer id, @RequestBody Mecanico Mecanico) {

		Mecanico MecanicoAlterado = MecanicoService.alterar(id, Mecanico);
		
		return MecanicoAlterado;
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		MecanicoService.excluir(id);
	}
	
	@GetMapping
	public List<Mecanico> obterLista() {
		
		return MecanicoService.obterLista();
	}
	
	@GetMapping("/{id}")
	public Mecanico obterPorId(@PathVariable Integer id) {
		
		Mecanico MecanicoObtido = MecanicoService.obterPorId(id);
		
		return MecanicoObtido;
	}
	
	@PatchMapping("/{id}/inativar")
	public Mecanico inativar(@PathVariable Integer id) {
		
		Mecanico MecanicoInativado = MecanicoService.inativar(id);
		
		return MecanicoInativado;
	}
}