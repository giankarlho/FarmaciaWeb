package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.runtime.logging.Logger;
import model.RegVenta;
import model.RegVentaDet;

public class VentasD extends Conexion {

    public void registrarVta(RegVenta venta) throws Exception {
        try {
            // NCOD_DOC     NUM_DOC	TIP_DOC		FCHING_DOC	MONT_DOC	OBS_DOC     NUMPAC            
//            String sqlVta = "{call spInsertVta}";
            String sqlVta = "insert into doc_venta values (?,?,?,?,?,?)";
            PreparedStatement psVta = this.conectar().prepareStatement(sqlVta);
            psVta.setString(1, venta.getNdoc());
            psVta.setString(2, venta.getTipdoc());
            SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            psVta.setString(3, forma.format(venta.getFecha()));
            psVta.setDouble(4, venta.getMonto());
            psVta.setString(5, venta.getObs());
            psVta.setInt(6, venta.getNumpac());
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
}

