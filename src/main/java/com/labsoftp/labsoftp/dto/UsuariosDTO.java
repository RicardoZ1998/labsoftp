package com.labsoftp.labsoftp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosDTO
{
    private long id_usuario;
    private String nombre;
    private String telefono;
    private String correo;
}
