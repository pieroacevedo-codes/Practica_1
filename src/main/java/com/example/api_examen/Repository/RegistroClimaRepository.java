package com.example.api_examen.Repository;

import com.example.api_examen.Dto.ReporteDTO;
import com.example.api_examen.Entity.RegistroClimaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistroClimaRepository extends JpaRepository<RegistroClimaEntity, Long> {

    @Query("SELECT new com.example.api_examen.Dto.ReporteDTO(c.nombre_ciudad, AVG(r.temperatura)) " +
            "FROM RegistroClimaEntity r JOIN r.ciudad c " +
            "WHERE r.fecha_registro BETWEEN :desde AND :hasta " +
            "GROUP BY c.nombre_ciudad ORDER BY c.nombre_ciudad")
    List<ReporteDTO> promedio_por_ciudad(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);
}
