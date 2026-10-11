package com.example.api_examen.Config;

import com.example.api_examen.Entity.RolEntity;
import com.example.api_examen.Entity.UsuarioEntity;
import com.example.api_examen.Repository.RolRepository;
import com.example.api_examen.Repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Si el script no cargó los roles y usuarios, se crean al arrancar.
 * El password de todos los usuarios es el código de alumno (guardado encriptado).
 */
@Slf4j
@Component
public class DatosIniciales implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${app.codigo-alumno}")
    private String codigo_alumno;

    @Override
    public void run(String... args) {
        Map<String, String> usuarios = Map.of("admin", "ADMIN", "clima", "CLIMA");
        usuarios.forEach((username, nombre_rol) -> {
            RolEntity rol = rolRepository.buscar_por_nombre(nombre_rol).orElseGet(() -> {
                RolEntity nuevo = new RolEntity();
                nuevo.setNombre_rol(nombre_rol);
                return rolRepository.save(nuevo);
            });
            if (usuarioRepository.buscar_por_username(username).isEmpty()) {
                UsuarioEntity usuario = new UsuarioEntity();
                usuario.setUsername_usuario(username);
                usuario.setClave_usuario(passwordEncoder.encode(codigo_alumno));
                usuario.setRol(rol);
                usuarioRepository.save(usuario);
                log.info("Usuario creado: {} ({})", username, nombre_rol);
            }
        });
    }
}
