package model;

import lombok.Data;

@Data
public class TempVta {
    Integer idMed,cantPed,stockMed;
    double precio,subTotal;
    String comercial,presentacion, proveedor,generico;    
}
