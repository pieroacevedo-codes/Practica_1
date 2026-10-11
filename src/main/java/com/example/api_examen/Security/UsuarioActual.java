package com.example.api_examen.Security;

import com.example.api_examen.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * Usuario que hace la petición actual, tomado del token por JwtFilter.
 */
@Component
public class UsuarioActual {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Long id_usuario() {
        Authentication autenticacion = SecurityContextHolder.getContext().getAuthentication();
        if (autenticacion == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Falta el token de autenticación");
        }
        return usuarioRepository.buscar_por_username(autenticacion.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "El usuario del token no existe"))
                .getId_usuario();
    }
}
