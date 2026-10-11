package com.example.api_examen.Service.Impl;

import com.example.api_examen.Dto.CiudadDTO;
import com.example.api_examen.Entity.CiudadEntity;
import com.example.api_examen.Repository.CiudadRepository;
import com.example.api_examen.Security.UsuarioActual;
import com.example.api_examen.Service.AuditoriaService;
import com.example.api_examen.Service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadServiceImpl implements CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private AuditoriaService auditoriaService;

    @Autowired
    private UsuarioActual usuarioActual;

    @Override
    public CiudadDTO registrar(CiudadDTO ciudadDTO) {
        CiudadEntity ciudad = new CiudadEntity();
        ciudad.setNombre_ciudad(ciudadDTO.getNombre_ciudad());
        ciudad.setCodigo_pais(ciudadDTO.getCodigo_pais());
        ciudad = ciudadRepository.save(ciudad);
        auditoriaService.registrar_alta(AuditoriaServiceImpl.TABLA_CIUDAD, ciudad.getId_ciudad(), usuarioActual.id_usuario());
        ciudadDTO.setId_ciudad(ciudad.getId_ciudad());
        return ciudadDTO;
    }
}
