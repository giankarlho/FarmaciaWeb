package controller;

import dao.UsuarioD;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import model.Usuario;
import services.Encriptar;

@Data
@Named(value = "usuarioC")
@SessionScoped
public class UsuarioC implements Serializable {

    Usuario usuario;
    String user;
    String pass;

    public UsuarioC() {
        usuario = new Usuario();
    }

    public void login() {
        UsuarioD dao;
        try {
            dao = new UsuarioD();
            pass = Encriptar.encriptar(pass);
            usuario = dao.login2(user, pass);            
            if (usuario != null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objetoUsuario",usuario);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/FarmaciaWeb/faces/Principal.xhtml");
            } else{
                System.out.println("no puedes entrar");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Acceso al Sistema","Usuario y/o contraseña incorrecta"));
            }
            
        } catch (Exception e) {
            System.out.println("Error en el loginC" + e.getMessage());
        }
        
    }
    
     // Obtener el objeto de la sesión activa
    public static Usuario obtenerObjetoSesion() {
        return (Usuario) FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("objetoUsuario");
    }
 
    // Si la sesión no está iniciada no permitirá entrar a otra vista de la aplicación
    public void seguridadSesion() throws IOException {
        if (obtenerObjetoSesion() == null) {
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect("/HospitalSec2");
        }
    }

    // Cerrar y limpiar la sesión y direccionar al xhtml inicial del proyecto
    public void cerrarSesion() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/HospitalSec2");
    }

    // Si la sesión está activa se redirecciona a la vista principal
    public void seguridadLogin() throws IOException {
        Usuario us = obtenerObjetoSesion();
        if (us != null) {
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect("/HospitalSec2/faces/index.xhtml");
        }
    }

}
