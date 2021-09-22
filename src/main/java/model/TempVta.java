package model;

import java.io.Serializable;
import lombok.Data;

@Data
public class TempVta implements Serializable {
    int idMed,cantPed,stockMed;
    double precio,subTotal;
    String comercial,presentacion, proveedor,generico;    
}
