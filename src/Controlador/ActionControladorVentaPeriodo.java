
package Controlador;

import Modelo.OperacionesVentaPeriodo;
import Modelo.util;
import Reportes.AstractJasperReport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import vista.ReporteVentasPorPeriodo;

/**
 *
 * @author Aldair
 */
public class ActionControladorVentaPeriodo implements ActionListener{
    ReporteVentasPorPeriodo ventanaReporte=new ReporteVentasPorPeriodo();
    OperacionesVentaPeriodo operaciones=new OperacionesVentaPeriodo();
    String fechainicial;
    String fechafinal;
    
    public ActionControladorVentaPeriodo(ReporteVentasPorPeriodo ventanaReporte,OperacionesVentaPeriodo operaciones){
        this.ventanaReporte=ventanaReporte;
        this.operaciones=operaciones;
        this.ventanaReporte.jdtFechaInicial.setDate(new Date());
        
        //escuchadores
        this.ventanaReporte.jButtonGenerarReporte.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ventanaReporte.jButtonGenerarReporte){
            ejecutarReportePeriodo();
        }
        
    }
    
    public boolean ejecutarReportePeriodo(){
        boolean estado=false;
        fechainicial=util.aFechaMYSQL(ventanaReporte.jdtFechaFinal.getDate());
        fechafinal=util.aFechaMYSQL(ventanaReporte.jdtFechaInicial.getDate());
        operaciones.generarReporteVentaPeriodo(fechainicial, fechafinal);
        AstractJasperReport.ejecutarReporte();
        return estado;
    }
}
