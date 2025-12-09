package com.upiiz.practicaVIII.PracticaVIII.controllers;

import com.upiiz.practicaVIII.PracticaVIII.dto.EntrenadorCreateRequest;
import com.upiiz.practicaVIII.PracticaVIII.entities.Entrenador;
import com.upiiz.practicaVIII.PracticaVIII.services.EntrenadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
@Tag(
        name = "Entrenadores",
        description = "CRUD de entrenadores del sistema"
)
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    // ---------------------- LISTAR ----------------------

    @Operation(
            summary = "Listar entrenadores",
            description = "Devuelve una lista con todos los entrenadores registrados."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Entrenador.class)
            )
    )
    @GetMapping
    public ResponseEntity<List<Entrenador>> listar() {
        return ResponseEntity.ok(entrenadorService.getAll());
    }

    // ---------------------- CREAR ----------------------

    @Operation(
            summary = "Crear un nuevo entrenador",
            description = "Registra un nuevo entrenador enviando nombre, experiencia y nacionalidad."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Entrenador creado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Entrenador.class)
            )
    )
    @PostMapping
    public ResponseEntity<Entrenador> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para crear un entrenador",
                    required = true,
                    content = @Content(schema = @Schema(implementation = EntrenadorCreateRequest.class))
            )
            @RequestBody EntrenadorCreateRequest req
    ) {
        Entrenador e = new Entrenador(
                req.getNombre(),
                req.getExperiencia(),
                req.getNacionalidad()
        );
        return ResponseEntity.ok(entrenadorService.create(e));
    }
}

