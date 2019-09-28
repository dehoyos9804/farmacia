
package Controlador;

import Modelo.OperacionesVentas;
import Modelo.util;
import Reportes.AstractJasperReport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.Ventas;

/**
 *
 * @author Aldair
 */
public class ActionControladorVentas implements ActionListener{
   //instancio  la ventana venta
   Ventas vtVentas=new Ventas();
   //instancio el modelo operador venta
    OperacionesVentas operaciones=new OperacionesVentas();
    String idventa;
    String fechaventa;
    String codvendedores;
    String codcliente;
    String codventa;
    String codmedicamento;
    String cantidad;
    String preciounidad;
    int cedula;
    DefaultTableModel model;
    Icon icono;
    String data[][]={};
    String columnas[]={"Identicacion","Producto","Cantidad","Precio","Total Pagar"};
    
    //constructor
    public ActionControladorVentas(Ventas vtVentas,OperacionesVentas operaciones,int cedula){
        this.vtVentas=vtVentas;
        this.operaciones=operaciones;
        this.cedula=cedula;
        model=new DefaultTableModel(data,columnas){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        vtVentas.jtDetalle.setModel(model);
        
        seleccionarvendedor(cedula);
        
        //agrego escuchador a botones
        this.vtVentas.jButtonGuardar.addActionListener(this);
        this.vtVentas.jcElegirCliente.addActionListener(this);
        this.vtVentas.jButtonAdicionar.addActionListener(this);
        this.vtVentas.jButtonActualizar.addActionListener(this);
        this.vtVentas.jButtonEditarFila.addActionListener(this);
        this.vtVentas.jButtonImprimir.addActionListener(this);
        this.vtVentas.jButtonEli.addActionListener(this);
        this.vtVentas.jButtonLimpiar.addActionListener(this);
        this.vtVentas.jButtonAtras.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==vtVentas.jButtonAdicionar){
            validarDetalle();
        }
        
        if(e.getSource()==vtVentas.jButtonActualizar){
            validarActualizacion();
        }
        if(e.getSource()==vtVentas.jButtonEditarFila){
            actualizarDetalle();
        }
        if(e.getSource()==vtVentas.jButtonImprimir){
            String datos = JOptionPane.showInputDialog("Digite El Codigo de la venta");
            operaciones.generarReporte(datos);
            AstractJasperReport.ejecutarReporte();
        }
        
        if(e.getSource()==vtVentas.jButtonEli){
             if(vtVentas.jtDetalle.getSelectedRow()==-1){
           JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
       }else{
        DefaultTableModel dtm=(DefaultTableModel) vtVentas.jtDetalle.getModel();
        dtm.removeRow(vtVentas.jtDetalle.getSelectedRow());  
        if(vtVentas.jtDetalle.getRowCount()==-1){
           vtVentas.jtSubtotal.setText("0");
           vtVentas.jtIva.setText("0");
           vtVentas.jtTotal.setText("0");
           vtVentas.jtTotalPagar.setText("0");
           vtVentas.jtsubtotal.setText("0");
           vtVentas.jtTotalDevolver.setText("0");  
        }else{
          llenarValoresTransversales();
        }
       
       }
        
        }
        
        if(e.getSource()==vtVentas.jButtonLimpiar){
            vtVentas.jcMedicamentos.setSelectedIndex(0);
            vtVentas.jtCantidadVender.setText("");
            vtVentas.jtVAlorNeto.setText("");
            vtVentas.jtBuscador.setText("");
            vtVentas.jButtonActualizar.setEnabled(false);
            vtVentas.jButtonAdicionar.setEnabled(true);
        }
        
        if(e.getSource()==vtVentas.jButtonAtras){
            vtVentas.dispose();
        }
        
        if(e.getSource()==vtVentas.jButtonGuardar){
        obtenerDatosGUI();
        if(validarVentas()){
         operaciones.guardarDetalle(codventa, codmedicamento, cantidad, preciounidad, vtVentas.jtIdventa, vtVentas.jtDetalle);
         util.informar(this.vtVentas, "Datos Guardados Correctamento", vtVentas.getTitle());
         vtVentas.jtCc.setText("");
         vtVentas.jtNombre.setText("");
         vtVentas.jtApellidos.setText("");
         vtVentas.jtCorreoElectronico.setText("");
         operaciones.generarCodigo(vtVentas.jtIdventa);
         vtVentas.jdFechaVenta.setDate(new Date());
         vtVentas.jtCantidadVender.setText("");
         vtVentas.jtVAlorNeto.setText("");
         vtVentas.jtCantidad.setText("");
         vtVentas.jtBuscador.setText("");
         vtVentas.jtCantidadVender.requestFocus();
         model=new DefaultTableModel(data,columnas){
             public boolean isCellEditable(int row,int column){
                 return false;
             }
         };
         vtVentas.jtDetalle.setModel(model);
         operaciones.obtenerMedicamentos(vtVentas.jcMedicamentos);
         vtVentas.jtSubtotal.setText("0");
         vtVentas.jtIva.setText("0");
         vtVentas.jtTotal.setText("0");
         vtVentas.jtTotalPagar.setText("0");
         vtVentas.jtsubtotal.setText("0");
         vtVentas.jtTotalDevolver.setText("0");
         int confirmar=JOptionPane.showConfirmDialog(null,"¿Desea Imprimir Factura?","Confirmar",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE,icono);
         if(confirmar==0){
            operaciones.generarReporte(codventa);
            AstractJasperReport.ejecutarReporte();  
         }
       }
        }
        
    }
    public void obtenerDatosGUI(){
    idventa=vtVentas.jtIdventa.getText().trim();
    fechaventa=util.aFechaMYSQL(vtVentas.jdFechaVenta.getDate());
    codvendedores=vtVentas.jtCc.getText().trim();
    codcliente=vtVentas.jtcedula.getText().trim();
    codventa=vtVentas.jtIdventa.getText().trim();
    
}
    
    public boolean validarVentas(){
    boolean estado=false;
    if(vtVentas.jtCc.getText().length()==0){
        JOptionPane.showMessageDialog(null, "Escoja un vendedor");
    }else{
        if(vtVentas.jtIdventa.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Digite el Numero de Factura");
        }else{
            if(vtVentas.jtcedula.getText().length()==0){
                JOptionPane.showMessageDialog(null,"Escoja un cliente");
            }else{
                if(vtVentas.jtDetalle.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"No Hay Productos Para Realizar La Venta");
                }else{
                    operaciones.guardarVentas(idventa,fechaventa,codvendedores,codcliente);
                    estado=true;
                }
            }
        }
    }
    return estado;
}

public void validarDetalle(){
    
    if(vtVentas.jtIdMedicamento.getText().length()==0){
        JOptionPane.showMessageDialog(null, "El Codigo No Debe Quedar Vacio");
    }else{
        if(vtVentas.jcMedicamentos.getSelectedItem().toString().length()==0){
         JOptionPane.showMessageDialog(null, "Escoja un producto a Vender");   
        }else{
            if(vtVentas.jtCantidad.getText().length()==0){
               JOptionPane.showMessageDialog(null, "La Cantidad No Puede Quedar Vacio"); 
            }else{
                if(vtVentas.jtCantidadVender.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "Digite La Cantidad De Producto Que Desea Vender");
                    vtVentas.jtCantidadVender.requestFocus();
                    
                }else{
                    if(vtVentas.jtPrecios.getText().length()==0){
                        JOptionPane.showMessageDialog(null, "Digite el precio Del Producto");
                        vtVentas.jtPrecios.requestFocus();
                    }else{
                        if(vtVentas.jtVAlorNeto.getText().length()==0){
                            JOptionPane.showMessageDialog(null, "El Precio No Debe Quedar Vacio");
                        }else{
                            int cantidadbodega;
                            int cantidadvender;
                            cantidadbodega=Integer.parseInt(vtVentas.jtCantidad.getText());
                            cantidadvender=Integer.parseInt(vtVentas.jtCantidadVender.getText());
                            if(cantidadvender>cantidadbodega){
                                    JOptionPane.showMessageDialog(null, "No Puede Vender Mas De Lo Que Hay en Bodega");
                            }else{
                              validarFilasRepetidas();
                              vtVentas.jcMedicamentos.setSelectedIndex(0);
                              vtVentas.jtCantidadVender.setText("");
                              vtVentas.jtVAlorNeto.setText("");
                              vtVentas.jtBuscador.setText("");
                              vtVentas.jtCantidadVender.requestFocus();
                            }
                            
                        }
                    }
                }
            }
        }
    }
}

public void llenarDetalle(){
    try{
      String filas[]={vtVentas.jtIdMedicamento.getText().trim(),vtVentas.jcMedicamentos.getSelectedItem().toString(),
                   vtVentas.jtCantidadVender.getText().trim(),vtVentas.jtPrecios.getText().trim(),vtVentas.jtVAlorNeto.getText().trim()};
      model.addRow(filas);
      int[] ancho={2,150,20,20,20};
      for(int i=0;i<vtVentas.jtDetalle.getColumnCount();i++){
          vtVentas.jtDetalle.getColumnModel().getColumn(i).setPreferredWidth(ancho[i]);
      }
      llenarValoresTransversales();
    }catch (Exception e){
      System.out.println("Error...");
      System.err.println(e.getMessage());  
    }
}

public void llenarValoresTransversales(){
    double contador=0;
      for(int i=0;i<vtVentas.jtDetalle.getRowCount();i++){
          contador=contador+Double.parseDouble(vtVentas.jtDetalle.getValueAt(i, 4).toString());
      }
      vtVentas.jtSubtotal.setText(String.valueOf(contador));
      vtVentas.jtTotal.setText(String.valueOf(contador));
      vtVentas.jtTotalPagar.setText(String.valueOf(contador));
      vtVentas.jtsubtotal.setText(String.valueOf(contador));
      double totalpagar=Double.parseDouble(vtVentas.jtTotalPagar.getText().trim());
      double totalneto=(totalpagar-contador);
      vtVentas.jtTotalDevolver.setText(String.valueOf(totalneto));
}

public boolean validarFilasRepetidas(){
    boolean estado=false;
    String cod=vtVentas.jtIdMedicamento.getText().trim();//obtengo el valor del jtcodigo 
    int new_cantidad=Integer.parseInt(vtVentas.jtCantidadVender.getText().trim());//obtengo la nueva cantidad que deseo vender
    int new_precio=Integer.parseInt(vtVentas.jtPrecios.getText().trim());
    //recorrro  todas las filas de jtable
    for(int i=0;i<vtVentas.jtDetalle.getRowCount();i++){
        //si se cumple la condición de que, el codigo es igual a lo que esta registrado en la tabla
      if(vtVentas.jtDetalle.getValueAt(i, 0).equals(cod)){
          int old_cantidad=Integer.parseInt(vtVentas.jtDetalle.getValueAt(i, 2).toString());//obtengo la cantidad que se encuentra registrado en la tabla
          int suma=new_cantidad+old_cantidad;//sumo la cantidad nueva con la que estaba registrada en la tabla
          vtVentas.jtDetalle.setValueAt(suma, i, 2);//actualizo la celda de cantidad
          vtVentas.jtDetalle.setValueAt(new_precio, i, 3);
          vtVentas.jtDetalle.setValueAt(suma*new_precio, i, 4);//actualizo la celda valor neto
          estado=true;
      }  
    }
    llenarValoresTransversales();
    if(estado==false){
        llenarDetalle();
        
    }
    return estado;
}

public void actualizaDetalle(){
    if(vtVentas.jtDetalle.getRowCount()==0){
       JOptionPane.showMessageDialog(null, "Utilice El Boton Adicionar"); 
    }
    String cod=vtVentas.jtIdMedicamento.getText().trim();
    for(int i=0;i<vtVentas.jtDetalle.getRowCount();i++){
        if(vtVentas.jtDetalle.getValueAt(i, 0).equals(cod)){
            vtVentas.jtDetalle.setValueAt(cod,i, 0);
            vtVentas.jtDetalle.setValueAt(vtVentas.jcMedicamentos.getSelectedItem().toString(), i, 1);
            vtVentas.jtDetalle.setValueAt(vtVentas.jtCantidadVender.getText().trim(), i, 2);
            vtVentas.jtDetalle.setValueAt(vtVentas.jtPrecios.getText().trim(), i, 3);
            vtVentas.jtDetalle.setValueAt(vtVentas.jtVAlorNeto.getText().trim(), i, 4);
            
        }
        vtVentas.jButtonActualizar.setEnabled(false);
        vtVentas.jButtonAdicionar.setEnabled(true);
        llenarValoresTransversales();
    }
    
}

public void validarActualizacion(){
  if(vtVentas.jtIdMedicamento.getText().length()==0){
        JOptionPane.showMessageDialog(null, "El Codigo No Debe Quedar Vacio");
    }else{
        if(vtVentas.jcMedicamentos.getSelectedItem().toString().length()==0){
         JOptionPane.showMessageDialog(null, "Escoja un producto a Vender");   
        }else{
            if(vtVentas.jtCantidad.getText().length()==0){
               JOptionPane.showMessageDialog(null, "La Cantidad No Puede Quedar Vacio"); 
            }else{
                if(vtVentas.jtCantidadVender.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "Digite La Cantidad De Producto Que Desea Vender");
                    vtVentas.jtCantidadVender.requestFocus();
                    
                }else{
                    if(vtVentas.jtPrecios.getText().length()==0){
                        JOptionPane.showMessageDialog(null, "Digite el precio Del Producto");
                        vtVentas.jtPrecios.requestFocus();
                    }else{
                        if(vtVentas.jtVAlorNeto.getText().length()==0){
                            JOptionPane.showMessageDialog(null, "El Precio No Debe Quedar Vacio");
                        }else{
                            int cantidadbodega;
                            int cantidadvender;
                            cantidadbodega=Integer.parseInt(vtVentas.jtCantidad.getText());
                            cantidadvender=Integer.parseInt(vtVentas.jtCantidadVender.getText());
                            if(cantidadvender>cantidadbodega){
                                    JOptionPane.showMessageDialog(null, "No Puede Vender Mas De Lo Que Hay en Bodega");
                            }else{
                                  actualizaDetalle();
                                  vtVentas.jcMedicamentos.setSelectedIndex(0);
                                  vtVentas.jtCantidadVender.setText("");
                                  vtVentas.jtVAlorNeto.setText("");   
                            }
                            
                        }
                    }
                }
            }
        }
    }   
}

public void actualizarDetalle(){
   // DefaultTableModel model=(DefaultTableModel) jtDetalle.getModel();
    try{
       if(vtVentas.jtDetalle.getSelectedRow()==-1){
           
        JOptionPane.showMessageDialog(null, "seleccione una fila");
       }else{
           vtVentas.jtBuscador.setText("");
           if(vtVentas.jtDetalle.getSelectedRow()>=0){
              String nombre=vtVentas.jtDetalle.getValueAt(vtVentas.jtDetalle.getSelectedRow(), 1).toString();
              vtVentas.jcMedicamentos.setSelectedItem(new ComboMedicamento(null,nombre,null,null,null));
              vtVentas.jtCantidadVender.setText(vtVentas.jtDetalle.getValueAt(vtVentas.jtDetalle.getSelectedRow(), 2).toString());
              vtVentas.jtPrecios.setText(vtVentas.jtDetalle.getValueAt(vtVentas.jtDetalle.getSelectedRow(), 3).toString());
              vtVentas.jtVAlorNeto.setText(vtVentas.jtDetalle.getValueAt(vtVentas.jtDetalle.getSelectedRow(), 4).toString());
              vtVentas.jButtonAdicionar.setEnabled(false);
              vtVentas.jButtonActualizar.setEnabled(true);
              vtVentas.jtCantidadVender.requestFocus();
           }  
       } 
    }catch (Exception e){
        System.out.println("Error");
        System.err.println(e.getMessage());
    }
}

public void seleccionarvendedor(int ced){
        System.out.println(ced);
        for(int i=0;i<vtVentas.jTableVendedores.getRowCount();i++){
            if(vtVentas.jTableVendedores.getValueAt(i, 0).equals(ced)){
                vtVentas.jTableVendedores.changeSelection(i,0,false,false); 
            }
        }
        int variable=vtVentas.jTableVendedores.getSelectedRow();
        String codusuario=vtVentas.jTableVendedores.getValueAt(variable, 0).toString();
        operaciones.buscarImagenVenta(codusuario, vtVentas.lblFoto);
        vtVentas.jtCc.setText(vtVentas.jTableVendedores.getValueAt(variable, 0).toString());
        vtVentas.jtNombre.setText(vtVentas.jTableVendedores.getValueAt(variable, 1).toString());
        vtVentas.jtApellidos.setText(vtVentas.jTableVendedores.getValueAt(variable, 2).toString());
        vtVentas.jtCorreoElectronico.setText(vtVentas.jTableVendedores.getValueAt(variable, 3).toString());
        
    }

}
