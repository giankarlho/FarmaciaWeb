package reports;

import dao.Conexion;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
}
