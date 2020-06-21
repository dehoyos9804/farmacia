/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Reportes.AstractJasperReport;
import java.io.File;

/**
 *
 * @author Aldair
 */
public class OperacionesVentaPeriodo {
    private final conectorBD conector=new conectorBD();
    private File ruta_absoluta;
    
    public OperacionesVentaPeriodo(){
        ruta_absoluta = new File("");
        conector.getconecction();
    }
    
    public void generarReporteVentaPeriodo(String FechaInicial,String FechaFinal){
        try{
            if(conector.conectar()){
                AstractJasperReport.crearReportePorPeriodo(conector.getconecction(),ruta_absoluta.getAbsolutePath() + "src\\Reportes\\ReporteVentasPeriodo.jasper", FechaFinal, FechaFinal);
            }else{
                System.out.println("Error Al Conectarse A BD");
            }
        }catch(Exception e){
            System.err.println("Error..."+e.getMessage());
        }
    }
    
}
