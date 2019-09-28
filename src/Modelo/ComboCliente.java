
package Modelo;

import Modelo.conectorBD;
import Modelo.conectorBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

/**
 *
 * @author Aldair
 */
public class ComboCliente {
 private conectorBD conector=new conectorBD();
 private String consultaSQL;
 private String cedula;
 private String nombre;
 private String direccion;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ComboCliente(String cedula, String nombre, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
    }
    
    public ComboCliente(){
        
    }
    
    public void obtenerVendedores(JComboBox<ComboCliente> obtenerCliente){
       try{
           if(conector.conectar()){
           consultaSQL="SELECT * FROM tblclientes"; 
           ResultSet resultado=conector.seleccionar(consultaSQL);
           while(resultado.next()){
              obtenerCliente.addItem(new ComboCliente(resultado.getString("cedula"),
                                                                     resultado.getString("nombre"),
                                                                     resultado.getString("direccion")
                                                                     ));
            }
           }
           conector.desconectar();
         }catch (SQLException sqle){
        System.out.println("ERROR!!");
        System.err.println(sqle.getMessage() );
         }
    }
    public String toString(){
        return nombre;
    }
 
}
