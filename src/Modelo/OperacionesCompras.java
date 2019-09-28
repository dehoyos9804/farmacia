
package Modelo;

import Controlador.ComboMedicamento;
import Controlador.ComboProveedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Aldair
 */
public class OperacionesCompras {
    conectorBD conector=new conectorBD();
    String consultaSQL;
    
    public OperacionesCompras(){
        conector.getconecction();
    }
    
//metodo para generar el numero de factura automaticamente
 public boolean numeroFactura(JTextField txtNumeroFactura){
     boolean estado=false;
     try{
         if(conector.conectar()){
             consultaSQL="SELECT MAX(idcompra)+1 FROM tblcompras";
             ResultSet resultado=conector.seleccionar(consultaSQL);
             if(resultado.next()){
                 if(resultado.getString(1)==null){
                     txtNumeroFactura.setText("1");
                 }else{
                     txtNumeroFactura.setText(resultado.getString(1));
                 }
             }else{
                 System.out.println("Datos No Encontrados");
             }
         }
     }catch (SQLException sqle){
         System.out.println("Error..");
         System.err.println(sqle.getMessage());
     }
     return estado;
 }
 //metodo para guardar en la tabla de compra
 public boolean guardarCompra(String idcompra,String fechacompra,String codproveedor,String codmedicamento,String cantidad,String preciocompra,JTextField txtNumeroFactura,JTable jtDetalleCompra){
     boolean estado=false;
     if(conector.conectar()){
         consultaSQL="CALL sp_guardar_compra("+idcompra+","
                                              +"'"+fechacompra+"'"+","
                                              +codproveedor+");";
         if(conector.ejecutar(consultaSQL)){
             guardarDetalleCompra(codmedicamento,cantidad,preciocompra,txtNumeroFactura,jtDetalleCompra);
             estado=true;
         }
         conector.desconectar();
     }else{
       util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null); 
     }
     return estado;
 }
 //Metodo para guardar en la tabla de detalle de compra
 public boolean guardarDetalleCompra(String codmedicamento,String cantidad,String preciocompra,JTextField txtNumeroFactura,JTable jtDetalleCompra){
     boolean estado=false;
     String codcompra=txtNumeroFactura.getText().trim().toUpperCase();
     for(int i=0;i<jtDetalleCompra.getRowCount();i++){
     if(conector.conectar()){
         codmedicamento=jtDetalleCompra.getValueAt(i, 0).toString();
         cantidad=jtDetalleCompra.getValueAt(i, 2).toString();
         preciocompra=jtDetalleCompra.getValueAt(i, 3).toString();
         consultaSQL="CALL sp_guardar_detallecompra("+codcompra+","
                                                     +codmedicamento+","
                                                     +cantidad+","
                                                     +preciocompra+");";
         if(conector.ejecutar(consultaSQL)){
             estado=true;
         }
         conector.desconectar();
     }else{
       util.advertir(null,"Error!.. Verifique La Conexion De Base De Datos", null); 
    }
   }
     return estado;
 }
 //metodo para llenar el combobox con los valores de la tabla de datos 
 public void obtenerMedicamento(JComboBox jcMedicamentos){
     try{
         if(conector.conectar()){
             consultaSQL="SELECT * FROM tblmedicamentos";
             ResultSet resultado=conector.seleccionar(consultaSQL);
             DefaultComboBoxModel combo=new DefaultComboBoxModel();
             jcMedicamentos.setModel(combo);
             while(resultado.next()){
                 combo.addElement(new ComboMedicamento(resultado.getString("idmedicamento"),
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
 
 //metodo para llenar el combobox con los valores de la tabla de datos para proveedores
 public void obtenerProveedor(JComboBox jComboBox1){
     try{
         if(conector.conectar()){
             consultaSQL="SELECT * FROM tblproveedores";
             ResultSet resultado=conector.seleccionar(consultaSQL);
             DefaultComboBoxModel combo=new DefaultComboBoxModel();
             jComboBox1.setModel(combo);
             while(resultado.next()){
                 if(resultado.getInt("idproveedor")!=0){
                     combo.addElement(new ComboProveedor(resultado.getString("idproveedor"),
                                                         resultado.getString("nombre"),
                                                         resultado.getString("direccion"),
                                                         resultado.getString("telefonos"),
                                                         resultado.getString("email")));
                 }
             }
         }
         conector.desconectar();
     }catch (SQLException sqle){
         System.out.println("Error..");
         System.err.println(sqle.getMessage());
     }
 }
 
 //Metodo que busca los medicamentos por su nombre y/o codigo
 public void filtroBuscar(JComboBox jcMedicamentos,JTextField txtBuscador){
    try{
        if(conector.conectar()){
           consultaSQL="SELECT * FROM tblmedicamentos WHERE idmedicamento LIKE '%"+txtBuscador.getText()+"%' "
                                                      +"OR nombre LIKE '%"+txtBuscador.getText()+"%' ";
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
 public void filtroBuscarProveedor(JComboBox jComboBox1, JTextField txtBuscarProveedor){
    try{
        if(conector.conectar()){
           consultaSQL="SELECT * FROM tblproveedores WHERE idproveedor LIKE '%"+txtBuscarProveedor.getText()+"%' "
                                                      +"OR nombre LIKE '%"+txtBuscarProveedor.getText()+"%' ";
           ResultSet resultado=conector.seleccionar(consultaSQL);
           DefaultComboBoxModel combobox=new DefaultComboBoxModel();
           jComboBox1.setModel(combobox);
           while(resultado.next()){
               combobox.addElement(new ComboProveedor(resultado.getString("idproveedor"),
                                                  resultado.getString("nombre"),
                                                  resultado.getString("direccion"),
                                                  resultado.getString("telefonos"),
                                                  resultado.getString("email")
                                                  ));
           }
           conector.desconectar();
        }
    }catch(SQLException sqle){
       System.out.println("Error...");
       System.err.println(sqle.getMessage()); 
    }
}
}
