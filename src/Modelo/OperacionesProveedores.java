
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aldair
 */
public class OperacionesProveedores {
    conectorBD conector=new conectorBD();
    String consultaSQL;
    
    public OperacionesProveedores(){
        conector.getconecction();
    }
    
    public boolean guardarProveedor(int idproveedor,String nombre,String direccion,String telefonos,String email){
        boolean estado=false;
            if(conector.conectar()){
              consultaSQL="CALL sp_guardar_proveedor("+idproveedor+","
                                                      +"'"+nombre+"'"+","
                                                      +"'"+direccion+"'"+","
                                                      +"'"+telefonos+"'"+","
                                                      +"'"+email+"'"+");";
              
              if(conector.ejecutar(consultaSQL)){
                  estado=true;
              }
              conector.desconectar();
            }else{
              util.advertir(null,"Error...Verifique La Conexion Con BD", null);
            }
          
        return estado;
    }
    public boolean obtenerCodigo(JTextField txtIdProveedor){
       boolean estado=false;
       try{
           if(conector.conectar()){
               consultaSQL="SELECT MAX(idproveedor)+1 AS id FROM tblproveedores";
               ResultSet resultado=conector.seleccionar(consultaSQL);
               if(resultado.next()){
                   if(resultado.getString(1)==null){
                       txtIdProveedor.setText("1");
                       estado=true;
                   }else{
                       txtIdProveedor.setText(resultado.getString(1));
                       estado=true;
                   }
               }
           }
           conector.desconectar();
       }catch (SQLException sqle){
           System.out.println("Error...");
           System.err.println(sqle.getMessage());
       }
       return estado;
    }
    
    public boolean buscarDatos(String codigo,JTextField txtIdProveedores,JTextField txtNombre,JTextField txtDireccion,JTextField txtTelefonos,JTextField txtEmail){
        boolean estado=false;
            if(conector.conectar()){
                consultaSQL="CALL sp_buscar_proveedores("+codigo+");";
                ResultSet resultado=conector.seleccionar(consultaSQL);
                
                
                try{
                    if(resultado.next()){
                        txtIdProveedores.setText(resultado.getString("idproveedor"));
                        txtNombre.setText(resultado.getString("nombre"));
                        txtDireccion.setText(resultado.getString("direccion"));
                        txtTelefonos.setText(resultado.getString("telefonos"));
                        txtEmail.setText(resultado.getString("email"));
                        estado=true;
                    }
                   conector.desconectar();
                }catch (SQLException sqle){
                    System.err.println(sqle.getMessage());
                    System.out.println("Error...");
                }
            }
        return estado;
    }
    
    public boolean actualizarDatos(int idproveedor,String nombre,String direccion,String telefonos,String email){
        boolean estado=false;
        if(conector.conectar()){
            consultaSQL="CALL sp_atualizar_proveedor("+idproveedor+","
                                                       +"'"+nombre+"'"+","
                                                       +"'"+direccion+"'"+","
                                                       +"'"+telefonos+"'"+","
                                                       +"'"+email+"'"+");";
            if(conector.ejecutar(consultaSQL)){
                estado=true;
            }
            conector.desconectar();
        }else{
             util.advertir(null, "ERROR: Verifica la conexion BD.",null);
        }
        return estado;
    }
    
    public boolean EliminarDatos(int codigo){
        boolean estado=false;
        if(conector.conectar()){
            consultaSQL="CALL sp_eliminar_proveedor("+codigo+");";
            if(conector.ejecutar(consultaSQL)){
                estado=true;
            }
            conector.desconectar();
        }else{
            util.advertir(null, "ERROR: Verifica la conexion BD.", null); 
        }
        return estado;
    }
    public void llenarTabla(JTable tbProveedores){
        DefaultTableModel dtm=new DefaultTableModel(){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        dtm.setColumnIdentifiers(new Object[]{"Nit","Razón Social","Direccion","Email"});
        tbProveedores.setModel(dtm);
        int ancho[]={20,180,30,60};
        for(int i=0;i<tbProveedores.getColumnCount();i++){
            tbProveedores.getColumnModel().getColumn(i).setPreferredWidth(ancho[i]);
        }
        try{
            if(conector.conectar()){
                consultaSQL="SELECT * FROM tblproveedores";
                ResultSet resultado=conector.seleccionar(consultaSQL);
                
                while(resultado.next()){
                    if(resultado.getInt("idproveedor")!=0){
                    dtm.addRow(new Object[]{resultado.getInt("idproveedor"),resultado.getString("nombre"),
                        resultado.getString("direccion"),resultado.getString("email")});
                    }
                }
            }
            conector.desconectar();
        }catch (SQLException sqle){
            System.out.println("Error...");
            System.err.println(sqle.getMessage());
        }
    }
}
