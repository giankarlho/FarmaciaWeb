package controller;

import dao.MedicinaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Medicina;

@Named(value = "medicinaC")
@SessionScoped
public class MedicinaC implements Serializable {

    private Medicina medicina;
    private List<Medicina> listaMed;
    MedicinaImpl dao;
    private Date fechaFormulario;
    SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");

    public MedicinaC() {
        listaMed = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        medicina = new Medicina();
        dao = new MedicinaImpl();
    }

    

    public void registrar() throws Exception {
        try {
            medicina.setFecha(formateador.format(fechaFormulario));
            medicina.setIdMed(dao.buscaCodMedicina());
            dao.registrar(medicina);
            listarMed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Satisfactorio"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(medicina);
            listarMed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Satisfactoria"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Medicina med) throws Exception {
        try {
            dao.eliminar(med);
            listarMed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminación", "Satisfactoria"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() throws Exception {
        try {
            medicina = new Medicina();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarMed() throws Exception {
        try {
            listaMed = dao.listarTodos();
        } catch (Exception e) {
            throw e;
        }
    }

    //Getter y Setter
    public Date getFechaFormulario() {
        return fechaFormulario;
    }

    public void setFechaFormulario(Date fechaFormulario) {
        this.fechaFormulario = fechaFormulario;
    }

    public Medicina getMedicina() {
        return medicina;
    }

    public void setMedicina(Medicina medicina) {
        this.medicina = medicina;
    }

    public List<Medicina> getListaMed() {
        return listaMed;
    }

    public void setListaMed(List<Medicina> listaMed) {
        this.listaMed = listaMed;
    }
}
