
package Modelo;

import Controlador.ComboMedicamento;
import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aldair
 */
public class OperacionesInventarioInicial {
    conectorBD conector=new conectorBD();
    String consultaSQL;
    
    public OperacionesInventarioInicial(){
        conector.getconecction();
    }
    
    public void obtenerMedicamentos(JComboBox combo){
       try{
           if(conector.conectar()){
               consultaSQL="SELECT * FROM tblmedicamentos";
               ResultSet resultado=conector.seleccionar(consultaSQL);
               DefaultComboBoxModel combobox=new DefaultComboBoxModel();
               combo.setModel(combobox);
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
           System.out.println("Error...");
           System.err.println(sqle.getMessage());
       }
    }
    
    public void filtroBusqueda(JTextField txtBusqueda,JComboBox combo){
        try{
            if(conector.conectar()){
                consultaSQL="SELECT * FROM tblmedicamentos WHERE idmedicamento LIKE '%"+txtBusqueda.getText().trim()+"%' "
                                                           +"OR nombre LIKE '%"+txtBusqueda.getText().trim()+"%' ";
                ResultSet resultado=conector.seleccionar(consultaSQL);
                DefaultComboBoxModel combobox=new DefaultComboBoxModel();
                combo.setModel(combobox);
                while(resultado.next()){
                    combobox.addElement(new ComboMedicamento(resultado.getString("idmedicamento"),
                                                  resultado.getString("nombre"),
                                                  resultado.getString("cantidadmedicamento"),
                                                  resultado.getString("preciounidad"),
                                                  resultado.getString("preciocompra")));
                }
                conector.desconectar();
            }
        }catch (SQLException sqle){
            System.out.println("Error...");
            System.err.println(sqle.getMessage());
        }
    }
    
    public boolean guardarInventario(String fechaentrada){
        boolean estado=false;
        if(conector.conectar()){
            consultaSQL="CALL sp_guardar_compra("+null+","
                                              +"'"+fechaentrada+"'"+","
                                              +0+");";
            if(conector.ejecutar(consultaSQL)){
                estado=true;
            }
            conector.conectar();
        }else{
            util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null);
        }
        return estado;
    }
    
    public boolean guardarDetalleInvetario(int codmedicamento,int cantidad,int preciocompra){
        boolean estado=false;
        if(conector.conectar()){
            consultaSQL="CALL sp_guardar_detallecompra("+maxidcompra()+","
                                                     +codmedicamento+","
                                                     +cantidad+","
                                                     +preciocompra+");";
            if(conector.ejecutar(consultaSQL)){
                estado=true;
            }
        }else{
            util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null);
        }
        return estado;
    }
    
    public boolean actualizarInventario(String fechaentrada,int idcompra){
        boolean estado=false;
        
        if(conector.conectar()){
            consultaSQL="CALL sp_actualizar_compra("+idcompra+","
                                                    +"'"+fechaentrada+"'"+");";
            if(conector.ejecutar(consultaSQL)){
                estado=true;
            }
        }else{
            util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null);
        }
        return estado;
    }
    public boolean actualizarDetalleInventario(int codcompra,int cantidad,int preciocompra){
        boolean estado=false;
        if(conector.conectar()){
            consultaSQL="CALL sp_actualizar_detallecompra("+codcompra+","
                                                           +cantidad+","
                                                           +preciocompra+");";
            if(conector.ejecutar(consultaSQL)){
                estado=true;
            }
        }else{
            util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null);
        }
        return estado;
    }
    
    public void FiltroTabla(JTable tbInventario,JTextField txtBuscarProductosIn){
        DefaultTableModel tabla=new DefaultTableModel(){
          public boolean isCellEditable(int row, int column){
              return false;
          }  
        };
        tabla.setColumnIdentifiers(new Object[]{"Fecha Ingreso","Identificador","Producto","laboratorio","Cantidad","Precio","Numero"});
        tbInventario.setModel(tabla);
        int ancho[]={40,30,150,20,20,20,10};
        for(int i=0;i<tbInventario.getColumnCount();i++){
            tbInventario.getColumnModel().getColumn(i).setPreferredWidth(ancho[i]);
        }
        //oculta la columna numero
        tbInventario.getColumnModel().getColumn(6).setMaxWidth(0);
        tbInventario.getColumnModel().getColumn(6).setMinWidth(0);
        tbInventario.getColumnModel().getColumn(6).setPreferredWidth(0);
        
        try{
            if(conector.conectar()){
                consultaSQL="SELECT tblcompras.fechacompra,tbldetallecompras.codmedic,tblmedicamentos.nombre,tbllaboratorios.nombrelaboratorio,"
                           +"tbldetallecompras.cantidadcompra,tbldetallecompras.preciocompra,tbldetallecompras.codcompra "
                           +"FROM tbldetallecompras " 
                           +"INNER JOIN tblcompras ON tbldetallecompras.codcompra=tblcompras.idcompra "
                           +"INNER JOIN tblmedicamentos ON tbldetallecompras.codmedic=tblmedicamentos.idmedicamento "
                           +"INNER JOIN tbllaboratorios ON tblmedicamentos.codlaboratorio=tbllaboratorios.idlaboratorio "
                           +"WHERE  tblmedicamentos.nombre LIKE '%"+txtBuscarProductosIn.getText()+"%' "
                           +"OR tblmedicamentos.idmedicamento LIKE '%"+txtBuscarProductosIn.getText()+"%' "
                           +"ORDER BY tblcompras.fechacompra,tblmedicamentos.nombre,tbllaboratorios.nombrelaboratorio;";
                ResultSet resultado=conector.seleccionar(consultaSQL);
                while(resultado.next()){
                    tabla.addRow(new Object[]{resultado.getString(1),resultado.getString(2),
                                              resultado.getString(3),resultado.getString(4),
                                              resultado.getString(5),resultado.getString(6),
                                              resultado.getString(7)});
                }
                conector.desconectar();
            }else{
                util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null);
            }
        }catch (SQLException sqle){
            System.err.println("Error.."+sqle.getMessage());
        }
    }
    public void llenarTabla(JTable tbInventario){
        DefaultTableModel tabla=new DefaultTableModel(){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        tabla.setColumnIdentifiers(new Object[]{"Fecha Ingreso","Identificador","Producto","laboratorio","Cantidad","Precio","Numero"});
        tbInventario.setModel(tabla);
        int ancho[]={40,30,150,20,20,20,10};
        for(int i=0;i<tbInventario.getColumnCount();i++){
            tbInventario.getColumnModel().getColumn(i).setPreferredWidth(ancho[i]);
        }
        //oculta la columna numero
        tbInventario.getColumnModel().getColumn(6).setMaxWidth(0);
        tbInventario.getColumnModel().getColumn(6).setMinWidth(0);
        tbInventario.getColumnModel().getColumn(6).setPreferredWidth(0);
        try{
            if(conector.conectar()){
                consultaSQL="SELECT tblcompras.fechacompra,tbldetallecompras.codmedic,tblmedicamentos.nombre,tbllaboratorios.nombrelaboratorio,"
                           +"tbldetallecompras.cantidadcompra,tbldetallecompras.preciocompra,tbldetallecompras.codcompra "
                           +"FROM tbldetallecompras "
                           +"INNER JOIN tblcompras ON tbldetallecompras.codcompra=tblcompras.idcompra "
                           +"INNER JOIN tblmedicamentos ON tbldetallecompras.codmedic=tblmedicamentos.idmedicamento "
                           +"INNER JOIN tbllaboratorios ON tblmedicamentos.codlaboratorio=tbllaboratorios.idlaboratorio "
                           +"ORDER BY tblcompras.fechacompra,tblmedicamentos.nombre,tbllaboratorios.nombrelaboratorio";
                ResultSet resultado=conector.seleccionar(consultaSQL);
                while(resultado.next()){
                    tabla.addRow(new Object[]{resultado.getString(1),resultado.getString(2),
                                              resultado.getString(3),resultado.getString(4),
                                              resultado.getString(5),resultado.getString(6),
                                              resultado.getString(7)});
                }
                conector.desconectar();
            }else{
                util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null);
            }
        }catch (SQLException sqle){
            System.out.println("Error..");
            System.err.println(sqle.getMessage());
        }
    }
    
    public int maxidcompra(){
        int idcompra=0;
        try{
        if(conector.conectar()){
            consultaSQL="SELECT MAX(tblcompras.idcompra) FROM tblcompras;";
            ResultSet resultado=conector.seleccionar(consultaSQL);
            if(resultado.next()){
                idcompra=idcompra+resultado.getInt(1);
            }
        }
        }catch (SQLException sqle){
            System.out.println("Error.."+sqle.getMessage());
        }
        return idcompra;
    }
    
    public boolean buscarInventario(int codcompra,int codmedic,JDateChooser fechaentrada,JComboBox comboProductos,JTextField txtCantidad, JTextField txtPrecioCompra){
        boolean estado=false;
        try{
            if(conector.conectar()){
                consultaSQL="CALL sp_buscar_compra("+codcompra+","
                                                    +codmedic+");";
                ResultSet resultado=conector.seleccionar(consultaSQL);
                while(resultado.next()){
                    fechaentrada.setDate(resultado.getDate(1));
                    comboProductos.setSelectedIndex(resultado.getInt(2)-1);
                    txtCantidad.setText(resultado.getString(3));
                    txtPrecioCompra.setText(resultado.getString(4));
                    estado=true;
                }
                conector.desconectar();
            }else{
                util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null);
            }
        }catch (SQLException sqle){
            System.err.println("Error.. "+sqle.getMessage());
        }
        return estado;
    }
}
