package controller;

import dao.PacienteImpl;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;
import model.Paciente;

@Named(value = "pacienteC")
@SessionScoped
@Data
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
            listaPaciente = dao.listarTodos();
            System.out.println("listado" + listaPaciente);
        } catch (Exception e) {
            System.out.println("Error en PacienteC/listar: " + e.getMessage());
        }
    }
    
}
