
package Controlador;
public class FullSelectorListener extends java.awt.event.FocusAdapter {
    
    public FullSelectorListener() {
    }
    @Override
    public void focusLost(java.awt.event.FocusEvent evt) {
 
    }
    @Override
    public void focusGained(java.awt.event.FocusEvent evt) {
        Object o = evt.getSource();
        if(o instanceof javax.swing.JTextField){
            javax.swing.JTextField txt = (javax.swing.JTextField) o;
            txt.setSelectionStart(0);
            txt.setSelectionEnd(txt.getText().length());
        }
    }
}