
package Controlador;

import Modelo.OperacionesCompras;
import Modelo.util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.Compras;

/**
 *
 * @author Aldair
 */
public class ActionControladorCompras implements ActionListener{
   Compras vtCompras=new Compras();
   OperacionesCompras operaciones=new OperacionesCompras();
   String idcompra;
   String fechacompra;
   String codproveedor;
   String codmedicamento;
   String cantidad;
   String preciocompra;
   DefaultTableModel model;
   String data[][]={};
   String columnas[]={"Codigo","Producto","Cantidad","Precio","Total Pagar"};
   
   public ActionControladorCompras(Compras vtCompras,OperacionesCompras operaciones){
       this.vtCompras=vtCompras;
       this.operaciones=operaciones;
       
       model=new DefaultTableModel(data,columnas){
            public boolean isCellEditable(int row,int Column){
                return false;
            }
        };
       this.vtCompras.jtDetalleCompra.setModel(model);
       this.vtCompras.jButtonAdicionar.addActionListener(this);
       this.vtCompras.jButtonActualizar.addActionListener(this);
       this.vtCompras.jButtonLimpiar.addActionListener(this);
       this.vtCompras.jButtonAtras.addActionListener(this);
       this.vtCompras.jButtonGuardar.addActionListener(this);
       this.vtCompras.jButtonEditar.addActionListener(this);
       this.vtCompras.jButtonEliminar.addActionListener(this);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vtCompras.jButtonAdicionar){
            validarDetalleCompra();
        }
        if(e.getSource()==vtCompras.jButtonActualizar){
            validarActualizacionDetalle();
        }
        if(e.getSource()==vtCompras.jButtonLimpiar){
          operaciones.obtenerMedicamento(vtCompras.jcMedicamentos);
          vtCompras.txtBuscador.setText("");
        }
        
        if(e.getSource()==vtCompras.jButtonAtras){
            vtCompras.dispose();
        }
        if(e.getSource()==vtCompras.jButtonGuardar){
            obtenerDatosGUI();
            if(validarGuardado()){
                util.informar(null,"Datos Guardados Correctamente", vtCompras.getTitle());
                operaciones.numeroFactura(vtCompras.txtNumeroFactura);
                vtCompras.jdFechaCompra.setDate(new Date());
                vtCompras.txtBuscarProveedor.setText("");
                operaciones.obtenerProveedor(vtCompras.jComboBox1);
                vtCompras.txtBuscador.setText("");
                operaciones.obtenerMedicamento(vtCompras.jcMedicamentos);
                vtCompras.jcMedicamentos.setSelectedIndex(0);
                model=new DefaultTableModel(data,columnas){
                    public boolean isCellEditable(int row,int Column){
                        return false;
                    }
                };
                vtCompras.jtDetalleCompra.setModel(model);
                vtCompras.txtTotalNeto.setText("00");
                vtCompras.txtSubtotal.setText("00");
                vtCompras.txtDescuento.setText("00");
            }
        }
        
        if(e.getSource()==vtCompras.jButtonEditar){
            editarDetalleCompra();
        }
        
        if(e.getSource()==vtCompras.jButtonEliminar){
            eliminarFilas();
        }
    }
    
//metodo para obtener los valores de los jtextfield, jcombobox,jdate, etc
 public void obtenerDatosGUI(){
     ComboProveedor combo=(ComboProveedor) vtCompras.jComboBox1.getSelectedItem();
     idcompra=vtCompras.txtNumeroFactura.getText().trim().toUpperCase();
     fechacompra=util.aFechaMYSQL(vtCompras.jdFechaCompra.getDate());
     codproveedor=combo.getIdproveedor().toUpperCase();
     codmedicamento=vtCompras.txtCodigo.getText().trim().toUpperCase();
     cantidad=vtCompras.txtCantidadCompra.getText().trim().toUpperCase();
     preciocompra=vtCompras.txtPrecioCompra.getText().trim().toUpperCase();
 }
 
 
 //metodo que Llena la tabla de detalle con las entradas
 public void llenarDetalle(){
     try{
         String filas[]={vtCompras.txtCodigo.getText().trim(),vtCompras.jcMedicamentos.getSelectedItem().toString(),
                        vtCompras.txtCantidadCompra.getText().trim(),vtCompras.txtPrecioCompra.getText().trim(),
                        vtCompras.txtValorNeto.getText().trim()};
         vtCompras.jtDetalleCompra.setModel(model);
         model.addRow(filas);
         int ancho[]={10,150,20,20,20};
         for(int i=0;i<vtCompras.jtDetalleCompra.getColumnCount()-1;i++){
             vtCompras.jtDetalleCompra.getColumnModel().getColumn(i).setPreferredWidth(ancho[i]);
         }
         llenarValores();
     }catch (Exception e){
         System.out.println("Error.. mirar ");
         System.err.println(e.getMessage());
     }

}
 
 public void llenarValores(){
     float contador=0;
     for(int i=0;i<vtCompras.jtDetalleCompra.getRowCount();i++){
         contador=contador+Float.parseFloat(vtCompras.jtDetalleCompra.getValueAt(i, 4).toString());
     }
     vtCompras.txtTotalNeto.setText(String.valueOf(contador));
     vtCompras.txtDescuento.setText("00,0");
     vtCompras.txtSubtotal.setText(String.valueOf(contador));
 }
 
 public boolean validarFilasRepetidas(){
     boolean estado=false;
     String codi=vtCompras.txtCodigo.getText().trim();
     int new_cantidad=Integer.parseInt(vtCompras.txtCantidadCompra.getText().trim());
     int new_precio=Integer.parseInt(vtCompras.txtPrecioCompra.getText().trim());
     for(int i=0;i<vtCompras.jtDetalleCompra.getRowCount();i++){
         if(codi.equals(vtCompras.jtDetalleCompra.getValueAt(i, 0))){
             int old_cantidad=Integer.parseInt(vtCompras.jtDetalleCompra.getValueAt(i, 2).toString());
             int suma=new_cantidad+old_cantidad;
             vtCompras.jtDetalleCompra.setValueAt(suma, i, 2);
             vtCompras.jtDetalleCompra.setValueAt(new_precio, i, 3);
             vtCompras.jtDetalleCompra.setValueAt(suma*new_precio, i, 4);
             estado=true;
         }
     }
     llenarValores();
     if(estado==false){
      llenarDetalle();
     }
     return estado;
 }
 
 //Metodo para Validar el detalle compra
 public void validarDetalleCompra(){
     if(vtCompras.txtCodigo.getText().length()==0){
         JOptionPane.showMessageDialog(null,"Campo Vacio");
         vtCompras.txtCodigo.requestFocus();
     }else{
         if(vtCompras.jcMedicamentos.getSelectedItem().toString().length()==0){
           JOptionPane.showMessageDialog(null,"Campo Vacio");
           vtCompras.jcMedicamentos.requestFocus();
         }else{
             if(vtCompras.txtCantidadCompra.getText().length()==0){
                  JOptionPane.showMessageDialog(null,"Campo Vacio");
                  vtCompras.txtCantidadCompra.requestFocus();
             }else{
                 if(vtCompras.txtPrecioCompra.getText().length()==0){
                      JOptionPane.showMessageDialog(null,"Campo Vacio");
                      vtCompras.txtPrecioCompra.requestFocus();
                 }else{
                     if(vtCompras.txtValorNeto.getText().length()==0){
                          JOptionPane.showMessageDialog(null,"Campo Vacio");
                         vtCompras.txtValorNeto.requestFocus();
                     }else{
                         validarFilasRepetidas();
                         vtCompras.jcMedicamentos.setSelectedIndex(0);
                         vtCompras.txtBuscador.setText("");
                     }
                 }
             }
         }
     }
 }
 
 //metodo para actualizar el detalle compra
 public void actualizarDetalleCompra(){
     String codigo=vtCompras.txtCodigo.getText().trim();
     for(int i=0;i<vtCompras.jtDetalleCompra.getRowCount();i++){
         if(vtCompras.jtDetalleCompra.getValueAt(i, 0).equals(codigo)){
             vtCompras.jtDetalleCompra.setValueAt(codigo, i, 0);
             vtCompras.jtDetalleCompra.setValueAt(vtCompras.jcMedicamentos.getSelectedItem().toString(), i, 1);
             vtCompras.jtDetalleCompra.setValueAt(vtCompras.txtCantidadCompra.getText().trim(), i, 2);
             vtCompras.jtDetalleCompra.setValueAt(vtCompras.txtPrecioCompra.getText().trim(), i, 3);
             vtCompras.jtDetalleCompra.setValueAt(vtCompras.txtValorNeto.getText().trim(), i, 4);
         }
         vtCompras.jButtonAdicionar.setEnabled(true);
         vtCompras.jButtonActualizar.setEnabled(false);
         llenarValores();
     }
 }
 
 public void validarActualizacionDetalle(){
     if(vtCompras.txtCodigo.getText().length()==0){
         JOptionPane.showMessageDialog(null,"Campo Vacio");
         vtCompras.txtCodigo.requestFocus();
     }else{
         if(vtCompras.jcMedicamentos.getSelectedItem().toString().length()==0){
             JOptionPane.showMessageDialog(null,"Campo Vacio");
             vtCompras.jcMedicamentos.setPopupVisible(true);
         }else{
             if(vtCompras.txtCantidadCompra.getText().length()==0){
                 JOptionPane.showMessageDialog(null,"Campo Vacio");
                 vtCompras.txtCantidadCompra.requestFocus();
             }else{
                 if(vtCompras.txtPrecioCompra.getText().length()==0){
                     JOptionPane.showMessageDialog(null,"Campo Vacio");
                     vtCompras.txtPrecioCompra.requestFocus();
                 }else{
                     if(vtCompras.txtValorNeto.getText().length()==0){
                       JOptionPane.showMessageDialog(null,"Campo Vacio");
                       vtCompras.txtValorNeto.requestFocus();
                     }else{
                         actualizarDetalleCompra();
                         vtCompras.txtBuscador.setText("");
                         vtCompras.jcMedicamentos.setSelectedItem(0);
                     }
                 }
             }
         }
     }
 }
 public void editarDetalleCompra(){
     try{
         if(vtCompras.jtDetalleCompra.getSelectedRow()==-1){
             JOptionPane.showMessageDialog(null, "seleccione una fila");
         }else{
             vtCompras.txtBuscador.setText("");
             if(vtCompras.jtDetalleCompra.getSelectedRow()>=0){
                 String nombre=vtCompras.jtDetalleCompra.getValueAt(vtCompras.jtDetalleCompra.getSelectedRow(), 1).toString();
                 vtCompras.jcMedicamentos.setSelectedItem(new ComboMedicamento(null,nombre,null,null,null));
                 vtCompras.txtCantidadCompra.setText(vtCompras.jtDetalleCompra.getValueAt(vtCompras.jtDetalleCompra.getSelectedRow(), 2).toString());
                 vtCompras.txtPrecioCompra.setText(vtCompras.jtDetalleCompra.getValueAt(vtCompras.jtDetalleCompra.getSelectedRow(), 3).toString());
                 vtCompras.txtValorNeto.setText(vtCompras.jtDetalleCompra.getValueAt(vtCompras.jtDetalleCompra.getSelectedRow(), 4).toString());
                 vtCompras.txtCantidadCompra.requestFocus();
                 vtCompras.jButtonAdicionar.setEnabled(false);
                 vtCompras.jButtonActualizar.setEnabled(true);
             }
         }
     }catch (Exception e){
         System.out.println("Error..");
         System.err.println(e.getMessage());
     }
 }
 
 public void eliminarFilas(){
     try{
         if(vtCompras.jtDetalleCompra.getSelectedRow()==-1){
          JOptionPane.showMessageDialog(null, "Escoja Una Fila Para Eliminar");
         }else{
             DefaultTableModel model=(DefaultTableModel) vtCompras.jtDetalleCompra.getModel();
             model.removeRow(vtCompras.jtDetalleCompra.getSelectedRow());
             if(vtCompras.jtDetalleCompra.getRowCount()==-1){
                 vtCompras.txtTotalNeto.setText("0");
                 vtCompras.txtSubtotal.setText("0");
                 vtCompras.txtDescuento.setText("0");
             }else{
                 llenarValores();
             }
        }
     }catch (Exception e){
         System.out.println("Error..");
         System.err.println(e.getMessage());
     }
 }

    private boolean validarGuardado() {
        boolean estado=false;
        if(vtCompras.txtNumeroFactura.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Numero De Factura En Blanco");
            operaciones.numeroFactura(vtCompras.txtNumeroFactura);
        }else{
            if(vtCompras.jdFechaCompra.getDate().toString().length()==0){
                JOptionPane.showMessageDialog(null,"Feha No Valida");
                vtCompras.jdFechaCompra.setDate(new Date());
            }else{
                if(vtCompras.jComboBox1.getSelectedItem().toString().length()==0){
                    JOptionPane.showMessageDialog(null,"Escoja un Proveedor");
                    operaciones.obtenerProveedor(vtCompras.jComboBox1);
                }else{
                    if(vtCompras.jtDetalleCompra.getRowCount()==0){
                        JOptionPane.showMessageDialog(null,"No Hay Productos Para Entrar");
                        vtCompras.txtCantidadCompra.requestFocus();
                    }else{
                        if(operaciones.guardarCompra(idcompra, fechacompra, codproveedor, codmedicamento, cantidad, preciocompra, vtCompras.txtNumeroFactura, vtCompras.jtDetalleCompra)){
                            estado=true;
                        }else{
                            estado=false;
                            util.advertir(null,"Error Al Guardar Los Datos", vtCompras.getTitle());
                        }
                        
                    }
                }
            }
        }
        return estado;
    }    
   
    
}
