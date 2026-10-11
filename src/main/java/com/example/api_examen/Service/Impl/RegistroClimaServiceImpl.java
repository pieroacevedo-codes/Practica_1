package com.example.api_examen.Service.Impl;

import com.example.api_examen.Dto.RegistroClimaDTO;
import com.example.api_examen.Dto.ReporteDTO;
import com.example.api_examen.Entity.CiudadEntity;
import com.example.api_examen.Entity.RegistroClimaEntity;
import com.example.api_examen.Repository.CiudadRepository;
import com.example.api_examen.Repository.RegistroClimaRepository;
import com.example.api_examen.Security.UsuarioActual;
import com.example.api_examen.Service.AuditoriaService;
import com.example.api_examen.Service.RegistroClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistroClimaServiceImpl implements RegistroClimaService {

    @Autowired
    private RegistroClimaRepository registroClimaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private AuditoriaService auditoriaService;

    @Autowired
    private UsuarioActual usuarioActual;

    @Override
    public RegistroClimaDTO registrar(RegistroClimaDTO registroClimaDTO) {
        CiudadEntity ciudad = ciudadRepository.findById(registroClimaDTO.getId_ciudad())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La ciudad indicada no existe"));
        RegistroClimaEntity registro = new RegistroClimaEntity();
        registro.setFecha_registro(registroClimaDTO.getFecha_registro());
        registro.setTemperatura(registroClimaDTO.getTemperatura());
        registro.setDescripcion(registroClimaDTO.getDescripcion());
        registro.setCiudad(ciudad);
        registro = registroClimaRepository.save(registro);
        auditoriaService.registrar_alta(AuditoriaServiceImpl.TABLA_REGISTRO_CLIMA, registro.getId_registro_clima(), usuarioActual.id_usuario());
        registroClimaDTO.setId_registro_clima(registro.getId_registro_clima());
        return registroClimaDTO;
    }

    @Override
    public List<ReporteDTO> promedio_por_ciudad(LocalDate desde, LocalDate hasta) {
        return registroClimaRepository.promedio_por_ciudad(desde, hasta);
    }
}
