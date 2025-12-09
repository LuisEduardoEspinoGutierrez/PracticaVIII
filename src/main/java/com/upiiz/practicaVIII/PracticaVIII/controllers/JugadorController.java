package com.upiiz.practicaVIII.PracticaVIII.controllers;

import com.upiiz.practicaVIII.PracticaVIII.dto.JugadorCreateRequest;
import com.upiiz.practicaVIII.PracticaVIII.entities.Jugador;
import com.upiiz.practicaVIII.PracticaVIII.services.JugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
@Tag(
        name = "Jugadores",
        description = "Operaciones relacionadas con jugadores: listar y crear"
)
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService){
        this.jugadorService = jugadorService;
    }

    // ---------------------- LISTAR JUGADORES ----------------------

    @Operation(
            summary = "Listar jugadores",
            description = "Obtiene la lista completa de jugadores registrados."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de jugadores obtenida correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Jugador.class)
            )
    )
    @GetMapping
    public ResponseEntity<List<Jugador>> listar() {
        return ResponseEntity.ok(jugadorService.getAll());
    }

    // ---------------------- CREAR JUGADOR ----------------------

    @Operation(
            summary = "Crear un nuevo jugador",
            description = "Registra un jugador nuevo enviando nombre, posición, número y el ID del equipo al que pertenece."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Jugador creado correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Jugador.class)
            )
    )
    @PostMapping
    public ResponseEntity<Jugador> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información necesaria para crear un jugador",
                    required = true,
                    content = @Content(schema = @Schema(implementation = JugadorCreateRequest.class))
            )
            @RequestBody JugadorCreateRequest req
    ) {
        Jugador j = new Jugador(req.getNombre(), req.getPosicion(), req.getNumero());
        return ResponseEntity.ok(jugadorService.create(j, req.getEquipoId()));
    }
}

