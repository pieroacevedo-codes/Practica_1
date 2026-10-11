package com.example.api_examen.Service;

import com.example.api_examen.Dto.RegistroClimaDTO;
import com.example.api_examen.Dto.ReporteDTO;

import java.time.LocalDate;
import java.util.List;

public interface RegistroClimaService {

    RegistroClimaDTO registrar(RegistroClimaDTO registroClimaDTO);

    List<ReporteDTO> promedio_por_ciudad(LocalDate desde, LocalDate hasta);
}
