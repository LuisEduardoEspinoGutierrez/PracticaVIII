package com.upiiz.practicaVIII.PracticaVIII.services;

import com.upiiz.practicaVIII.PracticaVIII.entities.Entrenador;
import com.upiiz.practicaVIII.PracticaVIII.repositories.EntrenadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntrenadorService {
    private final EntrenadorRepository entrenadorRepository;
    public EntrenadorService(EntrenadorRepository entrenadorRepository){ this.entrenadorRepository = entrenadorRepository; }
    public List<Entrenador> getAll(){ return entrenadorRepository.findAll(); }
    public Entrenador create(Entrenador e){ return entrenadorRepository.save(e); }
}
