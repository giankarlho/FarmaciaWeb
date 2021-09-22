package model;

import java.io.Serializable;
import lombok.Data;

@Data
public class RegCompraDet implements Serializable{
        // NUMMED  NCOD_DOC  CANTV_MED   STOTV_DOC
     int nummed;
     int cant;
     int nrodoc;
     Double stotal;    
     Float costo;
    
}

