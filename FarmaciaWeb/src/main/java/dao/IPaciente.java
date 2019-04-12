package dao;

import java.util.List;
import model.Paciente;

public interface IPaciente {

    void registrar(Paciente paciente) throws Exception;

    void modificar(Paciente paciente) throws Exception;

    void eliminar(Paciente paciente) throws Exception;

    List<Paciente> listarPaciente() throws Exception;
}
