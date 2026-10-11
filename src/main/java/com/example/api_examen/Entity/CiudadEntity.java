package com.example.api_examen.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tm_ciudad", schema = "examen")
public class CiudadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad")
    private Long id_ciudad;

    @Column(name = "nombre_ciudad")
    private String nombre_ciudad;

    @Column(name = "codigo_pais")
    private String codigo_pais;
}
