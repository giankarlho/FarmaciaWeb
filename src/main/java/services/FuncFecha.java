package services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.Timer;

public class FuncFecha {
       public static java.util.Date fechaActual() {
        java.util.Date fecha = new java.util.Date();
        return (fecha);
    }

    //con esto se va actualizando lahora en cada segundo
//    public String horaActual() {
//        String hora;
//        int j = 1000;
//        Timer timer;
//        timer = new Timer(j, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                hora = mostrarHora();
//            }
//        });        
//        timer.start();
//    }

    //este es le metodo para mostrar la hora
    public static String mostrarHora() {
        Calendar cal = Calendar.getInstance();
        String hora = cal.get(cal.HOUR_OF_DAY) + ":" + cal.get(cal.MINUTE) + ":" + cal.get(cal.SECOND);
        return hora;
    }    
    
    public static synchronized java.util.Date deStringToDate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaEnviar = null;
        try {
            fechaEnviar = (Date) formatoDelTexto.parse(fecha);
            return fechaEnviar;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }  
}
