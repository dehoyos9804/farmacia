
package vista;
import Modelo.conectorBD;
import javax.swing.JOptionPane;


public class Main {

    
    public static void main(String[] args) {
       conectorBD C= new conectorBD ();
        
        if (C.conectar()){
            Inicio iniciar= new Inicio();
            iniciar.setVisible(true);
        }else{
          JOptionPane.showMessageDialog(null ,"no se conecto (*_*) ");   
        }
    }
    
}