package model;

import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

@Data
public class Personal implements Serializable {
//    NUMUSU	NOMUSU APEUSU	USUUSU	PWDUSU	DNIUSU	DIRUSU	INGUSU	TELFUSU	FNACUSU	CARGUSU	UBIUSU	TIPUSU
    private int codigo;
    private int tipo;
    private String nombre;
    private String apellido;
    private String dni;
    private String usuario;
    private String pass;
    private String direccion;
    private String telefono;
    private String cargo;
    private String ubigeo;
    private String mail;
    private String sexo;
    private Date ingreso;
    private Date nacimiento;
  
}
