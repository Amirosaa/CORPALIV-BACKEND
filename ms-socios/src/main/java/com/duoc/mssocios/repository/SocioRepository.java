package com.duoc.mssocios.repository;

import com.duoc.mssocios.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<Socio, Long> {

    // busca el socio para el login
    Socio findByCorreoAndClave(String correo, String clave);
}
