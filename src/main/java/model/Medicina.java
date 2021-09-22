package model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Medicina implements Serializable{
//NUMMED  NOMGENMED  NOMCOMMED  COSMED  PRECMED   PREMED  CANTMED OBSMED LUGMED FCHMED  LOTMED ESTMED NUMPROV

     Integer idMed;
     String generico;
     String comercial;
     float costo;
     float precio;
     String presen;
     int cantidad;
     String obs;
     String lugar;
     String fecha;
     String lote;
     String estado;
     int idProv;
}
