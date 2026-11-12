package com.labsoftp.labsoftp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clientes
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El documento es obligatorio")
    private Integer documento;

    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;

    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fecha_nacimiento;
}
