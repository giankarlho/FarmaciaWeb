package model;

import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

@Data
public class Personal implements Serializable {
//    NUMUSU	NOMUSU APEUSU	USUUSU	PWDUSU	DNIUSU	DIRUSU	INGUSU	TELFUSU	FNACUSU	CARGUSU	UBIUSU	TIPUSU
     int codigo;
     int tipo;
     String nombre;
     String apellido;
     String dni;
     String usuario;
     String pass;
     String direccion;
     String telefono;
     String cargo;
     String ubigeo;
     String mail;
     String sexo;
     Date ingreso;
     Date nacimiento;
  
}
