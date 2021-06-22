package model;

import java.io.Serializable;
import lombok.Data;
@Data
public class DetVta implements Serializable{
    // TB-VtaDetalle
    String codigoMed,generico, comercial, provAbr, presentacion;
    double precio, subtotal;
    int cantidad;
}
