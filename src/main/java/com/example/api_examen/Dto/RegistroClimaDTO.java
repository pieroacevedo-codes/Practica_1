package com.example.api_examen.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroClimaDTO {
    private Long id_registro_clima;
    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha_registro;
    @NotNull(message = "La temperatura es obligatoria")
    private Double temperatura;
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
    @NotNull(message = "El id de la ciudad es obligatorio")
    private Long id_ciudad;
}
