package com.example.proyectoLogin.services;

import com.example.proyectoLogin.models.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoleService {
    public List<Role> listar();
    public Role obtenerPorId(Integer id);
}
