package model;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class RegLab implements Serializable {
    // NUMSLAB NROLAB NUMPAC MONTLAB FCHLAB OBSLAB ESTLAB 
     int numslab;
     int numpac;
     String nrolab;
     String obslab;
     String estlab;
     Double montlab;
     Timestamp fechlab;
    
}
