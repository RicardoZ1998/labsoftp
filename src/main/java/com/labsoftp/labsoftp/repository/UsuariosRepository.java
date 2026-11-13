package com.labsoftp.labsoftp.repository;

import com.labsoftp.labsoftp.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long>
{
}
