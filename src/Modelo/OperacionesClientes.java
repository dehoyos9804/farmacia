
package Modelo;
import Modelo.*;
import Modelo.util;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import vista.Clientes;
/**
 *
 * @author Aldair
 */
public class OperacionesClientes {
   conectorBD conector=new conectorBD();
   String consultaSQL;
   
   public OperacionesClientes(){
       conector.getconecction();
   }
    public boolean guardar(String cedula,String nombres,String telefonos, String direccion){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_guardar_clientes("+cedula+","
                                               +"'"+nombres+"'"+","
                                               +"'"+telefonos+"'"+","
                                               +"'"+direccion+"'"+");";
        if(conector.ejecutar(consultaSQL)){
            estado=true;
        }
        conector.desconectar();
    }else{
        util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos",null);
    }
    return estado;
}

public boolean actualizar(String cedula,String nombres,String telefonos, String direccion){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_actualizar_cliente("+cedula+","
                                                  +"'"+nombres+"'"+","
                                                  +"'"+telefonos+"'"+","
                                                  +"'"+direccion+"'"+");";
                                                  
        if(conector.ejecutar(consultaSQL)){
            estado=true;
        }
        conector.desconectar();
    }else{
        util.advertir(null, "ERROR: Verifica la conexion BD.",null);
    }
    return estado;
}

public boolean buscar(String cedula, JTextField jtCedula,JTextField jtNombres,JTextField jtTelefonos,JTextField jtDireccion){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_buscar_cliente("+cedula+");";
        ResultSet resultado=conector.seleccionar(consultaSQL);
        try{
            if(resultado.next()){
               jtCedula.setText(String.valueOf(resultado.getInt("cedula")));
               jtNombres.setText(resultado.getString("nombre"));
               jtTelefonos.setText(resultado.getString("telefonos"));
               jtDireccion.setText(resultado.getString("direccion"));
               estado=true;
            }
            conector.desconectar();
        }catch (SQLException sqle){
        System.err.println(sqle.getMessage ());
    }
        
    }else{
       util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null); 
    }
    return estado;
}

public boolean eliminar(String cedula){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_eliminar_cliente("+cedula+")";
        if(conector.ejecutar(consultaSQL)){
            estado=true;
        }
        conector.desconectar();
    }else{
      util.advertir(null, "ERROR: Verifica la conexion BD.", null);  
    }
    return estado;
}

public boolean llenarTabla(JTable tabla){
    boolean estado=false;
    DefaultTableModel dtm=new DefaultTableModel(){
        public boolean isCellEditable(int row,int column){
            return false;
        }
    };
    dtm.setColumnIdentifiers(new Object[]{"Cedula","Nombres","Telefonos","Dirección"});
    tabla.setModel(dtm);
    try{
        if(conector.conectar()){
          consultaSQL="SELECT * FROM tblclientes";
          ResultSet resultado=conector.seleccionar(consultaSQL);
          while(resultado.next()){
              dtm.addRow(new Object[]{resultado.getInt("cedula"),resultado.getString("nombre"),resultado.getString("telefonos"),resultado.getString("direccion")});
              estado=true;
          }
        }
        conector.desconectar();
    }catch (SQLException sqle){
        System.out.println("Error...");
        System.err.println(sqle.getMessage());
    }
  return estado;
}
}
