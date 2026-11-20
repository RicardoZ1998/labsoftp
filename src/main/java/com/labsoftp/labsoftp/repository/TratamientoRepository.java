package com.labsoftp.labsoftp.repository;

import com.labsoftp.labsoftp.entity.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
    List<Tratamiento> findByCitaId(Long citaId);
}
