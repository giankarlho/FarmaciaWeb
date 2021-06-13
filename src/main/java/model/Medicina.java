package model;

import lombok.Data;

@Data
public class Medicina {
//NUMMED  NOMGENMED  NOMCOMMED  COSMED  PRECMED   PREMED  CANTMED OBSMED LUGMED FCHMED  LOTMED ESTMED NUMPROV

    private int idMed;
    private String generico;
    private String comercial;
    private float costo;
    private float precio;
    private String presen;
    private int cantidad;
    private String obs;
    private String lugar;
    private String fecha;
    private String lote;
    private String estado;
    private int idProv;
}
