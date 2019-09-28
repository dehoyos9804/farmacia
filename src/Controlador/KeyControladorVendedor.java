
package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import vista.vendedores;
/**
 *
 * @author Aldair
 */
public class KeyControladorVendedor implements KeyListener{
    vendedores vtVendedor=new vendedores();
    String palabra1;
    String palabra2;
    String concadenar;
    public KeyControladorVendedor(vendedores vtVendedor){
        this.vtVendedor=vtVendedor;
        
        this.vtVendedor.jtNombres.addKeyListener(this);
        this.vtVendedor.jtApellidos.addKeyListener(this);
    }
   
    public void keyTyped(KeyEvent ke) {
        
    }

   
    public void keyPressed(KeyEvent ke) {
        
    }

    public void keyReleased(KeyEvent e) {
        if(e.getSource()==vtVendedor.jtNombres){
            palabra1=vtVendedor.jtNombres.getText().toUpperCase();
            palabra2=vtVendedor.jtApellidos.getText().toUpperCase();
            int p1=palabra1.indexOf(" ");
            int p2=palabra2.indexOf(" ");
            if(p1==-1&&p2==-1){
              concadenar=palabra1.substring(0,palabra1.length())+palabra2.substring(0,palabra2.length());
              vtVendedor.txtUsuario.setText(concadenar+"@GENESIS.COM"); 
            }else{
              concadenar=palabra1.substring(0,palabra1.indexOf(" "))+palabra2.substring(0,palabra2.indexOf(" "));
              vtVendedor.txtUsuario.setText(concadenar+"@GENESIS.COM");   
            }
        }
        
        if(e.getSource()==vtVendedor.jtApellidos){
            palabra1=vtVendedor.jtNombres.getText().toUpperCase();
            palabra2=vtVendedor.jtApellidos.getText().toUpperCase();
            int p1=palabra1.indexOf(" ");
            int p2=palabra2.indexOf(" ");
            if(p1==-1&&p2==-1){
              concadenar=palabra1.substring(0,palabra1.length())+palabra2.substring(0,palabra2.length());
              vtVendedor.txtUsuario.setText(concadenar+"@GENESIS.COM"); 
            }else{
              concadenar=palabra1.substring(0,palabra1.indexOf(" "))+palabra2.substring(0,palabra2.indexOf(" "));
              vtVendedor.txtUsuario.setText(concadenar+"@GENESIS.COM");   
            }
             
        }
    }
    
}
