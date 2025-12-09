package com.upiiz.practicaVIII.PracticaVIII.services;

import com.upiiz.practicaVIII.PracticaVIII.entities.Liga;
import com.upiiz.practicaVIII.PracticaVIII.repositories.LigaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigaService {
    private final LigaRepository ligaRepository;
    public LigaService(LigaRepository ligaRepository){ this.ligaRepository = ligaRepository; }
    public List<Liga> getAll(){ return ligaRepository.findAll(); }
    public Liga create(Liga liga){ return ligaRepository.save(liga); }
    public Liga getById(Long id){ return ligaRepository.findById(id).orElseThrow(() -> new RuntimeException("Liga no encontrada")); }
}

