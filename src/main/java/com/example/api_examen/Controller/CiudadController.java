package com.example.api_examen.Controller;

import com.example.api_examen.Dto.CiudadDTO;
import com.example.api_examen.Service.CiudadService;
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
@RequestMapping("/ciudad")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Controlador de ciudades", description = "HU02: registro de ciudades (solo ADMIN)")
public class CiudadController {

    @Autowired
    private CiudadService service;

    @PostMapping
    @Operation(summary = "Registrar una ciudad")
    public CiudadDTO registrar(@Valid @RequestBody CiudadDTO ciudadDTO) {
        return service.registrar(ciudadDTO);
    }
}
