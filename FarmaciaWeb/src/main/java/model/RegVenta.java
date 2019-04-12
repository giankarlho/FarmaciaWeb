package model;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class RegVenta {
// NCOD_DOC  NUM_DOC  TIP_DOC  FCHING_DOC  MONT_DOC  OBS_DOC  EST_DOC  NUMPAC 
    private double monto;
    private int id;
    private int numpac;
    private String ndoc;
    private String tipdoc;
    private String obs;
    private String estado;
    private Timestamp fVenta;   

}
