package com.example.proyectoLogin.models.domain;

import com.example.proyectoLogin.validation.IdentificadorRegex;
import com.example.proyectoLogin.validation.Requerido;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.List;

public class Usuario {
    @IdentificadorRegex
    private String indentificador;
    private String nombre;
    @Requerido
    private String apellido;
    @NotEmpty
    private String password;
    @Requerido
    @Email(message = "Correo con formato incorrecto")
    private String email;
    @NotNull
    @Min(5)
    @Max(5000)
    private Integer cuenta;

    @NotNull
    @Past
    private Date fechaNacimiento;
    @NotNull
    private Pais pais;
    @NotEmpty
    private List<Role> roles;


}
