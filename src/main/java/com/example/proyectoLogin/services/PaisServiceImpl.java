package com.example.proyectoLogin.services;

import com.example.proyectoLogin.models.domain.Pais;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class PaisServiceImpl implements PaisService {
    private List<Pais> listar;
    public PaisServiceImpl(){
        this.listar = Arrays.asList(
                new Pais(1,"ES", "España"),
                new Pais(2,"MX", "Mexico"),
                new Pais(3,"CL", "Chile"),
                new Pais(4,"AR", "Argentina"),
                new Pais(5,"PE", "Peru"),
                new Pais(6,"CO", "Colombia"),
                new Pais(7,"VE", "Venezuela")
        );
    }
    @Override
    public List<Pais> listar() {
        return listar;
    }

    @Override
    public Pais obtenerPorId(Integer id) {
        Pais resultado  = null;
        for (Pais pais: this.listar){
            if(id==pais.getId()){
                resultado = pais;
                break;
            }
        }
        return resultado;
    }
}
