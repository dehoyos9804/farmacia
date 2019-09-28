
package Modelo;

import Reportes.AstractJasperReport;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Controlador.ComboMedicamento;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Aldair
 */
public class OperacionesVentas {
  conectorBD conector=new conectorBD(); 
  String consultaSQL;
  private final int cont=1;
  private String num="";
  
  public OperacionesVentas(){
      conector.getconecction();
  }
  
  public void generarCodigo(JTextField jtIdventa){
  
  try{
       if(conector.conectar()){
          
           consultaSQL="SELECT max(idventa) FROM tblventas;";
           ResultSet resultado=conector.seleccionar(consultaSQL);
           
           if(resultado.next()){
               if(resultado.getString(1)==null){
                   jtIdventa.setText("CD0001");
               }else{
               char R1=resultado.getString(1).charAt(2);
               char R2=resultado.getString(1).charAt(3);
               char R3=resultado.getString(1).charAt(4);
               char R4=resultado.getString(1).charAt(5);
               String r=""+R1+R2+R3+R4;
               int dato=Integer.parseInt(r);
               if((dato>=1000) || (dato<1000)){
                   int cadena=cont+dato;
                   num=""+cadena;
      
               }
               if((dato>=100) || (dato<100)){
                  int cadena=cont+dato;
                  num="0"+cadena;
      
               }
               if((dato>=9) || (dato<100)){
                   int cadena=cont+dato;
                   num="00"+cadena;
      
               }
               if(dato<9){
                  int cadena=cont+dato;
                  num="000"+cadena; 
              }
               
               jtIdventa.setText("CD"+num);
               }
               
           }
           
       }
       conector.desconectar();
    }catch (SQLException sqle){
        System.out.println("ERROR!");
        System.err.println(sqle.getMessage());
    }
  
}

public boolean guardarVentas(String idventa,String fechaventa,String codvendedores,String codcliente){
    boolean estado=false;
    if(conector.conectar()){
        consultaSQL="CALL sp_guardar_venta("+"'"+idventa+"'"+","
                                            +"'"+fechaventa+"'"+","
                                            +codvendedores+","
                                            +codcliente+"); ";
        if(conector.ejecutar(consultaSQL)){
            estado=true;
        }
        conector.desconectar();
    }else{
       util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null); 
    }
    return estado;
}
public boolean guardarDetalle(String codventa,String codmedicamento,String cantidad,String preciounidad,JTextField jtIdventa,JTable jtDetalle){
    boolean estado=false;
    codventa=jtIdventa.getText().trim();
    for(int i=0;i<jtDetalle.getRowCount();i++){
        if(conector.conectar()){
            codmedicamento=jtDetalle.getValueAt(i, 0).toString();
            cantidad=jtDetalle.getValueAt(i, 2).toString();
            preciounidad=jtDetalle.getValueAt(i, 3).toString();
            consultaSQL="CALL sp_guardar_detalle("+"'"+codventa+"'"+","
                                                  +codmedicamento+","
                                                  +cantidad+","
                                                  +preciounidad+");";
            while(conector.ejecutar(consultaSQL)){
                estado=true;
            }
           conector.desconectar();
        }else{
            util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null);
        }
    }
    return estado;
}

public void obtenerMedicamentos(JComboBox jcMedicamentos){
        
     try{
           if(conector.conectar()){
           consultaSQL="SELECT * FROM tblmedicamentos"; 
           ResultSet resultado=conector.seleccionar(consultaSQL);
           DefaultComboBoxModel combobox=new DefaultComboBoxModel();
           jcMedicamentos.setModel(combobox);
           while(resultado.next()){
              combobox.addElement(new ComboMedicamento(resultado.getString("idmedicamento"),
                                                  resultado.getString("nombre"),
                                                  resultado.getString("cantidadmedicamento"),
                                                  resultado.getString("preciounidad"),
                                                  resultado.getString("preciocompra")));
            }
           }
           conector.desconectar();
         }catch (SQLException sqle){
        System.out.println("ERROR!!");
        System.err.println(sqle.getMessage() );
         }
    }

public void generarReporte(String codigo){
       try{
          if(conector.conectar()){
              AstractJasperReport.creaReporte(conector.getconecction(),"C:/Users/dehoy/OneDrive/Documentos/NetBeansProjects/farmacia/src/Reportes/FacturaVenta.jasper",codigo);
          }else{
              System.out.print("Error Al Conectarse Con BD");
          } 
       }catch(Exception e){
         System.out.println("Error...");
         System.err.println(e.getMessage());
       }
}

public void llenarTabla(JTable jTableVendedores){
    DefaultTableModel dtm=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column){
        return false;
        }
        };
    dtm.setColumnIdentifiers(new Object[]{"Cedula","Nombres","Apellidos","Cargo","Correo"});
    jTableVendedores.setModel(dtm);
    try{
        if(conector.conectar()){
          consultaSQL="SELECT * FROM tblvendedores";
          ResultSet resultado=conector.seleccionar(consultaSQL);
          while(resultado.next()){
              dtm.addRow(new Object[]{resultado.getInt("cc"),resultado.getString("nombres"),resultado.getString("apellidos"),
                                      resultado.getString("cargo"),resultado.getString("correoelectronico")});
          }
        }
        conector.desconectar();
    }catch (SQLException sqle){
        System.out.println("Error...");
        System.err.println(sqle.getMessage());
    }
}

public void FiltroBuscar(JTextField jtBuscador,JComboBox jcMedicamentos){
    try{
        if(conector.conectar()){
           consultaSQL="SELECT * FROM tblmedicamentos WHERE idmedicamento LIKE '%"+jtBuscador.getText()+"%' "
                                                      +"OR nombre LIKE '%"+jtBuscador.getText()+"%' ";
           ResultSet resultado=conector.seleccionar(consultaSQL);
           DefaultComboBoxModel combobox=new DefaultComboBoxModel();
           jcMedicamentos.setModel(combobox);
           while(resultado.next()){
               combobox.addElement(new ComboMedicamento(resultado.getString("idmedicamento"),
                                                  resultado.getString("nombre"),
                                                  resultado.getString("cantidadmedicamento"),
                                                  resultado.getString("preciounidad"),
                                                  resultado.getString("preciocompra")));
           }
           conector.desconectar();
        }
    }catch(SQLException sqle){
       System.out.println("Error...");
       System.err.println(sqle.getMessage()); 
    }
}

public boolean buscarImagenVenta(String codusuario, JLabel lblFoto){
    boolean estado=false;
    ImageIcon foto;
    String is;
    try{
        if(conector.conectar()){
            consultaSQL="CALL sp_buscar_imagenventa("+codusuario+");";
            ResultSet resultado=conector.seleccionar(consultaSQL);
            if(resultado.next()){
              is=resultado.getString(1);//traigo la ruta
              foto=new ImageIcon(is);//convierto la ruta en icono
              Image img=foto.getImage();//convierto el icono en imagen
              Image newimg=img.getScaledInstance(110,90, Image.SCALE_DEFAULT);//convierto la dimension de la imagen
              ImageIcon newicon=new ImageIcon(newimg);//convierto mi imagen dimensionada en icono
              lblFoto.setIcon(newicon);//agrego el icon a mi label
              estado=true;
            }
        }
    }catch (SQLException sqle){
        System.err.println("Error.. "+sqle.getMessage());
    }
    return estado;
}

}
