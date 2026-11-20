package com.labsoftp.labsoftp.controller;

import com.labsoftp.labsoftp.entity.Dueno;
import com.labsoftp.labsoftp.repository.DuenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duenos")
public class DuenoRestController {

    @Autowired
    private DuenoRepository duenoRepository;

    @GetMapping
    public List<Dueno> getAll() {
        return duenoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dueno> getById(@PathVariable Long id) {
        return duenoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Dueno> create(@RequestBody Dueno dueno) {
        Dueno nuevo = duenoRepository.save(dueno);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dueno> update(@PathVariable Long id, @RequestBody Dueno dueno) {
        return duenoRepository.findById(id)
                .map(existente -> {
                    dueno.setId(id);
                    Dueno actualizado = duenoRepository.save(dueno);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!duenoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        duenoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
