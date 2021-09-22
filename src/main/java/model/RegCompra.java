package model;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class RegCompra implements Serializable{
    //NCOD_DOC             NUM_DOC        TIP_DOC    FCHING_DOC    MONT_DOC                          OBS_DOC                                            EST_DOC NUMPROV
     int id;
     int numprov;
     String ndoc;
     String tipdoc;
     String obs;
     String estado;
     Timestamp fCompra;

}
