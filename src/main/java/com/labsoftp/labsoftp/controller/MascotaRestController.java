package com.labsoftp.labsoftp.controller;

import com.labsoftp.labsoftp.entity.Dueno;
import com.labsoftp.labsoftp.entity.Mascota;
import com.labsoftp.labsoftp.repository.DuenoRepository;
import com.labsoftp.labsoftp.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaRestController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private DuenoRepository duenoRepository;

    @GetMapping
    public List<Mascota> getAll() {
        return mascotaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> getById(@PathVariable Long id) {
        return mascotaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mascota> create(@RequestBody Mascota mascota) {
        if (mascota.getDueno() != null && mascota.getDueno().getId() != null) {
            Dueno dueno = duenoRepository.findById(mascota.getDueno().getId()).orElse(null);
            mascota.setDueno(dueno);
        }
        Mascota nueva = mascotaRepository.save(mascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> update(@PathVariable Long id, @RequestBody Mascota mascota) {
        return mascotaRepository.findById(id)
                .map(existente -> {
                    mascota.setId(id);
                    if (mascota.getDueno() != null && mascota.getDueno().getId() != null) {
                        Dueno dueno = duenoRepository.findById(mascota.getDueno().getId()).orElse(existente.getDueno());
                        mascota.setDueno(dueno);
                    } else {
                        mascota.setDueno(existente.getDueno());
                    }
                    Mascota actualizada = mascotaRepository.save(mascota);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!mascotaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        mascotaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
