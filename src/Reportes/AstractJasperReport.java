
package Reportes;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.view.JasperViewer;
import vista.ReporteVentasDiarias;
import vista.ReporteVentasPorPeriodo;
import vista.Ventas;
/**
 *
 * @author Aldair
 */
public abstract class AstractJasperReport {
    private static JasperReport reporte;
    private static JasperPrint reportecreado;
    private static JasperViewer visor;
    static Ventas ventas = new Ventas();
    static ReporteVentasDiarias rvd = new ReporteVentasDiarias();
    static ReporteVentasPorPeriodo rvpp = new ReporteVentasPorPeriodo();
    
    public static void creaReporte(Connection conexion, String ruta,String codigoVenta){
        try{
            //URL in = ventas.getClass().getResource(ruta);
            Map parametro=new HashMap();
            parametro.clear();
            parametro.put("codigoVenta", codigoVenta);
            //reporte =(JasperReport)JRLoader.loadObjectFromFile(ruta);
            reportecreado=JasperFillManager.fillReport(reporte,parametro, conexion);
        }catch(JRException ex){
            ex.printStackTrace();
        }
    }
    public static void creaReporteVentaDiaria(Connection conexion, String ruta,String fechaventa){
        try{
            URL in = rvd.getClass().getResource(ruta);
            Map parametro=new HashMap();
            parametro.clear();
            parametro.put("fechaventa", fechaventa);
            reporte=(JasperReport)JRLoader.loadObject(in);
            reportecreado=JasperFillManager.fillReport(reporte,parametro, conexion);
        }catch(JRException ex){
        }
    }
    
    //reporte de ventas por periodos 
    public static void crearReportePorPeriodo(Connection conexion,String ruta,String fechaI,String fechaF){
        try{
            URL in = rvpp.getClass().getResource(ruta);
            Map parametros=new HashMap();
            parametros.clear();
            parametros.put("fechaI", fechaI);
            parametros.put("fechaF", fechaF);
            reporte=(JasperReport) JRLoader.loadObject(in);
            reportecreado=JasperFillManager.fillReport(reporte,parametros, conexion);
            
        }catch(JRException ex){
            ex.printStackTrace();
        }
    }
    
    public static void ejecutarReporte(){//muestra el visor donde se va mirar el reporte
        visor=new JasperViewer(reportecreado,false);
        visor.setTitle("REPORTES");
        visor.setSize(600,500);
        visor.setLocationRelativeTo(null);
        visor.setVisible(true);
    }
    
    
}
