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
        try {
            // NCOD_DOC     NUM_DOC	TIP_DOC		FCHING_DOC	MONT_DOC	OBS_DOC     NUMPAC            
//            String sqlVta = "{call spInsertVta}";
            String sqlVta = "insert into doc_venta values (?,?,?,?,?,?,?)";
            PreparedStatement psVta = this.conectar().prepareStatement(sqlVta);
            psVta.setString(1, venta.getNdoc());
            psVta.setString(2, venta.getTipdoc());
            SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            psVta.setString(3, forma.format(venta.getFecha()));
            psVta.setDouble(4, venta.getMonto());
            psVta.setString(5, venta.getObs());
            psVta.setInt(6, venta.getNumpac());
            psVta.setString(7, "A");
            psVta.executeUpdate();
            psVta.close();
        } catch (Exception e) {
            System.out.println("Error en registrarDao " + e.getMessage());
        }
    }

    public void registrarVtaDet(RegVentaDet detVenta) throws Exception {
        // NUMMED       NCOD_DOC	CANTV_MED	STOTV_DOC
        //            String sqlDetVta = "{call spInsertDetVta}";            
        String sqlDetVta = "insert into detventa values (?,?,?,?)";
        try {
            PreparedStatement psDetVta = this.conectar().prepareStatement(sqlDetVta);
            psDetVta.setInt(1, detVenta.getNummed());
            psDetVta.setInt(2, detVenta.getNrodoc());
            psDetVta.setInt(3, detVenta.getCant());
            psDetVta.setDouble(4, detVenta.getStotal());
            psDetVta.executeUpdate();
            psDetVta.close();
        } catch (Exception e) {
            System.out.println("Error al registrarVtaDet " + e.getMessage());
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
