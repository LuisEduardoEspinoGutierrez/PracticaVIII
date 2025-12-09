package com.upiiz.practicaVIII.PracticaVIII.config;

import com.upiiz.practicaVIII.PracticaVIII.entities.Liga;
import com.upiiz.practicaVIII.PracticaVIII.entities.Usuario;
import com.upiiz.practicaVIII.PracticaVIII.repositories.LigaRepository;
import com.upiiz.practicaVIII.PracticaVIII.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final LigaRepository ligaRepository;

    public DataLoader(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, LigaRepository ligaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.ligaRepository = ligaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!usuarioRepository.existsByUsername("admin")) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(List.of("ADMIN", "USER"));
            usuarioRepository.save(admin);
        }

        if (ligaRepository.count() == 0) {
            Liga l = new Liga("Liga MX", "Mexico", "2025-2026");
            ligaRepository.save(l);
        }
    }
}
