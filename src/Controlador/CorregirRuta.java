
package Controlador;

import java.util.StringTokenizer;

/**
 *
 * @author Aldair
 */
public class CorregirRuta 
{
    String ruta, separador, nuevoSeparador; 
 
    public CorregirRuta(String laruta, String sep, String nuevoSep)
    {
        ruta=laruta;
        separador=sep;
        nuevoSeparador=nuevoSep;
    }
 
    public String obtenerRutaCorregidaWindows()
    {
         StringTokenizer tokens=new StringTokenizer(ruta, separador);
         //Para guardar la ruta corregida
         String rutaCorregida = new String();
         //Contar los tokens (en este caso las carpetas, contado tambien
         // el nombre del archivo seleccionado).
         int noTokens = tokens.countTokens();
         int i = 1;
         do
         {      //Agregar el nuevo separador
            rutaCorregida += tokens.nextToken()+nuevoSeparador;
            i++;
         }while(i<noTokens);
         //Agregar a la ruta corregida el ultimo token, (nombre del archivo)
         rutaCorregida += tokens.nextToken();       
         //Mostrar la ruta corregida en la consola
         System.out.println(rutaCorregida+"\n"+noTokens+ " tokens");
         return rutaCorregida;
    }
}