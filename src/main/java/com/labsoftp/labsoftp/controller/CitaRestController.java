package com.labsoftp.labsoftp.controller;

import com.labsoftp.labsoftp.entity.Cita;
import com.labsoftp.labsoftp.entity.Mascota;
import com.labsoftp.labsoftp.repository.CitaRepository;
import com.labsoftp.labsoftp.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaRestController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @GetMapping
    public List<Cita> getAll() {
        return citaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getById(@PathVariable Long id) {
        return citaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cita> create(@RequestBody Cita cita) {
        if (cita.getMascota() != null && cita.getMascota().getId() != null) {
            Mascota mascota = mascotaRepository.findById(cita.getMascota().getId()).orElse(null);
            cita.setMascota(mascota);
        }
        Cita nueva = citaRepository.save(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> update(@PathVariable Long id, @RequestBody Cita cita) {
        return citaRepository.findById(id)
                .map(existente -> {
                    cita.setId(id);
                    if (cita.getMascota() != null && cita.getMascota().getId() != null) {
                        Mascota mascota = mascotaRepository.findById(cita.getMascota().getId()).orElse(existente.getMascota());
                        cita.setMascota(mascota);
                    } else {
                        cita.setMascota(existente.getMascota());
                    }
                    Cita actualizada = citaRepository.save(cita);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!citaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        citaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
