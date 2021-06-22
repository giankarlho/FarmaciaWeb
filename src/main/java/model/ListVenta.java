package model;

import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ListVenta {
    String codDoc, nroDoc, obs, nombre;
    double monto;
    Date fecha;
}
