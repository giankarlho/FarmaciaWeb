package services;

import dao.Conexion;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import static net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream;
import net.sf.jasperreports.engine.JasperFillManager;
import static net.sf.jasperreports.engine.JasperFillManager.fillReport;
import net.sf.jasperreports.engine.JasperPrint;

public class ReporteS extends Conexion {

    private Date now = new Date();
    SimpleDateFormat forma = new SimpleDateFormat("dd/MMM/yyyy");

    public static final String REPORTE_IMPRESION_TICKET = "Ticket.jasper";

    // Reporte para la vista Medicina
    public void listadoMedicinas(Map parameters) throws JRException, IOException, Exception {
        try {
            File jasper = new File(getCurrentInstance().getExternalContext().getRealPath("./reports/ListMedicina.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, conectar());
            HttpServletResponse response = (HttpServletResponse) getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=CatálogoMedicina_(" + forma.format(now) + ").pdf");
            ServletOutputStream stream = response.getOutputStream();
            exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("ERROR en listadoMedicinaPDF: " + e.getMessage());
        }
    }
    
     public void listadoPacientes(Map parameters) throws JRException, IOException, Exception {
        try {
            File jasper = new File(getCurrentInstance().getExternalContext().getRealPath("./reports/ListPacientes.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, conectar());
            HttpServletResponse response = (HttpServletResponse) getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=ListadoPacientes_(" + forma.format(now) + ").pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("ERROR en listadoPacientes: " + e.getMessage());
        }
    }

    public JasperPrint generarTicket(int nrodoc, String usuario, String fechaActual) throws JRException, ClassNotFoundException {
        try {
            File jasper = new File(getCurrentInstance().getExternalContext().getRealPath("./reports/Ticket.jasper"));
            HashMap parameters = new HashMap();
            parameters.put("NCOD_DOC", BigDecimal.valueOf(nrodoc));
            parameters.put("pPersonal", usuario);
            parameters.put("pFecha", fechaActual);
            System.out.println("BigDecimal.valueOf(nrodoc) " + BigDecimal.valueOf(nrodoc));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, conectar());
            HttpServletResponse response = (HttpServletResponse) getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=TicketVta_(" + forma.format(now) + ").pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            getCurrentInstance().responseComplete();
            return jasperPrint;
        } catch (Exception e) {
            System.out.println("ERROR en JasperPrint generarTicket: " + e.getMessage());
        }
        return null;
    }
    
    public void exportarPDFGlobal(Map parameters, String url, String nomPDF) throws JRException, IOException, Exception {        
        try {
            File jasper = new File(getCurrentInstance().getExternalContext().getRealPath("./reports/" + url + ""));
            JasperPrint jasperPrint = fillReport(jasper.getPath(), parameters, conectar());
            HttpServletResponse response = (HttpServletResponse) getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=" + nomPDF + "");
            try ( ServletOutputStream stream = response.getOutputStream())
            {
                exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
            System.out.println("Error en generar Reporte Servicio: " + e);
        }
    }
    
}
