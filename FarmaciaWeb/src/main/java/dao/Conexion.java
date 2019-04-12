package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Conexion {

    public static Connection cnx = null;

    public static Connection conectar() throws Exception {
            String user = "sa";
            String pwd = "@gvs123@";
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost;databaseName=BDHospital";
        try {
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión, revise xfa");            
        }
        return cnx;
    }

    public void cerrar() throws Exception {
        if (cnx != null) {
            if (cnx.isClosed() == false) {
                cnx.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        conectar();
        if(cnx!=null){
            System.out.println("esta abierta, jojolete");
        }else{
            System.out.println("fijate el driver, conexión, etc....monse");
        }
    }

}
