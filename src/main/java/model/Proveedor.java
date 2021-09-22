package model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Proveedor implements Serializable{

     int id;
     String razon;
     String ruc;
     String dir;
     String tipo;
     String telf;
     String contacto;
     String mail;
     String abre;
     String ubigeo;
     String comercial;

}
