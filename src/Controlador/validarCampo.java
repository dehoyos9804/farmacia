
package Controlador;

import java.awt.event.KeyEvent;

/**
 *
 * @author Aldair
 */
public abstract class validarCampo {
    public static void validar(KeyEvent eve){
        char validar=eve.getKeyChar();
        if(Character.isLetter(validar)){
            eve.consume();
        }
    }
}
