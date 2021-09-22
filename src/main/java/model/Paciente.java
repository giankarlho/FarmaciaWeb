package model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Paciente implements Serializable {
// NUMPAC NOMPAC  APEPAC   SEXPAC FNPAC  DNIPAC  TELFPAC   EMAILPAC  NUMUBI DIRPAC  GSPAC HCPAC  ESTPAC
     int NUMPAC;
     String NOMPAC;
     String APEPAC;
     String SEXPAC;
     String DNIPAC;
     String TELFPAC;
     String EMAILPAC;
     String NUMUBI;
     String DIRPAC;
     String GSPAC;
     String HCPAC;
     String ESTPAC;
     String FNPAC;

}