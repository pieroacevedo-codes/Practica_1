package com.example.api_examen.Service;

import com.example.api_examen.Dto.LoginRespuestaDTO;
import com.example.api_examen.Dto.UsuarioDTO;

public interface UsuarioService {

    LoginRespuestaDTO login(UsuarioDTO usuarioDTO);
}
