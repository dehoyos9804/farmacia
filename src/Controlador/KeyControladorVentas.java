/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.OperacionesVentas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import vista.Ventas;

/**
 *
 * @author Aldair
 */
public class KeyControladorVentas implements KeyListener{
   public int cantidadvender=0;
   public int precio=0;

   Ventas vtVentas=new Ventas();
   OperacionesVentas operaciones =new OperacionesVentas();
   
    public KeyControladorVentas(Ventas vtVentas,OperacionesVentas operaciones) {
        this.vtVentas=vtVentas;
       this.operaciones=operaciones;
       
       this.vtVentas.jtBuscador.addKeyListener(this);
       this.vtVentas.jtCantidadVender.addKeyListener(this);
       this.vtVentas.jtPrecios.addKeyListener(this);
       this.vtVentas.jtTotalPagar.addKeyListener(this);
    }
   
    public void keyTyped(KeyEvent ke) {
        
    }

    public void keyPressed(KeyEvent ke) {
       
    }

    public void keyReleased(KeyEvent e) {
        if(e.getSource()==vtVentas.jtBuscador){
        operaciones.FiltroBuscar(vtVentas.jtBuscador, vtVentas.jcMedicamentos); 
        vtVentas.jcMedicamentos.setPopupVisible(true);
        }
        
        if(e.getSource()==vtVentas.jtCantidadVender){
        cantidadvender=Integer.parseInt(vtVentas.jtCantidadVender.getText());
        precio=Integer.parseInt(vtVentas.jtPrecios.getText());
        int total=cantidadvender*precio;
        vtVentas.jtVAlorNeto.setText(String.valueOf(total)); 
        }
        
        if(e.getSource()==vtVentas.jtPrecios){
        cantidadvender=Integer.parseInt(vtVentas.jtCantidadVender.getText());
        precio=Integer.parseInt(vtVentas.jtPrecios.getText());
        int total=cantidadvender*precio;
        vtVentas.jtVAlorNeto.setText(String.valueOf(total));
        }
        
        if(e.getSource()==vtVentas.jtTotalPagar){
              double totalpagar=Double.parseDouble(vtVentas.jtTotalPagar.getText().trim());
              double subtotal=Double.parseDouble(vtVentas.jtsubtotal.getText().trim());
              double totalneto=(totalpagar-subtotal);
              vtVentas.jtTotalDevolver.setText(String.valueOf(totalneto));
        }
    }
    
}
