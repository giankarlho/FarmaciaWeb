package model;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class RegLab {
    // NUMSLAB NROLAB NUMPAC MONTLAB FCHLAB OBSLAB ESTLAB 
    private int numslab;
    private int numpac;
    private String nrolab;
    private String obslab;
    private String estlab;
    private Double montlab;
    private Timestamp fechlab;
    
}
