package com.example.api_examen.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CiudadDTO {
    private Long id_ciudad;
    @NotBlank(message = "El nombre de la ciudad es obligatorio")
    private String nombre_ciudad;
    @NotBlank(message = "El código de país es obligatorio")
    private String codigo_pais;
}
