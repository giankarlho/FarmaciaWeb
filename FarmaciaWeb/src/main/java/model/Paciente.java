package model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Paciente implements Serializable {
// NUMPAC NOMPAC  APEPAC   SEXPAC FNPAC  DNIPAC  TELFPAC   EMAILPAC  NUMUBI DIRPAC  GSPAC HCPAC  ESTPAC
    private int NUMPAC;
    private String NOMPAC;
    private String APEPAC;
    private String SEXPAC;
    private String DNIPAC;
    private String TELFPAC;
    private String EMAILPAC;
    private String NUMUBI;
    private String DIRPAC;
    private String GSPAC;
    private String HCPAC;
    private String ESTPAC;
    private String FNPAC;

}