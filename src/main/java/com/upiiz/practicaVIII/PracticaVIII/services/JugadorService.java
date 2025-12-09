package com.upiiz.practicaVIII.PracticaVIII.services;

import com.upiiz.practicaVIII.PracticaVIII.entities.Equipo;
import com.upiiz.practicaVIII.PracticaVIII.entities.Jugador;
import com.upiiz.practicaVIII.PracticaVIII.repositories.EquipoRepository;
import com.upiiz.practicaVIII.PracticaVIII.repositories.JugadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JugadorService {
    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;
    public JugadorService(JugadorRepository jugadorRepository, EquipoRepository equipoRepository){
        this.jugadorRepository = jugadorRepository;
        this.equipoRepository = equipoRepository;
    }

    public List<Jugador> getAll(){ return jugadorRepository.findAll(); }

    public Jugador create(Jugador j, Long equipoId){
        Equipo equipo = equipoRepository.findById(equipoId).orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        j.setEquipo(equipo);
        equipo.getJugadores().add(j);
        return jugadorRepository.save(j);
    }
}

