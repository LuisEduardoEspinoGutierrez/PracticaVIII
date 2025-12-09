package com.upiiz.practicaVIII.PracticaVIII.controllers;

import com.upiiz.practicaVIII.PracticaVIII.dto.EquipoCreateRequest;
import com.upiiz.practicaVIII.PracticaVIII.entities.Equipo;
import com.upiiz.practicaVIII.PracticaVIII.services.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@Tag(
        name = "Equipos",
        description = "Gestión de equipos: listar y crear"
)
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService){
        this.equipoService = equipoService;
    }

    // ---------------------- LISTAR ----------------------

    @Operation(
            summary = "Listar equipos",
            description = "Regresa todos los equipos registrados en el sistema."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de equipos obtenida correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Equipo.class))
    )
    @GetMapping
    public ResponseEntity<List<Equipo>> listar() {
        return ResponseEntity.ok(equipoService.getAll());
    }

    // ---------------------- CREAR ----------------------

    @Operation(
            summary = "Crear un nuevo equipo",
            description = "Crea un equipo enviando nombre, ciudad y ID de la liga a la que pertenece."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Equipo creado exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Equipo.class))
    )
    @PostMapping
    public ResponseEntity<Equipo> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para registrar un equipo",
                    required = true,
                    content = @Content(schema = @Schema(implementation = EquipoCreateRequest.class))
            )
            @RequestBody EquipoCreateRequest req
    ) {
        Equipo e = new Equipo(req.getNombre(), req.getCiudad());
        return ResponseEntity.ok(equipoService.create(e, req.getLigaId()));
    }
}


