package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.runtime.logging.Logger;
import model.DetVta;
import model.ListVenta;
import model.RegVenta;
import model.RegVentaDet;

public class VentasD extends Conexion {

    public void registrarVta(RegVenta venta) throws Exception {
        // NCOD_DOC     NUM_DOC	TIP_DOC		FCHING_DOC	MONT_DOC	OBS_DOC     NUMPAC            
        try {      
//            String sqlVta = "insert into doc_venta values (?,?,?,?,?,?,?)";
//            PreparedStatement ps = this.conectar().prepareStatement(sqlVta);
            CallableStatement ps = this.conectar().prepareCall("{call spInsertVta(?,?,?,?,?,?,?)}");
            ps.setString(1, venta.getNdoc());
            ps.setString(2, venta.getTipdoc());
            SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            ps.setString(3, forma.format(venta.getFecha()));        // model/fecha tipo Date
            ps.setDouble(4, venta.getMonto());
            ps.setString(5, venta.getObs());
            ps.setInt(6, venta.getNumpac());
            ps.setString(7, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en VentasD/registrarVta " + e.getMessage());
        }
    }

    public void registrarVtaDet(RegVentaDet detVenta) throws Exception {
        // NUMMED       NCOD_DOC	CANTV_MED	STOTV_DOC                
        try {
    //        String sqlDetVta = "insert into detventa values (?,?,?,?)";
    //        PreparedStatement ps = this.conectar().prepareStatement(sqlDetVta);
            CallableStatement ps = this.conectar().prepareCall("{call spInsertDetVta(?,?,?)}");
            ps.setInt(1, detVenta.getNummed());            
            ps.setInt(2, detVenta.getCant());
            ps.setDouble(3, detVenta.getStotal());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al VentasD/registrarVtaDet " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public Integer obtenerCodigoVta() throws Exception {
        int codigoVta = 0;
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery("select max(NCOD_DOC) as maxCode from doc_venta");
            while (rs.next()) {
                codigoVta = rs.getInt("maxCode");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en getCodigoVta");
        }
        return codigoVta;
    }

    public String generarTicket(String año, Integer tipo, String stipo, Boolean cons) throws Exception {
        String nrodoc = "";
        int codigo = 0;
        Statement st = this.conectar().createStatement();
        String sql = "";
        if (cons == true) {
            sql = "select RIGHT(FACSER,6) AS NRO from SERIE where SUBSTRING(FACSER,4,4)='" + año + "' AND TIPSER=" + tipo;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next() == true) {
                codigo = Integer.parseInt(rs.getString("NRO"));
                codigo++;
                if (Integer.toString(codigo).length() == 1) {
                    nrodoc = stipo + año + "-00000" + codigo;
                } else if (Integer.toString(codigo).length() == 2) {
                    nrodoc = stipo + año + "-0000" + codigo;
                } else if (Integer.toString(codigo).length() == 3) {
                    nrodoc = stipo + año + "-000" + codigo;
                } else if (Integer.toString(codigo).length() == 4) {
                    nrodoc = stipo + año + "-00" + codigo;
                } else if (Integer.toString(codigo).length() == 5) {
                    nrodoc = stipo + año + "-0" + codigo;
                } else if (Integer.toString(codigo).length() == 6) { // hasta la venta "FAR2017-999999"
                    nrodoc = stipo + año + "-" + codigo;
                }
                st.close();
                rs.close();
            } else {
                nrodoc = stipo + año + "-000001";
            }
        } else {
            // Esto sucede cuando sólo se tenga un sólo registro
            sql = "select FACSER from SERIE where TIPSER=" + tipo;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next() == true) {
                nrodoc = rs.getString("FACSER");
            }
        }
        return nrodoc;
    }
}
