
package Modelo;

import Controlador.ActionControladorMedicamentos;
import com.toedter.calendar.JDateChooser;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.effect.ImageInput;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aldair
 */
public class OperacionesMedicamentos {
  conectorBD conector=new conectorBD();
  String consultaSQL;

public  OperacionesMedicamentos(){
    conector.getconecction();
} 

public boolean guardar(String idmedicamento,String nombre,String principioactivo,String grupofarmacologico,String codlaboratorio,String concentracion,String formafarmaceutica, String fechavencimiento,String cantidadmedicamento,String preciounidad,String preciocompra,String seguimiento){
    boolean estado=false;
    int cod1=codlaboratorio.indexOf(" ");
    if(conector.conectar()){
        consultaSQL="CALL sp_guardar_medicamento( "+idmedicamento+","
                                              +"'"+nombre+"'"+","
                                              +"'"+principioactivo+"'"+","
                                              +"'"+grupofarmacologico+"'"+","
                                              +codlaboratorio.substring(0,cod1)+","
                                              +"'"+concentracion+"'"+","
                                              +"'"+formafarmaceutica+"'"+","
                                              +"'"+fechavencimiento+"'"+","
                                              +cantidadmedicamento+","
                                              +preciounidad+","
                                              +preciocompra+","
                                              +"'"+seguimiento+"'" + "); ";
        
       if(conector.ejecutar(consultaSQL)){
           estado=true;
       }
       conector.desconectar();
       
    }else{
        util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null);
    }
    return estado;
}

public void generarId(JTextField jtidmedicamento){
    try{
       if(conector.conectar()){
          
           consultaSQL="SELECT max(idmedicamento)+1 as id FROM tblmedicamentos; ";
           ResultSet resultado=conector.seleccionar(consultaSQL);
           
           if(resultado.next()){
               if(resultado.getString(1)==null){
                   jtidmedicamento.setText("1");
               }else{
               jtidmedicamento.setText(resultado.getString(1));
               }
               
           }
           
       }
       conector.desconectar();
    }catch (SQLException sqle){
        System.out.println("ERROR!");
        System.err.println(sqle.getMessage());
    }
}

public void obtenerLaboratorio(JComboBox jccodlaboratorio){
    try{
        if(conector.conectar()){
            consultaSQL="SELECT idlaboratorio,nombrelaboratorio FROM tbllaboratorios";
            ResultSet resultado = conector.seleccionar(consultaSQL);
            
            while(resultado.next()){
                jccodlaboratorio.addItem(resultado.getString("idlaboratorio")+" "+resultado.getString("nombrelaboratorio"));
            }
        }
        conector.desconectar();
    }catch (SQLException sqle){
        System.out.println("ERROR!!");
        System.err.println(sqle.getMessage ());
    }
}

public void llenarTabla(JTable jTableRegistro){
    DefaultTableModel dtm=new DefaultTableModel(){
        public boolean isCellEditable(int row,int column){
            return false;
        }
    };
    dtm.setColumnIdentifiers(new Object[]{"Numero","Nombre","laboratorio","Concentracion","Forma","Cantidad"});
    jTableRegistro.setModel(dtm);
    int ancho[]={20,150,20,20,20,20};
    for(int i=0;i<jTableRegistro.getColumnCount();i++){
        jTableRegistro.getColumnModel().getColumn(i).setPreferredWidth(ancho[i]);
    }
    try{
        if(conector.conectar()){
          consultaSQL="SELECT tblmedicamentos.idmedicamento,tblmedicamentos.nombre,"
                  + "tbllaboratorios.nombrelaboratorio,tblmedicamentos.concentracion,"
                  + "tblmedicamentos.formafarmaceutica,tblmedicamentos.cantidadmedicamento "
                  + "FROM tblmedicamentos INNER JOIN tbllaboratorios ON "
                  + "tblmedicamentos.codlaboratorio=tbllaboratorios.idlaboratorio;";
          ResultSet resultado=conector.seleccionar(consultaSQL);
          while(resultado.next()){
              dtm.addRow(new Object[]{resultado.getInt(1),resultado.getString(2),resultado.getString(3),
                                      resultado.getString(4),resultado.getString(5),
                                      resultado.getString(6)});
          }
        }
        conector.desconectar();
    }catch (SQLException sqle){
        System.out.println("Error...");
        System.err.println(sqle.getMessage());
    }
}

public boolean buscarMedicamento(String idmedic,JTextField jtidmedicamento,JTextField jtnombre,JTextField jtprincipioactivo,JTextField jtgrupofarmacologico,JComboBox jccodlaboratorio,JTextField jtconcentracion,JComboBox jcformafarmaceutica,JDateChooser jdfechavencimiento,JTextField jtcantidadmedicamento,JTextField jtPrecioUnidad,JTextField txtPrecioCompra,JTextField jtseguimiento){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_buscar_medicamento("+idmedic+");";
        ResultSet resultado = conector.seleccionar(consultaSQL);
       
        try{
            if(resultado.next()){
            jtidmedicamento.setText(String.valueOf(resultado.getInt("idmedicamento")));
            jtnombre.setText(resultado.getString("nombre"));
            jtprincipioactivo.setText(resultado.getString("principioactivo"));
            jtgrupofarmacologico.setText(resultado.getString("grupofarmacologico"));
            jccodlaboratorio.setSelectedIndex(resultado.getInt("codlaboratorio")-1);
            jtconcentracion.setText(resultado.getString("concentracion"));
            jcformafarmaceutica.setSelectedItem(resultado.getString("formafarmaceutica"));
            jdfechavencimiento.setDate(resultado.getDate("fechavencimiento"));
            jtcantidadmedicamento.setText(String.valueOf(resultado.getInt("cantidadmedicamento")));
            jtPrecioUnidad.setText(String.valueOf(resultado.getDouble("preciounidad")));
            txtPrecioCompra.setText(String.valueOf(resultado.getDouble("preciocompra")));
            jtseguimiento.setText(resultado.getString("seguimiento"));
            estado=true;
            }
            conector.desconectar();
        }catch(SQLException sqle){
          System.out.println(" ERROR: Buscar ()");
          System.err.println(sqle.getMessage ());
        }
    }else{
        util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null);
    }
    return estado;
}

public boolean actualizar(String idmedicamento,String nombre,String principioactivo,String grupofarmacologico,String codlaboratorio,String concentracion,String formafarmaceutica, String fechavencimiento,String cantidadmedicamento,String preciounidad,String preciocompra,String seguimiento){
    boolean estado=false;
    int cod1=codlaboratorio.indexOf(" ");
     if(conector.conectar()){
        consultaSQL="CALL sp_actualizar_medicamento("+idmedicamento+","
                                              +"'"+nombre+"'"+","
                                              +"'"+principioactivo+"'"+","
                                              +"'"+grupofarmacologico+"'"+","
                                              +codlaboratorio.substring(0,cod1)+","
                                              +"'"+concentracion+"'"+","
                                              +"'"+formafarmaceutica+"'"+","
                                              +"'"+fechavencimiento+"'"+","
                                              +cantidadmedicamento+","
                                              +preciounidad+","
                                              +preciocompra+","
                                              +"'"+seguimiento+"'" + "); ";
    if(conector.ejecutar(consultaSQL)){
           estado=true;
       }
       conector.desconectar();
      }else{
            util.advertir(null, "ERROR: Verifica la conexion BD.", null);
        }
    return estado;
}

public boolean eliminar(String idmedicamento){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_eliminar_medicamento("+idmedicamento+");";
        if(conector.ejecutar(consultaSQL)){
            estado=true;
        }
        conector.desconectar();
    }else{
         util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null);
    }
    return estado;
}
public boolean ejecutarSentencia(){
boolean estado=false;
if(conector.conectar()){
    consultaSQL="CALL sp_seguimiento_med("+")";
    if(conector.ejecutar(consultaSQL)){
        estado=true;
    }
    conector.desconectar();
}else{
        util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null);
    }
return estado;
}

public boolean guardarImagenMedicamento(String ruta,String tipoimagen,String codmedicamento){
    boolean estado=false;
    try{
        if(conector.conectar()){
            //archivofoto=new FileInputStream(ruta);
            consultaSQL="CALL sp_guardar_imagenmedicamento("+null+","
                                                 +"'"+ruta+"'"+","
                                                 +"'"+tipoimagen+"'"+","
                                                 +"'"+codmedicamento+"'"+");";
            if(conector.ejecutar(consultaSQL)){
                estado=true;
            }
        }
    }catch (Exception e){
        System.out.println(e.getLocalizedMessage());
    }
    return estado;
}

public boolean buscarImagenMedicamento(JLabel lblImagenMedicamento,String codimagen){
    boolean estado=false;
    int codimagen2 = Integer.parseInt(codimagen);
    ImageIcon foto;
    String is;
    if(conector.conectar()){
        consultaSQL = "CALL sp_buscar_imagenmedicamento("+codimagen2+"); ";
        ResultSet r = conector.seleccionar(consultaSQL);
        
        try {
            if(r.next()){
                is = r.getString(1);
                foto = new ImageIcon(is);
                Image img = foto.getImage();
                Image newImg = img.getScaledInstance(160,160,Image.SCALE_DEFAULT);
                ImageIcon newIcon = new ImageIcon(newImg);
                lblImagenMedicamento.setIcon(newIcon);
                ActionControladorMedicamentos ACM = new ActionControladorMedicamentos();
                ACM.ruta=r.getString(2);
                estado = true;
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage ());
        }
    }
    return estado;
}

public boolean actualizarImagenMedicamento(String imagen,String tipoimagen,String codmedicamento){
    boolean estado = false;
    int id = buscarIdMedicamento(codmedicamento);
    try {
        if(conector.conectar()){
                    consultaSQL = "CALL sp_actualizar_imagenmedicamento("+id+","
                                                             +"'"+imagen+"'"+","
                                                             +"'"+tipoimagen+"'"+","
                                                             +"'"+codmedicamento+"'"+");";
            if(conector.ejecutar(consultaSQL)){
                estado = true;
            }
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return estado;
}

private int buscarIdMedicamento(String codmedicamento){
    int idimgmedicamento = 0;
    if(conector.conectar()){
        consultaSQL = "SELECT tblimagenmedicamentos.idimagenmedicamento " +
                      "FROM tblimagenmedicamentos " +
                      "WHERE tblimagenmedicamentos.codmedicamento="+codmedicamento+";";
        ResultSet r = conector.seleccionar(consultaSQL);
        
        try {
            System.out.print(r.next());
            if(r.next()){
                idimgmedicamento = r.getInt(1);
                System.out.print(idimgmedicamento);
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
    
    return idimgmedicamento;
}
}
