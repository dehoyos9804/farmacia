
package Controlador;

import Modelo.OperacionesInventarioInicial;
import Modelo.util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import vista.InventarioInicial;

/**
 *
 * @author Aldair
 */
public class ActionControladorInventarioI implements ActionListener{
    String fechaentrada;
    int codproducto;
    int cantidad;
    int preciocompra;
    int codcompra;
    InventarioInicial vtInventarioI=new InventarioInicial();
    OperacionesInventarioInicial operaciones=new OperacionesInventarioInicial();
    
    public ActionControladorInventarioI(InventarioInicial vtInventarioI,OperacionesInventarioInicial operaciones){
        this.vtInventarioI=vtInventarioI;
        this.operaciones=operaciones;
        this.vtInventarioI.jButtonActualizar.setEnabled(false);
        operaciones.llenarTabla(vtInventarioI.TableInventarionicial);
        this.vtInventarioI.jButtonAdicionar.addActionListener(this);
        this.vtInventarioI.jButtonEditarFila.addActionListener(this);
        this.vtInventarioI.jButtonActualizar.addActionListener(this);
        this.vtInventarioI.jButtonLimpiar.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vtInventarioI.jButtonAdicionar){
            obtenerDatosGUI();
            if(operaciones.guardarInventario(fechaentrada)){
                operaciones.guardarDetalleInvetario(codproducto, cantidad, preciocompra);
                operaciones.llenarTabla(vtInventarioI.TableInventarionicial);
                vtInventarioI.DateFechaEntrada.setDate(new Date());
                vtInventarioI.comboProductos.setSelectedIndex(0);
                vtInventarioI.txtBuscador.setText("");
            }else{
               util.informar(this.vtInventarioI,"Error.. Producto En Inventario",vtInventarioI.getTitle());
            }
            
        }
        
        if(e.getSource()==vtInventarioI.jButtonEditarFila){
            ActualizarInventario();
        }
        
        if(e.getSource()==vtInventarioI.jButtonActualizar){
            Actualizar();
        }
        
        if(e.getSource()==vtInventarioI.jButtonLimpiar){
             vtInventarioI.DateFechaEntrada.setDate(new Date());
             vtInventarioI.comboProductos.setSelectedIndex(0);
             vtInventarioI.txtBuscador.setText("");
             vtInventarioI.jButtonAdicionar.setEnabled(true);
             vtInventarioI.jButtonActualizar.setEnabled(false);
        }
    }
    public void obtenerDatosGUI(){
        ComboMedicamento com=(ComboMedicamento) vtInventarioI.comboProductos.getSelectedItem();
        fechaentrada=util.aFechaMYSQL(vtInventarioI.DateFechaEntrada.getDate());
        codproducto=Integer.parseInt(com.getIdmedicamento());
        cantidad=Integer.parseInt(vtInventarioI.txtCantidadCompra.getText());
        preciocompra=Integer.parseInt(vtInventarioI.txtCostoCompra.getText());
        
    }

    private void ActualizarInventario(){
        if(vtInventarioI.TableInventarionicial.getSelectedRow()==-1){
            JOptionPane.showMessageDialog(null,"Escoja Una fila");
        }else{
            int codcompras=Integer.parseInt(vtInventarioI.TableInventarionicial.getValueAt(vtInventarioI.TableInventarionicial.getSelectedRow(), 6).toString());
            int codmedic=Integer.parseInt(vtInventarioI.TableInventarionicial.getValueAt(vtInventarioI.TableInventarionicial.getSelectedRow(), 1).toString());
            operaciones.buscarInventario(codcompras, codmedic, vtInventarioI.DateFechaEntrada,vtInventarioI.comboProductos,vtInventarioI.txtCantidadCompra, vtInventarioI.txtCostoCompra);
            vtInventarioI.jButtonAdicionar.setEnabled(false);
            vtInventarioI.jButtonActualizar.setEnabled(true);
            vtInventarioI.txtCantidadCompra.requestFocus();
        }
    }

    private void Actualizar() {
        if(vtInventarioI.DateFechaEntrada.getDate().toString().length()==0){
            JOptionPane.showMessageDialog(null, "Campo Vacio");
        }else{
            if(vtInventarioI.txtCantidadCompra.getText().length()==0){
                JOptionPane.showMessageDialog(null, "Campo Vacio");
                vtInventarioI.txtCantidadCompra.requestFocus();
            }else{
                if(vtInventarioI.txtCostoCompra.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "Campo Vacio");
                    vtInventarioI.txtCostoCompra.requestFocus();
                }else{
                    codcompra=Integer.parseInt(vtInventarioI.TableInventarionicial.getValueAt(vtInventarioI.TableInventarionicial.getSelectedRow(), 6).toString());
                    obtenerDatosGUI();
                    if(operaciones.actualizarInventario(fechaentrada, codcompra)){
                        operaciones.actualizarDetalleInventario(codcompra, cantidad, preciocompra);
                        operaciones.llenarTabla(vtInventarioI.TableInventarionicial);
                        vtInventarioI.DateFechaEntrada.setDate(new Date());
                        vtInventarioI.comboProductos.setSelectedIndex(0);
                        vtInventarioI.txtBuscador.setText("");
                       
                    }
                }
            }
        }
    }
    
}
