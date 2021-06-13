package controller;

import dao.MedicinaImpl;
import dao.PacienteImpl;
import dao.VentasD;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import lombok.Data;
import model.Medicina;
import model.Personal;
import model.Paciente;
import model.RegVenta;
import model.RegVentaDet;
import model.TempVta;

@Data
@Named(value = "ventasC")
@SessionScoped
public class VentasC implements Serializable {

    Boolean enabled;

    Medicina medicina;  // para el autocomplete de productos y agregar al carrito temporal
    Paciente paciente;  // para el autocomplete de paciente
    RegVenta regventa;  // para el registro de la Transaccional
    RegVentaDet regdetVta;
    VentasD daoVtas;
    TempVta tempVta;    // para obtener los campos de la lista temporal

    double precio = 0.0, monto = 0.0;
    Integer stockMed = 0, cantPed = 1;
    String proveedor = "", presentacion = "", generico = "", comercial = "", cadenaMed = "", cadenaPac = "";

    List<Object> lstProductos = new ArrayList();    // Lista temporal de los productos agregados

    List<TempVta> productos; // Lista temporal de los productos agregados

    MedicinaImpl daoMed;

    public VentasC() {
        medicina = new Medicina();
        paciente = new Paciente();
        regventa = new RegVenta();
        productos = new ArrayList();
        daoVtas = new VentasD();

        regventa.setFecha(GregorianCalendar.getInstance().getTime());
    }

    @PostConstruct
    public void init() {
        daoMed = new MedicinaImpl();
    }

    public void pruebaMensaje() throws Exception {

    }

    public List<TempVta> agregarTmp() throws Exception {
        try {
            tempVta = new TempVta();
            tempVta.setIdMed(daoMed.obtenerCodigoMedicina(cadenaMed));
            tempVta.setGenerico(daoMed.generico);
            tempVta.setComercial(daoMed.comercial);
            tempVta.setPresentacion(daoMed.presentacion);
            tempVta.setProveedor(daoMed.proveedor);
            tempVta.setStockMed(daoMed.stockMed);
            tempVta.setPrecio(daoMed.precio);
            tempVta.setCantPed(cantPed); // esto ya lo tengo en la vista
            tempVta.setSubTotal((double) (tempVta.getPrecio() * tempVta.getCantPed()));
            this.productos.add(tempVta);
            monto += tempVta.getSubTotal();
            
            limpiarCampos();
            for (TempVta temp : productos) {
                System.out.println(temp);
            }
        } catch (Exception e) {
            System.out.println("Error en agregar MedicinaC " + e.getMessage());
        }
        return productos;
    }

    public void limpiarListaTemp() throws Exception {
        productos.clear();
        monto = 0.0;
    }

    public void limpiarCampos() throws Exception {
        cadenaMed = "";
        cantPed = 1;
    }

    public void nuevoRegVta() throws Exception {
        tempVta = new TempVta();
        cadenaPac = "";
        regventa.setNdoc("");
        limpiarCampos();
        productos.clear();
    }

    public void anularTmp() throws Exception {
        limpiarCampos();
        productos.clear();
    }

    public void registar() {
        //  NCOD_DOC(identity)	NUM_DOC		TIP_DOC		FCHING_DOC	MONT_DOC	OBS_DOC	NUMPAC
        try {
            regdetVta = new RegVentaDet();
            regventa.setTipdoc("1"); // ticket
            regventa.setMonto(monto);
            PacienteImpl daoPac = new PacienteImpl();
            regventa.setNumpac(daoPac.obtenerCodigoPaciente(cadenaPac));
            daoVtas.registrarVta(regventa);
            //  NUMMED	NCOD_DOC	CANTV_MED	STOTV_DOC
            regdetVta.setNrodoc(daoVtas.obtenerCodigoVta());
            for (TempVta venta : productos) {
                regdetVta.setNummed(venta.getIdMed());                
                regdetVta.setCant(venta.getCantPed());
                regdetVta.setStotal(venta.getSubTotal());
                daoVtas.registrarVtaDet(regdetVta);
            }
            nuevoRegVta();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Satisfactorio"));
        } catch (Exception e) {

        }
//        this.desactivarBotones();
    }

    public List<String> completeTextPaciente(String query) throws SQLException, Exception {
        PacienteImpl daoPac = new PacienteImpl();
        return daoPac.autocompletePaciente(query);
    }

    public List<String> completeTextMedicina(String query) throws SQLException, Exception {
        MedicinaImpl daoMed = new MedicinaImpl();
        List<String> listDetMedicina;
        listDetMedicina = daoMed.autocompleteMedicina(query);
        return listDetMedicina;
    }

    public void completeDatosMedicina(String query) throws Exception {
        MedicinaImpl dao = new MedicinaImpl();
        dao.obtenerCodigoMedicina(query);
        precio = dao.precio;
        stockMed = dao.stockMed;
        proveedor = dao.proveedor;
        presentacion = dao.presentacion;
        comercial = dao.comercial;
        generico = dao.generico;
    }

    public void activarBotones() {
        enabled = true;
    }

    public void desactivarBotones() {
        enabled = false;
    }
}
