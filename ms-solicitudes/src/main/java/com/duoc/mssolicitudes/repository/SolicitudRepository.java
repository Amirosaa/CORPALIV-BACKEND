package com.duoc.mssolicitudes.repository;

import com.duoc.mssolicitudes.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    List<Solicitud> findByCorreo(String correo);
}
