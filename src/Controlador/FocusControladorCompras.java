
package Controlador;

import Modelo.OperacionesCompras;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import vista.Compras;

/**
 *
 * @author Aldair
 */
public class FocusControladorCompras implements FocusListener{
 
    Compras vtCompras=new Compras();
    OperacionesCompras operaciones=new OperacionesCompras();
    
    public FocusControladorCompras(Compras vtCompras,OperacionesCompras operaciones){
        this.vtCompras=vtCompras;
        this.operaciones=operaciones;
        
        this.vtCompras.txtBuscador.addFocusListener(this);
        this.vtCompras.txtBuscarProveedor.addFocusListener(this);
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        //cuando el elemento tiene el foco
        if(e.getSource()==vtCompras.txtBuscador){
            vtCompras.jcMedicamentos.setPopupVisible(true);
        }
        
        if(e.getSource()==vtCompras.txtBuscarProveedor){
            vtCompras.jComboBox1.setPopupVisible(true);
        }
    }

    @Override
    public void focusLost(FocusEvent fe) {
        //cuando el elemento pierde el foco
    }
    
}
