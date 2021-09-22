package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Usuario;

public class UsuarioD extends Conexion {

    public Usuario login(String user, String pass) throws Exception{
        Usuario usuario = null;
        String sql = "select NOMUSU,USUUSU, PWDUSU,LEVUSU from usuario where usuusu=? and pwdusu=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1,user);
            ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                usuario = new Usuario();
                usuario.setUser(user);
                usuario.setPass(pass);
                usuario.setNombre(rs.getString("nomusu"));
                usuario.setLevel(rs.getInt("levusu"));
            }
            ps.close();
            rs.close();
            return usuario;
        } catch (Exception e) {
            System.out.println("Errorr en uusarioD");
            throw e;
        }
    } 
    
}
