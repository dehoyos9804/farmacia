
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
public class OperacionesLaboratorios {
    conectorBD conector=new conectorBD();
    String consultaSQL;
    
    public OperacionesLaboratorios(){
        conector.getconecction();
    }

    public boolean guardar(int idlaboratorio,String nombrelaboratorio,String direccion,String telefonos,String email){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_guardar_laboratorios("+idlaboratorio+","
                                                   +"'"+nombrelaboratorio+"'"+","
                                                   +"'"+direccion+"'"+","
                                                   +"'"+telefonos+"'"+","
                                                   +"'"+email+"'"+");";
        if(conector.ejecutar(consultaSQL)){
            estado=true;
        }
        conector.desconectar();
    }else{
        util.advertir(null,"ERROR... Verifique La Conexion Con BD", null);
    }
            
    return estado;
}

public boolean buscar(String idlabora,JTextField jtIdLaboratorio,JTextField jtNombreLaboratorio,JTextField jtDireccion,JTextField jtTelefonos,JTextField jtEmail){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_buscar_laboratorio("+idlabora+");";
        ResultSet resultado=conector.seleccionar(consultaSQL);
        
        try{
          if(resultado.next()){
              jtIdLaboratorio.setText(String.valueOf(resultado.getInt("idlaboratorio")));
              jtNombreLaboratorio.setText(resultado.getString("nombrelaboratorio"));
              jtDireccion.setText(resultado.getString("direccion"));
              jtTelefonos.setText(resultado.getString("telefonos"));
              jtEmail.setText(resultado.getString("email"));
              estado=true;
          }  
        }catch (SQLException sqle){
            System.out.println("ERROR...");
            System.out.println(sqle.getMessage());
        }
        conector.desconectar();
    }else{
        util.advertir(null, "ERROR: Verifica la conexion BD.", null);
    }
    return estado;
}
public boolean actualizar(int idlaboratorio,String nombrelaboratorio,String direccion,String telefonos,String email){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_actualizar_laboratorio("+idlaboratorio+","
                                                     +"'"+nombrelaboratorio+"'"+","
                                                     +"'"+direccion+"'"+","
                                                     +"'"+telefonos+"'"+","
                                                     +"'"+email+"'"+");";
        if(conector.ejecutar(consultaSQL)){
            estado=true;
        }
        conector.desconectar();
    }else{
        util.advertir(null, "ERROR: Verifica la conexion BD.", null);
    }
    return estado;
}
public boolean eliminar(JTextField jtIdLaboratorio,int idlaboratorio){
    boolean estado=false;
    if(conector.conectar()){
        if(jtIdLaboratorio.getText().length()==0){
            util.informar(null,"Campo id Laboratorio Vacio", null);
        }else{
        consultaSQL="CALL sp_eliminar_laboratorio("+idlaboratorio+");";
        if(conector.ejecutar(consultaSQL)){
            estado=true;
        }
        conector.desconectar();
        }
    }else{
        util.advertir(null,"Error.. Verifique Conexion Con BD", null);
    }
    return estado;
}
public void llenarTabla(JTable jTableRegistro){
    DefaultTableModel dtm=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    dtm.setColumnIdentifiers(new Object[]{"Numero","Nombre","Dirección","Telefonos","Email"});
    jTableRegistro.setModel(dtm);
    try{
        if(conector.conectar()){
          consultaSQL="SELECT * FROM tbllaboratorios";
          ResultSet resultado=conector.seleccionar(consultaSQL);
          while(resultado.next()){
              dtm.addRow(new Object[]{resultado.getInt("idlaboratorio"),resultado.getString("nombrelaboratorio"),resultado.getString("direccion"),
                                      resultado.getString("telefonos"),resultado.getString("email")});
          }
        }
        conector.desconectar();
    }catch (SQLException sqle){
        System.out.println("Error...");
        System.err.println(sqle.getMessage());
    }
}

public void obtenerIdLaboratorio(JTextField jtIdLaboratorio){
    try{
        if(conector.conectar()){
            consultaSQL="SELECT MAX(idlaboratorio)+1 FROM tbllaboratorios;";
            ResultSet resultado=conector.seleccionar(consultaSQL);
            if(resultado.next()){
                if(resultado.getString(1)==null){
                    jtIdLaboratorio.setText("1");
                }else{
                jtIdLaboratorio.setText(resultado.getString(1));
                }
            }else{
                System.out.println("Datos No Encontrados");
            }
        }
        conector.desconectar();
    }catch (SQLException sqle){
        System.out.println("Error..");
        System.out.println(sqle.getMessage());
    }
}
}
