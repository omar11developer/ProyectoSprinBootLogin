package com.example.proyectoLogin.editors;

import com.example.proyectoLogin.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {
    @Autowired
    private PaisService service;
    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        try {
            Integer id = Integer.parseInt(text);
            setValue(service.obtenerPorId(id));
        }catch (NumberFormatException e){
            setValue(null);
        }
    }
}
