package com.api.envio_rapido.service.security;

import com.api.envio_rapido.config.security.JwtService;
import com.api.envio_rapido.dto.RegisterRequest;
import com.api.envio_rapido.entity.security.Role;
import com.api.envio_rapido.entity.security.Usuario;
import com.api.envio_rapido.repository.security.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public String register(RegisterRequest request) {

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());

        usuario.setSenha(passwordEncoder.encode(request.getSenha()));

        usuario.setRole(Role.ROLE_USER);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return jwtService.generateToken(usuarioSalvo);
    }
}


