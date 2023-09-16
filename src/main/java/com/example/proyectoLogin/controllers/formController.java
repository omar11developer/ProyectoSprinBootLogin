package com.example.proyectoLogin.controllers;

import com.example.proyectoLogin.editors.NombreMayusculaEditor;
import com.example.proyectoLogin.editors.PaisPropertyEditor;
import com.example.proyectoLogin.editors.RolesEditor;
import com.example.proyectoLogin.models.domain.Pais;
import com.example.proyectoLogin.models.domain.Role;
import com.example.proyectoLogin.models.domain.Usuario;
import com.example.proyectoLogin.services.PaisService;
import com.example.proyectoLogin.services.RoleService;
import com.example.proyectoLogin.validation.UsuarioValidador;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

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
        roles.put("ROLE_USER","Usuario");
        roles.put("ROLE_MODERATOR","Moderador");

        return roles;
    }
    @ModelAttribute("paises")
    public List<String> paises(){
        return Arrays.asList("España","Mexico","Chile","Argentina","Peru","Colombia","Venezuela");
    }
    @ModelAttribute("paisMap")
    public Map<String, String> paisesMap(){
        Map<String, String> paises = new HashMap<>();
        paises.put("ES","España");
        paises.put("MX","Mexico");
        paises.put("CL","Chile");
        paises.put("AR","Argentina");
        paises.put("PE","Peru");
        paises.put("CO","Colombia");
        paises.put("VE","Venezuela");
        return paises;
    }

    @GetMapping("/form")
    public String form(Model model){
        Usuario usuario = new Usuario();
        usuario.setNombre("Jhon");
        usuario.setApellido("Doe");
        usuario.setIndentificador("123.456.789-K");
        usuario.setHabilitar(true);
        usuario.setValorSecreto("Algun valor secreto ****");
        usuario.setPais(new Pais(3,"CL","Chile"));
        usuario.setRoles(Arrays.asList(new Role(2,"Usuario","ROLE_USER")));
        model.addAttribute("titulo", "Formulario usuario");
        model.addAttribute("usuario", usuario);
        return "form";
    }
    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("titulo", "Resultado form");
        }
        return "redirect:/ver";
    }
    @GetMapping("/ver")
    public String ver(@SessionAttribute(name="usuario",required =false) Usuario usuario, Model model, SessionStatus status){
        if(usuario == null){
            return "redirect:/form";
        }
        model.addAttribute("titulo","Resultado form");
        status.setComplete();
        return "resultado";
    }
}
