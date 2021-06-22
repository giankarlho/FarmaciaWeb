package reports;

import dao.Conexion;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ReporteS extends Conexion {

    private Date now = new Date();
    SimpleDateFormat forma = new SimpleDateFormat("dd/MMM/yyyy");
    
    public static final String REPORTE_IMPRESION_TICKET = "Ticket.jasper";
    
    // Reporte para la vista Medicina
    public void exportarMedicinaPDF(Map parameters) throws JRException, IOException, Exception {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reporte/ListMedicina.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=CatálogoMedicina_(" + forma.format(now) + ").pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("ERROR en exportarPerPDF: " + e.getMessage());
        }
    }
        public JasperPrint generarTicket(BigDecimal nrodoc, String usuario, String fechaActual) throws JRException, ClassNotFoundException {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reports/Ticket.jasper"));                        
            HashMap parameters = new HashMap();
            parameters.put("NCOD_DOC", nrodoc);
            parameters.put("pPersonal", usuario);
            parameters.put("pFecha", fechaActual);    
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());            
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();            
            response.addHeader("Content-disposition", "attachment; filename=TicketVenta_(" + forma.format(now) + ").pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
            return jasperPrint;            
        } catch (Exception e) {                        
            System.out.println("ERROR en JasperPrint generarTicket: " + e.getMessage());
        }
        return null;
    }
}
