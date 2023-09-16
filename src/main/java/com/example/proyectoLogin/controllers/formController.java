package com.example.proyectoLogin.controllers;

import com.example.proyectoLogin.editors.NombreMayusculaEditor;
import com.example.proyectoLogin.editors.PaisPropertyEditor;
import com.example.proyectoLogin.editors.RolesEditor;
import com.example.proyectoLogin.models.domain.Pais;
import com.example.proyectoLogin.models.domain.Role;
import com.example.proyectoLogin.services.PaisService;
import com.example.proyectoLogin.services.RoleService;
import com.example.proyectoLogin.validation.UsuarioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes("usuarios")
public class formController {
    @Autowired
    private UsuarioValidador validador;
    @Autowired
    private PaisService paisService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PaisPropertyEditor paisEditor;
    @Autowired
    private RolesEditor rolesEditor;

    public void initBinder(WebDataBinder binder){
        binder.addValidators(validador);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(String.class, "fechaNacimiento", new CustomDateEditor(dateFormat,true));
        binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
        binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
        binder.registerCustomEditor(String.class, "pais", paisEditor);
        binder.registerCustomEditor(String.class, "roles", rolesEditor);
    }

    @ModelAttribute("genero")
    public List<String> genero(){
        return Arrays.asList("Hombre","Mujer");
    }
    @ModelAttribute("listaRoles")
    public List<Role> listaRoles(){
        return this.roleService.listar();
    }
    @ModelAttribute("listaPaises")
    public List<Pais> listarPaises(){
        return paisService.listar();
    }
    @ModelAttribute("listaRolesString")
    public List<String> listaRolesString(){
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        roles.add("ROLE_MODERATOR");
        return roles;
    }
    @ModelAttribute("listaRoleMap")
    public Map<String, String> listaRolesMap(){
        Map<String, String> roles = new HashMap<>();
        roles.put("ROLE_ADMIN","Administrador");
    }

}
