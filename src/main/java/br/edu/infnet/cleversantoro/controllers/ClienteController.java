package br.edu.infnet.cleversantoro.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.cleversantoro.model.domain.Cliente; // Importar a classe Cliente
import br.edu.infnet.cleversantoro.model.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService ClienteService;
    
    public ClienteController(ClienteService ClienteService) {
        this.ClienteService = ClienteService;
    }

    @PostMapping
    public Cliente incluir(@RequestBody Cliente Cliente) {
        
        Cliente ClienteIncluido = ClienteService.incluir(Cliente);

        return ClienteIncluido;
    }

    @PutMapping("/{id}")
    public Cliente alterar(@PathVariable Integer id, @RequestBody Cliente Cliente) {

        Cliente ClienteAlterado = ClienteService.alterar(id, Cliente);
        
        return ClienteAlterado;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        ClienteService.excluir(id);
    }
    
    @GetMapping
    public List<Cliente> obterLista() {
        
        return ClienteService.obterLista();
    }
    
    @GetMapping("/{id}")
    public Cliente obterPorId(@PathVariable Integer id) {
        
        Cliente ClienteObtido = ClienteService.obterPorId(id);
        
        return ClienteObtido;
    }
}