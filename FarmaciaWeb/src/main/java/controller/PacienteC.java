package controller;

import dao.PacienteImpl;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Paciente;

@Named(value = "pacienteC")
@SessionScoped
public class PacienteC implements Serializable {

    private Paciente paciente;
    private List<Paciente> listaPaciente;
    PacienteImpl dao;
    private Date fechaFormulario;
    SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");

    public PacienteC() {
        paciente = new Paciente();
        listaPaciente = new ArrayList<>();
        dao = new PacienteImpl();
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(paciente);
            listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(paciente);
            listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            listaPaciente = dao.listarPaciente();
        } catch (Exception e) {
        }
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Paciente> getListaPaciente() {
        return listaPaciente;
    }

    public void setListaPaciente(List<Paciente> listaPaciente) {
        this.listaPaciente = listaPaciente;
    }

    public Date getFechaFormulario() {
        return fechaFormulario;
    }

    public void setFechaFormulario(Date fechaFormulario) {
        this.fechaFormulario = fechaFormulario;
    }

}
