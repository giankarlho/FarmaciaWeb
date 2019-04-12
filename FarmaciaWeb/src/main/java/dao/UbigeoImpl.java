package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Ubigeo;

public class UbigeoImpl extends Conexion implements IUbigeo {
// NUMUBI DPTOUBI PROVUBI DISTUBI
    @Override
    public List<Ubigeo> listarUbigeo() throws Exception {
        List<Ubigeo> listado = new ArrayList();
        Ubigeo ubi;
        String sql = "select * from ubigeo";
        Statement st = this.conectar().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            ubi = new Ubigeo();
            ubi.setId(rs.getString("NUMUBI"));
            ubi.setDpto(rs.getString("DPTOUBI"));
            ubi.setProv(rs.getString("PROVUBI"));
            ubi.setDist(rs.getString("DISTUBI"));
            listado.add(ubi);
        }
        return listado;
    }
}
