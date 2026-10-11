package com.example.api_examen.Controller;

import com.example.api_examen.Dto.ReporteDTO;
import com.example.api_examen.Service.RegistroClimaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reporte")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Controlador de reportes", description = "HU04: promedio de temperaturas por ciudad (solo CLIMA)")
public class ReporteController {

    @Autowired
    private RegistroClimaService service;

    @GetMapping
    @Operation(summary = "Promedio de temperatura por ciudad en un rango de fechas (yyyy-MM-dd)")
    public List<ReporteDTO> promedio_por_ciudad(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return service.promedio_por_ciudad(desde, hasta);
    }
}
