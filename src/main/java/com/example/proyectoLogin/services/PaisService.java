package com.example.proyectoLogin.services;

import com.example.proyectoLogin.models.domain.Pais;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PaisService {
    public List<Pais> listar();
    public Pais obtenerPorId(Integer id);
}
