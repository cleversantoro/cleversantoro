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

import br.edu.infnet.cleversantoro.model.domain.Cliente;
import br.edu.infnet.cleversantoro.model.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> incluir(@Valid @RequestBody Cliente cliente) {
        
        Cliente clienteIncluido = clienteService.incluir(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteIncluido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> alterar(@PathVariable Integer id, @RequestBody Cliente cliente) {
        Cliente clienteAlterado = clienteService.alterar(id, cliente);
        return ResponseEntity.ok(clienteAlterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    public ResponseEntity<List<Cliente>> obterLista() {
    	List<Cliente> lista = clienteService.obterLista();

    	if(lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterPorId(@PathVariable Integer id) {
        Cliente clienteObtido = clienteService.obterPorId(id);
        
        return ResponseEntity.ok(clienteObtido);
    }
        
}