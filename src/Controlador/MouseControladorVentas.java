/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.OperacionesVentas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.Ventas;

/**
 *
 * @author Aldair
 */
public class MouseControladorVentas implements MouseListener {

    Ventas vtVentas=new Ventas();
    OperacionesVentas operaciones=new OperacionesVentas(); 
    
    public MouseControladorVentas(Ventas vtVentas){
        this.vtVentas=vtVentas;
        
        //escuchador
        this.vtVentas.jTableVendedores.addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent me) {
        //Se llama cuando se oprime y se suelta un botón en el mouse.
        
        int variable=vtVentas.jTableVendedores.getSelectedRow();
        String codusuario=vtVentas.jTableVendedores.getValueAt(variable, 0).toString();
        operaciones.buscarImagenVenta(codusuario, vtVentas.lblFoto);
        vtVentas.jtCc.setText(vtVentas.jTableVendedores.getValueAt(variable, 0).toString());
        vtVentas.jtNombre.setText(vtVentas.jTableVendedores.getValueAt(variable, 1).toString());
        vtVentas.jtApellidos.setText(vtVentas.jTableVendedores.getValueAt(variable, 2).toString());
        vtVentas.jtCorreoElectronico.setText(vtVentas.jTableVendedores.getValueAt(variable, 3).toString());
    }

    @Override
    public void mousePressed(MouseEvent me) {
       //es llamado cuando se oprime un botón en el Mouse
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //Ocurre cuando se suelta un botón en el Mouse.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //Ocurre cuando el cursor entra dentro de los límites del componente.
    }

    @Override
    public void mouseExited(MouseEvent me) {
         //Ocurre cuando el cursor sale dentro de los límites del componente.
    }
    
}
