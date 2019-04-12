package dao;

import java.util.List;
import model.Medicina;

public interface IMedicina {
    void registrar(Medicina medicina) throws Exception;
    void modificar(Medicina medicina) throws Exception;
    void eliminar(Medicina medicina) throws Exception;
    List<Medicina> listarMedicinas() throws Exception;
    Integer buscaCodMedicina() throws Exception; //busca el código y suma 1
}
