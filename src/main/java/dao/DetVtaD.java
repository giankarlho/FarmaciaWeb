package dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.DetVta;
import model.ListVenta;

public class DetVtaD extends Conexion {

    public List<DetVta> listarDetVta(int codigoVta) throws Exception {
        List<DetVta> detalle = new ArrayList();
        DetVta det;
        try {
            CallableStatement ps = this.conectar().prepareCall("{call spDetalleVta(?)}");
            ps.setInt(1, codigoVta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                det = new DetVta();
                det.setCodigoMed(rs.getString("NUMMED"));
                det.setGenerico(rs.getString("NOMGENMED"));
                det.setComercial(rs.getString("NOMCOMMED"));
                det.setPresentacion(rs.getString("PREMED"));
                det.setProvAbr(rs.getString("ABRPROV"));
                det.setCantidad(rs.getInt("CANTV_MED"));
                det.setPrecio(rs.getDouble("PRECMED"));
                det.setSubtotal(rs.getDouble("STOTV_DOC"));
                detalle.add(det);
            }
        } catch (Exception e) {
            System.out.println("Error en dao/DetVtaD/listarDetVta " + e.getMessage());
        }
        return detalle;
    }

    public List<ListVenta> listarVtas() throws Exception {
        String sql = "select NCOD_DOC,NUM_DOC,FCHING_DOC,MONT_DOC,OBS_DOC,CONCAT(NOMPAC, ' ',	APEPAC) as nombre \n" +
                    "from doc_venta dv inner join paciente p on dv.NUMPAC = p.NUMPAC ";
        
//        ArrayList<String> detalle = new ArrayList<>();
        ArrayList<ListVenta> detalle = new ArrayList<>();
        ListVenta det;
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                det = new ListVenta();
                det.setCodDoc(rs.getString("NCOD_DOC"));
                det.setNroDoc(rs.getString("NUM_DOC"));
                det.setFecha(rs.getDate("FCHING_DOC"));
                det.setMonto(rs.getDouble("MONT_DOC"));
                det.setNombre(rs.getString("nombre"));
                det.setObs(rs.getString("OBS_DOC"));
                detalle.add(det);
            }
        } catch (Exception e) {
            System.out.println("Error en dao/DetVtaD/listarVtas " + e.getMessage());
        }
        return detalle;
    }
}
