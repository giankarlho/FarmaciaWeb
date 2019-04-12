package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Paciente;

public class PacienteImpl extends Conexion implements IPaciente {

    @Override
    public void registrar(Paciente paciente) throws Exception {
        try {     
            String sql = "insert into paciente"
                    + " (NOMPAC,NOMCOMMED,APEPAC,SEXPAC,FNPAC,DNIPAC,TELFPAC,EMAILPAC,NUMUBI,DIRPAC,GSPAC,HCPAC,ESTPAC,NUMPAC)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, paciente.getNOMPAC());
            ps.setString(2, paciente.getAPEPAC());
            ps.setString(3, paciente.getSEXPAC());
            ps.setString(4, paciente.getFNPAC());
            ps.setString(5, paciente.getDNIPAC());
            ps.setString(6, paciente.getTELFPAC());
            ps.setString(7, paciente.getEMAILPAC());
            ps.setString(8, paciente.getNUMUBI());
            ps.setString(9, paciente.getDIRPAC());
            ps.setString(10, paciente.getGSPAC());
            ps.setString(11, paciente.getESTPAC());
            ps.setInt(12, paciente.getNUMPAC());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrarDao " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void modificar(Paciente paciente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Paciente paciente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Paciente> listarPaciente() throws Exception {
        List<Paciente> pacientes = new ArrayList();
        Paciente pac;
        String sql = "select * from paciente";
//               
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pac = new Paciente();
                pac.setNUMPAC(rs.getInt("NUMPAC"));
                pac.setNOMPAC(rs.getString("NOMPAC"));
                pac.setAPEPAC(rs.getString("APEPAC"));
                pac.setSEXPAC(rs.getString("SEXPAC"));
                pac.setFNPAC(rs.getString("FNPAC"));
                pac.setDNIPAC(rs.getString("DNIPAC")); // hasta aqui funciona
                pac.setTELFPAC(rs.getString("TELFPAC"));
                pac.setEMAILPAC(rs.getString("EMAILPAC"));
                pac.setNUMUBI(rs.getString("NUMUBI"));
                pac.setDIRPAC(rs.getString("DIRPAC"));
                pac.setGSPAC(rs.getString("GSPAC"));
                pac.setHCPAC(rs.getString("HCPAC"));
                pac.setESTPAC(rs.getString("ESTPAC"));
                pacientes.add(pac);
            }
        } catch (Exception e) {
            throw e;
        }
        return pacientes;
    }
    
}
