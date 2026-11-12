package com.labsoftp.labsoftp.controller;

import com.labsoftp.labsoftp.entity.Clientes;
import com.labsoftp.labsoftp.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController
{
    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping
    public List<Clientes> getAll()
    {
        return clientesRepository.findAll();
    }

    @GetMapping("/{id}")
    public Clientes getById(@PathVariable Long id)
    {
        return clientesRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Clientes create(@RequestBody Clientes cliente)
    {
        return clientesRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public Clientes update(@PathVariable Long id, @RequestBody Clientes cliente)
    {
        cliente.setId_cliente(id);
        return clientesRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        clientesRepository.deleteById(id);
    }
}
