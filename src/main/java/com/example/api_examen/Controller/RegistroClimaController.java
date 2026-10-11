package com.example.api_examen.Controller;

import com.example.api_examen.Dto.RegistroClimaDTO;
import com.example.api_examen.Service.RegistroClimaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperatura")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Controlador de temperaturas", description = "HU03: registro de temperaturas por ciudad (solo ADMIN)")
public class RegistroClimaController {

    @Autowired
    private RegistroClimaService service;

    @PostMapping
    @Operation(summary = "Registrar una temperatura de una ciudad existente")
    public RegistroClimaDTO registrar(@Valid @RequestBody RegistroClimaDTO registroClimaDTO) {
        return service.registrar(registroClimaDTO);
    }
}
