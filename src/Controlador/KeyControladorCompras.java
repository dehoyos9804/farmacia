
package Controlador;

import Modelo.OperacionesCompras;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import vista.Compras;

/**
 *
 * @author Aldair
 */
public class KeyControladorCompras implements KeyListener{
    Compras vtCompras=new Compras();
    OperacionesCompras operaciones=new OperacionesCompras();
    
    public KeyControladorCompras(Compras vtCompras,OperacionesCompras operaciones){
        this.vtCompras=vtCompras;
        this.operaciones=operaciones;
        
        this.vtCompras.txtCantidadCompra.addKeyListener(this);
        this.vtCompras.txtPrecioCompra.addKeyListener(this);
        this.vtCompras.txtBuscador.addKeyListener(this);
        this.vtCompras.txtBuscarProveedor.addKeyListener(this);
        
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
       if(e.getSource()==vtCompras.txtCantidadCompra){
           int cantidadvender=0;
           int precio=0;
           cantidadvender=Integer.parseInt(vtCompras.txtCantidadCompra.getText());
           precio=Integer.parseInt(vtCompras.txtPrecioCompra.getText());
           int total=cantidadvender*precio;
           vtCompras.txtValorNeto.setText(String.valueOf(total)); 
       }
       
       if(e.getSource()==vtCompras.txtPrecioCompra){
           int cantidadvender=0;
           int precio=0;
           cantidadvender=Integer.parseInt(vtCompras.txtCantidadCompra.getText());
           precio=Integer.parseInt(vtCompras.txtPrecioCompra.getText());
           int total=cantidadvender*precio;
           vtCompras.txtValorNeto.setText(String.valueOf(total));  
       }
       
       if(e.getSource()==vtCompras.txtBuscador){
           operaciones.filtroBuscar(vtCompras.jcMedicamentos, vtCompras.txtBuscador);
           vtCompras.jcMedicamentos.setPopupVisible(true);
       }
       
       if(e.getSource()==vtCompras.txtBuscarProveedor){
           operaciones.filtroBuscarProveedor(vtCompras.jComboBox1, vtCompras.txtBuscarProveedor);
           vtCompras.jComboBox1.setPopupVisible(true);
       }
    }
    
}
