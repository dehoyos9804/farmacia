
package Modelo;


import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class util {
    
    
    public static String  aFechaMYSQL (Date date){
        String fecha;
        SimpleDateFormat sdf = new  SimpleDateFormat ("YYYY-MM-dd");
        fecha = sdf.format(date);
        
        return fecha;
    }

  /*public static String formatopesos (double valor){
      DecimalFormat  formateador= new DecimalFormat ();
      String res="$ " + formateador.format(valor);
      
      return res;
  }*/ 
        
  public static void  informar(Component c, String mensaje, String titulo){
      
      JOptionPane.showMessageDialog(c, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
  }
   
   
  
  public static void  advertir (Component c, String mensaje, String titulo){
      
      JOptionPane.showMessageDialog(c, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
  }
   
  
  
  public static String  capturar (Component c, String mensaje, String titulo){
      
     String datos = JOptionPane.showInputDialog(c, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
      
      
      if (datos == null){
          datos ="";
      }
      
      
      return datos;
 
  
   }
  
 
  
  public static boolean confirmar (Component c, String mensaje, String titulo){
      
      int respuesta= JOptionPane.showConfirmDialog(c, mensaje, titulo, JOptionPane.YES_NO_OPTION);
      
      if (respuesta == JOptionPane.YES_OPTION){
          return true;
      }else{
          return false;
      }
      
  }
  
  
  

    
}