package controller;

import dao.DetVtaD;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import model.DetVta;
import model.ListVenta;
import org.primefaces.event.SelectEvent;

@Named(value = "detVentaC")
@SessionScoped
@Data
public class DetVentaC implements Serializable {

    DetVtaD daoDetVta;
    DetVta modelDetVta;
    List<ListVenta> lstVenta;
    List<DetVta> lstDetVenta;
    List<ListVenta> selectedVta;
    double total =0.0;

    public DetVentaC() {
        lstVenta = new ArrayList<>();        
        daoDetVta = new DetVtaD();
        modelDetVta = new DetVta();
    }

    public void listarVta() throws Exception {
        try {
            lstVenta = daoDetVta.listarVtas();
        } catch (Exception e) {
            System.out.println("Error en controller/listarVta " + e.getMessage());
        }
        
//        System.out.println("ListVta");
//        for (ListVenta vta : lstVenta) {
//            System.out.println(vta.toString());
//        }
    }

    public void listarDetVta(int codigo) throws Exception {        
        try {
            lstDetVenta = daoDetVta.listarDetVta(codigo);
            for (DetVta det: lstDetVenta){
                total += det.getSubtotal();
            }
        } catch (Exception e) {
            System.out.println("Error en controller/listarDetVta " + e.getMessage());
        }
        
//        System.out.println("lstDetVenta");
//        for (DetVta vta : lstDetVenta) {
//            System.out.println(vta.toString());
//        }
//        Iterator<DetVta> nombreIterator = lstDetVenta.iterator();
//        while (nombreIterator.hasNext()) {
//            DetVta elemento = nombreIterator.next();
//            System.out.print(elemento.toString() + " / ");
//        }
    }

    public void onRowSelect(SelectEvent event) throws Exception {
        ListVenta vta = (ListVenta) event.getObject();        
        lstDetVenta = daoDetVta.listarDetVta(Integer.parseInt(vta.getCodDoc()));
    }    

}
