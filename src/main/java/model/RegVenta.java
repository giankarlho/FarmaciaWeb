package model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class RegVenta {
// NCOD_DOC  NUM_DOC  TIP_DOC  FCHING_DOC  MONT_DOC  OBS_DOC  EST_DOC  NUMPAC 
    private @Getter @Setter Integer id, numpac, idReg;
    private @Getter @Setter double monto;
    private @Getter @Setter String ndoc, tipdoc, obs, estado;
    private @Getter @Setter Date fecha;      

}
