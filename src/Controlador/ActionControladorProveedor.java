
package Controlador;

import Modelo.OperacionesProveedores;
import Modelo.util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.Proveedores;

/**
 *
 * @author Aldair
 */
public class ActionControladorProveedor implements ActionListener{
    OperacionesProveedores operaciones=new OperacionesProveedores();
    Proveedores vtProveedor=new Proveedores();
    int idproveedor;
    String nombre;
    String direccion;
    String telefonos;
    String email;
    
    public ActionControladorProveedor(Proveedores vtProveedor,OperacionesProveedores operaciones){
        this.vtProveedor=vtProveedor;
        this.operaciones=operaciones;
        this.operaciones.obtenerCodigo(vtProveedor.txtIdProveedores);
        this.operaciones.llenarTabla(vtProveedor.tbProveedores);
        
        this.vtProveedor.txtIdProveedores.setEnabled(false);
        this.vtProveedor.btnGuardar.addActionListener(this);
        this.vtProveedor.btnAtras.addActionListener(this);
        this.vtProveedor.btnBuscar.addActionListener(this);
        this.vtProveedor.btnActualizar.addActionListener(this);
        this.vtProveedor.btnEliminar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vtProveedor.btnGuardar){
            validarCampos();
        }
        if(e.getSource()==vtProveedor.btnAtras){
            vtProveedor.dispose();
        }
        
        if(e.getSource()==vtProveedor.btnActualizar){
            validarActualizacion();
        }
        
        if(e.getSource()==vtProveedor.btnBuscar){
            String codigo;
            int variable=vtProveedor.tbProveedores.getSelectedRow();
            if(variable==-1){
                JOptionPane.showMessageDialog(null,"Selecciones Un Proveedor");
            }else{
                codigo=vtProveedor.tbProveedores.getValueAt(variable, 0).toString();
                if(operaciones.buscarDatos(codigo,vtProveedor.txtIdProveedores,vtProveedor.txtNombre,vtProveedor.txtDireccion,vtProveedor.txtTelefonos,vtProveedor.txtEmail)){
                    vtProveedor.btnGuardar.setEnabled(false);
                }else{
                    util.informar(this.vtProveedor,"Error.. Al Buscar Los Datos", vtProveedor.getTitle());
                }
            }
        }
        
        if(e.getSource()==vtProveedor.btnEliminar){
            validarEliminado();
        }
    }
    
    public void obtenerDatosGUI(){
        idproveedor=Integer.parseInt(vtProveedor.txtIdProveedores.getText().trim().toUpperCase());
        nombre=vtProveedor.txtNombre.getText().trim().toUpperCase();
        direccion=vtProveedor.txtDireccion.getText().trim().toUpperCase();
        telefonos=vtProveedor.txtTelefonos.getText().trim().toUpperCase();
        email=vtProveedor.txtEmail.getText().trim().toUpperCase();
    }
    
    public boolean validarCampos(){
        boolean estado=false;
        if(vtProveedor.txtIdProveedores.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Datos Vacio");
            vtProveedor.txtIdProveedores.requestFocus();
        }else{
            if(vtProveedor.txtNombre.getText().length()==0){
                JOptionPane.showMessageDialog(null, "Datos Vacio");
                vtProveedor.txtNombre.requestFocus();
            }else{
                if(vtProveedor.txtDireccion.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "Datos Vacio");
                    vtProveedor.txtDireccion.requestFocus();
                }else{
                    if(vtProveedor.txtTelefonos.getText().length()==0){
                        JOptionPane.showMessageDialog(null, "Datos Vacio");
                        vtProveedor.txtTelefonos.requestFocus();
                    }else{
                        if(vtProveedor.txtEmail.getText().length()==0){
                            JOptionPane.showMessageDialog(null, "Datos Vacio");
                            vtProveedor.txtTelefonos.requestFocus();
                        }else{
                            obtenerDatosGUI();
                            if(operaciones.guardarProveedor(idproveedor, nombre, direccion, telefonos, email)){
                                util.informar(this.vtProveedor,"Datos Guardados Exitosamente",vtProveedor.getTitle());
                                operaciones.obtenerCodigo(vtProveedor.txtIdProveedores);
                                vtProveedor.txtNombre.setText("");
                                vtProveedor.txtDireccion.setText("");
                                vtProveedor.txtTelefonos.setText("");
                                vtProveedor.txtEmail.setText("");
                                operaciones.llenarTabla(vtProveedor.tbProveedores);
                            }
                        }
                    }
                }
            }
        }
        return estado;
    }
    
    public void validarActualizacion(){
        int nit=vtProveedor.txtIdProveedores.getText().length();
        int nom=vtProveedor.txtNombre.getText().length();
        int dire=vtProveedor.txtDireccion.getText().length();
        int tele=vtProveedor.txtTelefonos.getText().length();
        int ema=vtProveedor.txtEmail.getText().length();
        if(nit==0||nom==0||dire==0||tele==0||ema==0){
            JOptionPane.showMessageDialog(null,"Campos Vacios, Por Favor Escoja Un Cliente");
        }else{
            obtenerDatosGUI();
            if(operaciones.actualizarDatos(idproveedor, nombre, direccion, telefonos, email)){
                util.informar(this.vtProveedor,"Datos Actualizados Correctamente",vtProveedor.getTitle());
                vtProveedor.txtNombre.setText("");
                vtProveedor.txtDireccion.setText("");
                vtProveedor.txtTelefonos.setText("");
                vtProveedor.txtEmail.setText("");
                operaciones.obtenerCodigo(vtProveedor.txtIdProveedores);
                operaciones.llenarTabla(vtProveedor.tbProveedores);
                vtProveedor.btnGuardar.setEnabled(true);
            }
        }
    }

    private void validarEliminado() {
        int max=0;
        for(int i=0;i<vtProveedor.tbProveedores.getRowCount();i++){
            max=(int)vtProveedor.tbProveedores.getValueAt(i, 0);
        }
        int cod=Integer.parseInt(vtProveedor.txtIdProveedores.getText());
        if(cod>max){
            JOptionPane.showMessageDialog(null,"Campos Vacios, Por Favor Escoja Un Proveedor");
        }else{
            obtenerDatosGUI();
            if(operaciones.EliminarDatos(idproveedor)){
                util.informar(this.vtProveedor,"Datos Eliminados", vtProveedor.getTitle());
                operaciones.llenarTabla(vtProveedor.tbProveedores);
                vtProveedor.btnGuardar.setEnabled(true);
                operaciones.obtenerCodigo(vtProveedor.txtIdProveedores);
                vtProveedor.txtNombre.setText("");
                vtProveedor.txtDireccion.setText("");
                vtProveedor.txtTelefonos.setText("");
                vtProveedor.txtEmail.setText("");
            }
        }
    }
    
}
