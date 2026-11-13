package com.labsoftp.labsoftp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuarios
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id_usuario;
   String nombre;
   String telefono;
   String correo;
   String username;
   String contrasena;
   String rol;

}
