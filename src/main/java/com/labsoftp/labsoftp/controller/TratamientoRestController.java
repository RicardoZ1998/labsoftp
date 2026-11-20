package com.labsoftp.labsoftp.controller;

import com.labsoftp.labsoftp.entity.Cita;
import com.labsoftp.labsoftp.entity.Tratamiento;
import com.labsoftp.labsoftp.repository.CitaRepository;
import com.labsoftp.labsoftp.repository.TratamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
public class TratamientoRestController {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private CitaRepository citaRepository;

    @GetMapping
    public List<Tratamiento> getAll() {
        return tratamientoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tratamiento> getById(@PathVariable Long id) {
        return tratamientoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tratamiento> create(@RequestBody Tratamiento tratamiento) {
        if (tratamiento.getCita() != null && tratamiento.getCita().getId() != null) {
            Cita cita = citaRepository.findById(tratamiento.getCita().getId()).orElse(null);
            tratamiento.setCita(cita);
        }
        Tratamiento nuevo = tratamientoRepository.save(tratamiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tratamiento> update(@PathVariable Long id, @RequestBody Tratamiento tratamiento) {
        return tratamientoRepository.findById(id)
                .map(existente -> {
                    tratamiento.setId(id);
                    if (tratamiento.getCita() != null && tratamiento.getCita().getId() != null) {
                        Cita cita = citaRepository.findById(tratamiento.getCita().getId()).orElse(existente.getCita());
                        tratamiento.setCita(cita);
                    } else {
                        tratamiento.setCita(existente.getCita());
                    }
                    Tratamiento actualizado = tratamientoRepository.save(tratamiento);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!tratamientoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tratamientoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
