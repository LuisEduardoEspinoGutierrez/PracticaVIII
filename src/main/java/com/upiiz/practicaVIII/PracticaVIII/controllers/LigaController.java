package com.upiiz.practicaVIII.PracticaVIII.controllers;

import com.upiiz.practicaVIII.PracticaVIII.dto.LigaCreateRequest;
import com.upiiz.practicaVIII.PracticaVIII.entities.Liga;
import com.upiiz.practicaVIII.PracticaVIII.services.LigaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ligas")
@Tag(
        name = "Ligas",
        description = "Operaciones relacionadas con ligas: listar y crear nuevas ligas"
)
public class LigaController {

    private final LigaService ligaService;

    public LigaController(LigaService ligaService){
        this.ligaService = ligaService;
    }

    // ---------------------- LISTAR LIGAS ----------------------

    @Operation(
            summary = "Listar ligas",
            description = "Obtiene la lista completa de ligas registradas."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de ligas obtenida correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Liga.class)
            )
    )
    @GetMapping
    public ResponseEntity<List<Liga>> listar() {
        return ResponseEntity.ok(ligaService.getAll());
    }

    // ---------------------- CREAR LIGA ----------------------

    @Operation(
            summary = "Crear una nueva liga",
            description = "Registra una liga nueva enviando nombre, país y temporada actual."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liga creada correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Liga.class)
            )
    )
    @PostMapping
    public ResponseEntity<Liga> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información necesaria para crear una liga",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LigaCreateRequest.class))
            )
            @RequestBody LigaCreateRequest req
    ) {
        Liga l = new Liga(req.getNombre(), req.getPais(), req.getTemporadaActual());
        return ResponseEntity.ok(ligaService.create(l));
    }
}


