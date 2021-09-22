package model;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class RegVenta implements Serializable{
// NCOD_DOC  NUM_DOC  TIP_DOC  FCHING_DOC  MONT_DOC  OBS_DOC  EST_DOC  NUMPAC ESTVTA
    int id, numpac, idReg;
    double monto;
    String ndoc, tipdoc, obs, estado;
    Date fecha;     

}
