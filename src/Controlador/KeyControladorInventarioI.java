
package Controlador;

import Modelo.OperacionesInventarioInicial;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import vista.InventarioInicial;

/**
 *
 * @author Aldair
 */
public class KeyControladorInventarioI implements KeyListener{
    OperacionesInventarioInicial operaciones=new OperacionesInventarioInicial();
    InventarioInicial vtInventarioI=new InventarioInicial();
    
    public KeyControladorInventarioI(InventarioInicial vtInventarioI,OperacionesInventarioInicial operaciones){
        this.vtInventarioI=vtInventarioI;
        this.operaciones=operaciones;
        
        this.vtInventarioI.txtBuscador.addKeyListener(this);
        this.vtInventarioI.txtBuscarProductosIn.addKeyListener(this);
    }
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource()==vtInventarioI.txtBuscador){
            operaciones.filtroBusqueda(vtInventarioI.txtBuscador,vtInventarioI.comboProductos);
            vtInventarioI.comboProductos.setPopupVisible(true);
        }
        if(e.getSource()==vtInventarioI.txtBuscarProductosIn){
            operaciones.FiltroTabla(vtInventarioI.TableInventarionicial, vtInventarioI.txtBuscarProductosIn);
        }
    }
    
}
