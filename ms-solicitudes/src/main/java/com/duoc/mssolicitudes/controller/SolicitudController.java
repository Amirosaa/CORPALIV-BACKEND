package com.duoc.mssolicitudes.controller;


import com.duoc.mssolicitudes.model.Solicitud;
import com.duoc.mssolicitudes.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudRepository repository;

    @PostMapping
    public Solicitud crear(@RequestBody Solicitud solicitud){
        solicitud.setEstado("Enviada");
        return repository.save(solicitud);
    }

    @GetMapping
    public List<Solicitud> listarPorCorreo(@RequestParam String correo) {
        return repository.findByCorreo(correo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> buscar(@PathVariable Long id) {
        Solicitud solicitud = repository.findById(id),orElse(null);
        if (solicitud == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(solicitud);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> actualizar(@PathVariable Long id, @RequestBody Solicitud datos){
        Solicitud solicitud = repository.findById(id).orElse(null);
        if (solicitud == null){
            return ResponseEntity.notFound().build();
        }
        solicitud.setNombre(datos.getNombre());
        solicitud.setCorreo(datos.getCorreo());
        solicitud.setTipoColaborador(datos.getTipoColaborador());
        solicitud.setFormaColaboracion(datos.getFormaColaboracion());
        solicitud.setMensaje(datos.getMensaje());
        solicitud.setEstado(datos.getEstado());
        return ResponseEntity.ok(repository.save(solicitud));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}
