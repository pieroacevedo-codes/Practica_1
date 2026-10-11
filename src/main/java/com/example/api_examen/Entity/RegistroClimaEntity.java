package com.example.api_examen.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tm_registro_clima", schema = "examen")
public class RegistroClimaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_clima")
    private Long id_registro_clima;

    @Column(name = "fecha_registro")
    private LocalDate fecha_registro;

    @Column(name = "temperatura")
    private Double temperatura;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    private CiudadEntity ciudad;
}
