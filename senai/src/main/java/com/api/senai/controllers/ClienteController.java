package com.api.senai.controllers;

import java.util.Iterator;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.api.senai.classes.Cliente;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @GetMapping
    public String getAllClientes() {
        if (!Cliente.clientes.isEmpty()) {
            return Cliente.clientes.toString();
        } else {
            return "Não há clientes cadastrados.";
        }
    }

    @PostMapping    
    public String addCliente(@RequestBody Cliente cliente) {
        Cliente.clientes.add(cliente);
        return "Cliente adicionado com sucesso!";
    }

    @PutMapping("{id}")
    public String atualizarCliente(@PathVariable UUID id, @RequestBody Cliente clienteAtualizado) {
        for (Cliente cliente : Cliente.clientes) {
            if (cliente.getId().equals(id)) {
                cliente.setNome(clienteAtualizado.getNome());
                cliente.setEmail(clienteAtualizado.getEmail());
                cliente.setCpf(clienteAtualizado.getCpf());
                cliente.setDataNascimento(clienteAtualizado.getDataNascimento());
                cliente.setTelefone(clienteAtualizado.getTelefone());
                return "Cliente atualizado com sucesso!";
            }
        }
        return "Cliente não encontrado...";
    }
    
    @DeleteMapping("{id}")
    public String deleteCliente(@PathVariable UUID id) {
        Iterator<Cliente> iterator = Cliente.clientes.iterator();
        while (iterator.hasNext()) {
            Cliente cliente = iterator.next();
            if (cliente.getId().equals(id)) {
                iterator.remove(); 
                return "Cliente deletado com sucesso!";
            }
        }
        return "Cliente não encontrado...";
    }

    @GetMapping("{id}")
    public String getbyId(@PathVariable UUID id) {
        for (Cliente cliente : Cliente.clientes) {
            if (cliente.getId().equals(id)) {
                return cliente.toString();
            }
        }
        return "Cliente não encontrado...";
    }
}
