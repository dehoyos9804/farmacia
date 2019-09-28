
package Modelo;

import com.toedter.calendar.JDateChooser;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aldair
 */
public class OperacionesVendedores {
    conectorBD conector=new conectorBD();
    static String consultaSQL;
    
    public OperacionesVendedores(){
        conector.getconecction();
    }
    
public boolean guardar(int cc,String nombres,String apellidos,String cargo,String telefonos,String fechanacimiento,String direccion,String correoelectronico){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_guardar_vendedor("+cc+","
                                               +"'"+nombres+"'"+","
                                               +"'"+apellidos+"'"+","
                                               +"'"+cargo+"'"+","
                                               +"'"+telefonos+"'"+","
                                               +"'"+fechanacimiento+"'"+","
                                               +"'"+direccion+"'"+","
                                               +"'"+correoelectronico+"'"+");";
        if(conector.ejecutar(consultaSQL)){
            estado=true;
        }
        conector.desconectar();
    }else{
        util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null);
    }
    return estado;
}

public boolean buscar(String cec,JTextField jtCc,JTextField jtNombres,JTextField jtApellidos,JTextField jtCargo,JTextField jtTelefonos,JDateChooser jdFechaNacimiento,JTextField jtDireccion,JTextField jtCorreoElectronico,JLabel lblfoto,JComboBox comboTipoUsuario,JTextField txtUsuario,JPasswordField txtContraseña,JPasswordField txtComprobarContraseña){
    boolean estado=false;
    ImageIcon foto;
    //InputStream is;
    String is;
    if(conector.conectar()){
        consultaSQL="CALL sp_buscar_vendedor("+cec+");";
        ResultSet resultado=conector.seleccionar(consultaSQL);
        try{
            while(resultado.next()){
               jtCc.setText(String.valueOf(resultado.getInt(1)));
               jtNombres.setText(resultado.getString(2));
               jtApellidos.setText(resultado.getString(3));
               jtCargo.setText(resultado.getString(4));
               jtTelefonos.setText(resultado.getString(5));
               jdFechaNacimiento.setDate(resultado.getDate(6));
               jtDireccion.setText(resultado.getString(7));
               jtCorreoElectronico.setText(resultado.getString(8));
               txtUsuario.setText(resultado.getString(9));
               txtContraseña.setText(resultado.getString(10));
               txtComprobarContraseña.setText(resultado.getString(10));
               comboTipoUsuario.setSelectedItem(resultado.getString(11));
               
               is=resultado.getString(12);
               //BufferedImage bi=ImageIO.read(is);
               foto=new ImageIcon(is);
               Image img=foto.getImage();
               Image newimg=img.getScaledInstance(120,105,Image.SCALE_DEFAULT);
               ImageIcon newicon=new ImageIcon(newimg);
               lblfoto.setIcon(newicon);
               estado=true;
            }
            conector.desconectar();
        }catch (Exception sqle){
        System.err.println(sqle.getMessage());
    }
        
    }else{
       util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null); 
    }
    return estado;
}

public boolean guardarusuarios(int codempleado,String usuario,String contraseña,String tipousuario){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_guardar_usuario("+codempleado+","
                                              +"'"+usuario+"'"+","
                                              +"'"+contraseña+"'"+","
                                              +"'"+tipousuario+"'"+");";
        if(conector.ejecutar(consultaSQL)){
            estado=true;
        }
    }else{
       util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null); 
    }
    return estado;
}

public boolean guardarImagen(String ruta,String tipoimagen,int codusuario){
    boolean estado=false;
    //FileInputStream archivofoto;
    try{
        if(conector.conectar()){
            //archivofoto=new FileInputStream(ruta);
            consultaSQL="CALL sp_guardar_imagen("+null+","
                                                 +"'"+ruta+"'"+","
                                                 +"'"+tipoimagen+"'"+","
                                                 +codusuario+");";
            if(conector.ejecutar(consultaSQL)){
                estado=true;
            }
        }
    }catch (Exception e){
        System.out.println(e.getLocalizedMessage());
    }
    return estado;
}
public boolean actualizar(int cc,String nombres,String apellidos,String cargo,String telefonos,String fechanacimiento,String direccion,String correoelectronico){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_actualizar_vendedor("+cc+","
                                                  +"'"+nombres+"'"+","
                                                  +"'"+apellidos+"'"+","
                                                  +"'"+cargo+"'"+","
                                                  +"'"+telefonos+"'"+","
                                                  +"'"+fechanacimiento+"'"+","
                                                  +"'"+direccion+"'"+","
                                                  +"'"+correoelectronico+"'"+");";
        if(conector.ejecutar(consultaSQL)){
            estado=true;
        }
        conector.desconectar();
    }else{
        util.advertir(null, "ERROR: Verifica la conexion BD.", null);
    }
    return estado;
}

public boolean eliminar(int cc){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_eliminar_vendedor("+cc+")";
        if(conector.ejecutar(consultaSQL)){
            estado=true;
        }
        conector.desconectar();
    }else{
      util.advertir(null, "ERROR: Verifica la conexion BD.", null);  
    }
    return estado;
}

public void llenarTabla(JTable jTableRegistro){
    DefaultTableModel dtm=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    dtm.setColumnIdentifiers(new Object[]{"Cedula","Nombres","Apellidos","Cargo","Telefonos","Nacimiento","Dirección","Correo"});
    jTableRegistro.setModel(dtm);
    try{
        if(conector.conectar()){
          consultaSQL="SELECT * FROM tblvendedores";
          ResultSet resultado=conector.seleccionar(consultaSQL);
          while(resultado.next()){
              dtm.addRow(new Object[]{resultado.getInt("cc"),resultado.getString("nombres"),resultado.getString("apellidos"),
                                      resultado.getString("cargo"),resultado.getString("telefonos"),resultado.getDate("fechanacimiento"),
                                      resultado.getString("direccion"),resultado.getString("correoelectronico")});
          }
        }
        conector.desconectar();
    }catch (SQLException sqle){
        System.out.println("Error...");
        System.err.println(sqle.getMessage());
    }
}

}
