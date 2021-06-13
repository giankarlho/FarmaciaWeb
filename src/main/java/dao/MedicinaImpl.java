package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Medicina;

public class MedicinaImpl extends Conexion implements ICRUD<Medicina> {
//NUMMED  NOMGENMED  NOMCOMMED  COSMED  PRECMED   PREMED  CANTMED OBSMED LUGMED FCHMED  LOTMED  ESTMED NUMPROV

    @Override
    public void registrar(Medicina medicina) throws Exception {
        try {
            String sql = "insert into medicina"
                    + " (NOMGENMED,NOMCOMMED,COSMED,PRECMED,PREMED,CANTMED,OBSMED,LUGMED,FCHMED,LOTMED,NUMPROV,NUMMED)"
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
            ps.setInt(11, medicina.getIdProv());
            ps.setInt(12, medicina.getIdMed());
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

    public Integer buscaCodMedicina() throws Exception {
        int codigo = 1;
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

    @Override
    public void cambiarEstado(Medicina obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medicina> listarTodos() throws Exception {
        List<Medicina> medicinas = new ArrayList();
        Medicina med;
        String sql = "select * from medicina";
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
            System.out.println("Error en listarTodos MedicinaImpl " + e.getMessage());
        }
        return medicinas;
    }

    @Override
    public List<Medicina> listarTodos(char estado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> autocompleteMedicina(String consulta) throws SQLException, Exception {
        List<String> lista = new ArrayList<>();
//        String sql = "select CONCAT(NOMGENMED, ' ',NOMCOMMED, ' ', PREMED, ' ','Disp.',CANTMED,' s/',PRECMED,' ',ABRPROV) as producto \n"
//                + "from medicina m inner join proveedor p on m.NUMPROV =p.NUMPROV \n"
//                + "where cantmed>0 and estmed='A' and NOMGENMED like ?";
//        String sql = "{call spAutoCompletMed(?)}";
        try {
//            PreparedStatement ps = this.conectar().prepareStatement(sql);
            CallableStatement ps = this.conectar().prepareCall("{call spAutoCompletMed(?)}");
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("producto"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteMedicina" + e.getMessage());
        }
        return lista;
    }

    public Double precio=0.0;
    public Integer stockMed = 0;
    public String proveedor="", presentacion="",generico="", comercial = "";

    public Integer obtenerCodigoMedicina(String cadenaUbi) throws SQLException, Exception {
//        String sql = "select NUMMED, NOMCOMMED,PREMED, PRECMED,CANTMED,NOMPROV \n"
//                + "from medicina m inner join proveedor p on m.NUMPROV = p.NUMPROV \n"
//                + "WHERE CONCAT(NOMGENMED, ' ',NOMCOMMED, ' ', PREMED, ' ','Disp.',CANTMED,' s/',PRECMED,' ',ABRPROV)= ?";
        Integer codigoMedicina = 0;
        try {
            CallableStatement ps = this.conectar().prepareCall("{call spDatosAutoCompletMed(?)}");
            ps.setString(1, cadenaUbi);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                codigoMedicina = rs.getInt("NUMMED");
                precio = rs.getDouble("PRECMED");
                stockMed = rs.getInt("CANTMED");
                proveedor = rs.getString("NOMPROV");
                presentacion = rs.getString("PREMED");
                comercial = rs.getString("NOMCOMMED");
                generico = rs.getString("NOMGENMED");
            }            
        } catch (Exception e) {
            System.out.println("Error en obtenerCodigoMedicina " + e.getMessage());            
        }
        return codigoMedicina;
    }
}
