package com.api.senai.controllers;

import com.google.gson.Gson;

import java.util.Iterator;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.api.senai.classes.Cliente;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @GetMapping
    public String getAllClientes(Cliente cliente) {
        if (!Cliente.clientes.isEmpty()) {
            Gson gson = new Gson();
            return gson.toJson(Cliente.clientes);
        } else {
            return "Não há clientes cadastrados.";
        }
    }

    @PostMapping    
    public String addCliente(@RequestBody Cliente cliente) {
        Cliente.clientes.add(cliente);
        Gson gson = new Gson();
         return gson.toJson(cliente);
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
                Gson gson = new Gson();
                return gson.toJson(cliente);
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
                Gson gson = new Gson();
                return gson.toJson(cliente);
            }
        }
        return "Cliente não encontrado...";
    }

    @GetMapping("{id}")
    public String getbyId(@PathVariable UUID id) {
        for (Cliente cliente : Cliente.clientes) {
            if (cliente.getId().equals(id)) {
                Gson gson = new Gson();
                return gson.toJson(cliente);
            }
        }
        return "Cliente não encontrado...";
    }
}
