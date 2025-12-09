package com.upiiz.practicaVIII.PracticaVIII.services;

import com.upiiz.practicaVIII.PracticaVIII.entities.Equipo;
import com.upiiz.practicaVIII.PracticaVIII.entities.Liga;
import com.upiiz.practicaVIII.PracticaVIII.repositories.EquipoRepository;
import com.upiiz.practicaVIII.PracticaVIII.repositories.LigaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoService {
    private final EquipoRepository equipoRepository;
    private final LigaRepository ligaRepository;

    public EquipoService(EquipoRepository equipoRepository, LigaRepository ligaRepository){
        this.equipoRepository = equipoRepository;
        this.ligaRepository = ligaRepository;
    }

    public List<Equipo> getAll(){ return equipoRepository.findAll(); }

    public Equipo create(Equipo equipo, Long ligaId){
        if (ligaId != null) {
            Liga liga = ligaRepository.findById(ligaId).orElseThrow(() -> new RuntimeException("Liga no encontrada"));
            equipo.setLiga(liga);
            liga.getEquipos().add(equipo);
        }
        return equipoRepository.save(equipo);
    }

    public Equipo getById(Long id){ return equipoRepository.findById(id).orElseThrow(() -> new RuntimeException("Equipo no encontrado")); }
}


