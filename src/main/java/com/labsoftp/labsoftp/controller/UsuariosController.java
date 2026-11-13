package com.labsoftp.labsoftp.controller;

import com.labsoftp.labsoftp.dto.UsuariosDTO;
import com.labsoftp.labsoftp.entity.Clientes;
import com.labsoftp.labsoftp.entity.Usuarios;
import com.labsoftp.labsoftp.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController
{
    @Autowired
    private UsuariosRepository usuariosRepository;

    public UsuariosDTO convertirDTO(Usuarios usuarios)
    {
        return new UsuariosDTO(
                usuarios.getId_usuario(),
                usuarios.getNombre(),
                usuarios.getTelefono(),
                usuarios.getCorreo());
    }

    @GetMapping
    public List<UsuariosDTO> getAll()
    {
        return usuariosRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public UsuariosDTO getById(@PathVariable Long id)
    {
        return usuariosRepository.findById(id)
                .map(this::convertirDTO)
                .orElse(null);
    }

    @PostMapping
    public UsuariosDTO create(@RequestBody Usuarios usuarios)
    {
        Usuarios usuario = usuariosRepository.save(usuarios);
        return convertirDTO(usuario);
    }

    @PutMapping("/{id}")
    public UsuariosDTO update(@PathVariable Long id, @RequestBody Usuarios usuarios)
    {
        usuarios.setId_usuario(id);
        Usuarios usuario = usuariosRepository.findById(id).orElse(null);
        return convertirDTO(usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        usuariosRepository.deleteById(id);
    }
}
