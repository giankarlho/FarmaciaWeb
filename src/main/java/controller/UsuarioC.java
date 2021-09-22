package controller;

import dao.UsuarioD;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import model.Usuario;

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
            usuario = dao.login(user, pass);
            if (usuario != null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objetoUsuario",usuario);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/FarmaciaWeb/faces/index.xhtml");
            } else{
                System.out.println("no puedes entrar");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Acceso al Sistema","Usuario y/o contraseña incorrecta"));
            }
            
        } catch (Exception e) {
            System.out.println("Error en el loginC" + e.getMessage());
        }
        
    }

}
