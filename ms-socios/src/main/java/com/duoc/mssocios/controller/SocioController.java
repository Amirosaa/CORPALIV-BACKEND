package com.duoc.mssocios.controller;

import com.duoc.mssocios.model.Socio;
import com.duoc.mssocios.repository.SocioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

    @Autowired
    private SocioRepository repository;

    @GetMapping
    public List<Socio> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Socio> buscar(@PathVariable Long id) {
        Socio socio = repository.findById(id).orElse(null);
        if (socio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(socio);
    }

    @PostMapping
    public Socio registrar(@RequestBody Socio socio) {
        return repository.save(socio);
    }

    @PostMapping("/login")
    public ResponseEntity<Socio> login(@RequestBody Socio datos) {
        Socio socio = repository.findByCorreoAndClave(datos.getCorreo(), datos.getClave());
        if (socio == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(socio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Socio> actualizar(@PathVariable Long id, @RequestBody Socio datos) {
        Socio socio = repository.findById(id).orElse(null);
        if (socio == null) {
            return ResponseEntity.notFound().build();
        }
        socio.setNombre(datos.getNombre());
        socio.setCorreo(datos.getCorreo());
        socio.setClave(datos.getClave());
        return ResponseEntity.ok(repository.save(socio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
