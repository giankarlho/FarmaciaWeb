package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Medicina;

public class MedicinaImpl extends Conexion implements IMedicina {
//NUMMED  NOMGENMED  NOMCOMMED  COSMED  PRECMED   PREMED  CANTMED OBSMED LUGMED FCHMED  LOTMED  ESTMED NUMPROV

    @Override
    public void registrar(Medicina medicina) throws Exception {
        try {
            String sql = "insert into medicina"
                    + " (NOMGENMED,NOMCOMMED,COSMED,PRECMED,PREMED,CANTMED,OBSMED,LUGMED,FCHMED,LOTMED,ESTMED,NUMPROV,NUMMED)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, medicina.getGenerico());
            ps.setString(2, medicina.getComercial());
            ps.setFloat(3, medicina.getCosto());
            ps.setFloat(4, medicina.getPrecio());
            ps.setString(5, medicina.getPresen());
            ps.setInt(6, medicina.getCantidad());
            ps.setString(7, medicina.getObs());
            ps.setString(8, medicina.getLugar());
            ps.setString(9, medicina.getFecha());
            ps.setString(10, medicina.getLote());
            ps.setString(11, medicina.getEstado());
            ps.setInt(12, medicina.getIdProv());
            ps.setInt(13, medicina.getIdMed());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrarDao " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void modificar(Medicina medicina) throws Exception {
        try {
            String sql = "update medicina set NOMGENMED=?,NOMCOMMED=?,COSMED=?,PRECMED=?,PREMED=?,CANTMED=?,OBSMED=?,LUGMED=?,FCHMED=?,"
                    + "LOTMED=?,ESTMED=?,NUMPROV=? where NUMMED=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, medicina.getGenerico());
            ps.setString(2, medicina.getComercial());
            ps.setFloat(3, medicina.getCosto());
            ps.setFloat(4, medicina.getPrecio());
            ps.setString(5, medicina.getPresen());
            ps.setInt(6, medicina.getCantidad());
            ps.setString(7, medicina.getObs());
            ps.setString(8, medicina.getLugar());
            ps.setString(9, medicina.getFecha());
            ps.setString(10, medicina.getLote());
            ps.setString(11, medicina.getEstado());
            ps.setInt(12, medicina.getIdProv());
            ps.setInt(13, medicina.getIdMed());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en modificarDao " + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Medicina medicina) throws Exception {
        try {
            String sql = "delete from medicina where NUMMED=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(0, medicina.getIdMed());
            ps.executeBatch();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en eliminarDao " + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Medicina> listarMedicinas() throws Exception {
        List<Medicina> medicinas = new ArrayList();
        Medicina med;
        String sql = "select * from medicina";
//NUMMED  NOMGENMED  NOMCOMMED  COSMED  PRECMED   PREMED  CANTMED OBSMED LUGMED FCHMED  LOTMED  ESTMED NUMPROV
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                med = new Medicina();
                med.setIdMed(rs.getInt("NUMMED"));
                med.setGenerico(rs.getString("NOMGENMED"));
                med.setComercial(rs.getString("NOMCOMMED"));
                med.setCosto(rs.getFloat("COSMED"));
                med.setPrecio(rs.getFloat("PRECMED"));
                med.setPresen(rs.getString("PREMED")); // hasta aqui funciona
                med.setCosto(rs.getInt("CANTMED"));
                med.setObs(rs.getString("OBSMED"));
                med.setLugar(rs.getString("LUGMED"));
                med.setFecha(rs.getString("FCHMED"));
                med.setLote(rs.getString("LOTMED"));
                med.setEstado(rs.getString("ESTMED"));
                med.setIdProv(rs.getInt("NUMPROV"));
                medicinas.add(med);
            }
        } catch (Exception e) {
            throw e;
        }
        return medicinas;
    }

    @Override
    public Integer buscaCodMedicina() throws Exception {
        int codigo=1;
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery("select max(NUMMED) as NUMMED from medicina");
            while (rs.next()) {
                codigo = rs.getInt("NUMMED") + 1;
            }
        } catch (Exception e) {
            System.out.println("Error en buscarCódigo " + e.getMessage());
            throw e;
        }
        return codigo;
    }

}
