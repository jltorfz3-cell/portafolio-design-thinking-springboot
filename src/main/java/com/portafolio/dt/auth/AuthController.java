package com.portafolio.dt.auth;

import com.portafolio.dt.model.Usuario;
import com.portafolio.dt.model.Rol;
import com.portafolio.dt.repository.UsuarioRepository;
import com.portafolio.dt.repository.RolRepository;
import com.portafolio.dt.security.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.OffsetDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager am;
    private final JwtService jwt;
    private final UsuarioRepository repo;
    private final RolRepository rolRepo;
    private final BCryptPasswordEncoder enc;

    public AuthController(AuthenticationManager am, JwtService jwt, UsuarioRepository repo,
                          RolRepository rolRepo, BCryptPasswordEncoder enc) {
        this.am = am; this.jwt = jwt; this.repo = repo; this.rolRepo = rolRepo; this.enc = enc;
    }

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Login x) {
        am.authenticate(new UsernamePasswordAuthenticationToken(x.username(), x.password()));
        Usuario u = repo.findByUsername(x.username()).orElseThrow();
        u.setUltimoAcceso(OffsetDateTime.now());
        repo.save(u);
        return Map.of(
            "token", jwt.generate(u.getUsername()),
            "username", u.getUsername(),
            "nombres", u.getNombres(),
            "apellidos", u.getApellidos(),
            "roles", u.getRoles().stream().map(Rol::getNombre).toList()
        );
    }

    @GetMapping("/me")
    public Usuario me(org.springframework.security.core.Authentication a) {
        return repo.findByUsername(a.getName()).orElseThrow();
    }

    @PostMapping("/register")
    public Usuario register(@RequestBody Register x) {
        if (repo.findByUsername(x.username()).isPresent())
            throw new IllegalArgumentException("Username ya existe");

        Usuario u = new Usuario();
        u.setUsername(x.username());
        u.setEmail(x.email());
        u.setPasswordHash(enc.encode(x.password()));
        u.setNombres(x.nombres());
        u.setApellidos(x.apellidos());
        u.setActivo(true);

        // Registro público = ESTUDIANTE por defecto.
        rolRepo.findByNombre("ESTUDIANTE").ifPresent(r -> u.getRoles().add(r));
        return repo.save(u);
    }

    public record Login(String username, String password) {}
    public record Register(String username, String email, String password,
                           String nombres, String apellidos) {}
}
