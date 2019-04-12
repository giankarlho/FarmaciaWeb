package model;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class RegCompra {
    //NCOD_DOC             NUM_DOC        TIP_DOC    FCHING_DOC    MONT_DOC                          OBS_DOC                                            EST_DOC NUMPROV
    private int id;
    private int numprov;
    private String ndoc;
    private String tipdoc;
    private String obs;
    private String estado;
    private Timestamp fCompra;

}
