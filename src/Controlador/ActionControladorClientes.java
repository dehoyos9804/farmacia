
package Controlador;
import Modelo.*;
import vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Aldair
 */
public class ActionControladorClientes implements ActionListener{
   Clientes vistaCliente=new Clientes();
   OperacionesClientes operaciones=new OperacionesClientes();
    
   String cedula;
   String nombre;
   String telefonos;
   String direccion;
   
    public ActionControladorClientes(Clientes vistaCliente, OperacionesClientes operaciones) {
        this.vistaCliente=vistaCliente;
        this.operaciones=operaciones;
        
        //Agregar escuchadores A Botones, si no se le agrega no funciona
        this.vistaCliente.jButtonGuardar.addActionListener(this);
        this.vistaCliente.jButtonActualizar.addActionListener(this);
        this.vistaCliente.jButtonBuscar.addActionListener(this);
        this.vistaCliente.jButtonEliminar.addActionListener(this);
        this.vistaCliente.jButton6.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()==vistaCliente.jButtonGuardar){
           validarcampos();
       } 
      if(e.getSource()==vistaCliente.jButtonActualizar){
          validarActualizacion();
      }
      if(e.getSource()==vistaCliente.jButtonBuscar){
          String cc="";
        int variable=vistaCliente.jtClientes.getSelectedRow();
        System.out.println(variable);
        if(variable==-1){
            JOptionPane.showMessageDialog(null,"Por Favor Seleccione Un Vendedor");  
        }else{
            cc=vistaCliente.jtClientes.getValueAt(variable, 0).toString();
            if(operaciones.buscar(cc, vistaCliente.jtCedula,vistaCliente.jtNombres,vistaCliente.jtTelefonos,vistaCliente.jtDireccion)){
             vistaCliente.jButtonGuardar.setEnabled(false);
            }else{
                util.informar(this.vistaCliente,"Error.. No Se Encontraron Los Datos",vistaCliente.getTitle());
            }
        }
      }
      
      if(e.getSource()==vistaCliente.jButtonEliminar){
          if(vistaCliente.jtCedula.getText().length()==0){
         JOptionPane.showMessageDialog(null, "Digite el cliente que desea eliminar");
         vistaCliente.jtCedula.requestFocus();
         vistaCliente.jtNombres.setEnabled(false);
         vistaCliente.jtTelefonos.setEnabled(false);
         vistaCliente.jtDireccion.setEnabled(false);
        }else{
        obtenerDatosGUI();
        if(operaciones.eliminar(cedula)){
            util.informar(this.vistaCliente, "Datos Eliminados Correctamente", vistaCliente.getTitle());
            operaciones.llenarTabla(vistaCliente.jtClientes);
            vistaCliente.jButtonGuardar.setEnabled(true);
            vistaCliente.jtCedula.setText("");
            vistaCliente.jtNombres.setText("");
            vistaCliente.jtTelefonos.setText("");
            vistaCliente.jtDireccion.setText("");
            vistaCliente.jtNombres.setEnabled(true);
            vistaCliente.jtTelefonos.setEnabled(true);
            vistaCliente.jtDireccion.setEnabled(true);
        }else{
            util.advertir(this.vistaCliente, "Error.. Los Datos No Fueron Eliminados", vistaCliente.getTitle());
        }
        }
      }
      
      if(e.getSource()==vistaCliente.jButton6){
          vistaCliente.dispose();
      }
    }
    
public void obtenerDatosGUI(){
     cedula=vistaCliente.jtCedula.getText().trim().toUpperCase();
     nombre=vistaCliente.jtNombres.getText().trim().toUpperCase();
     telefonos=vistaCliente.jtTelefonos.getText().trim().toUpperCase();
     direccion=vistaCliente.jtDireccion.getText().trim().toUpperCase();
        
}
    
public void validarcampos(){
    if(vistaCliente.jtCedula.getText().length()==0){
        JOptionPane.showMessageDialog(null, "El Campo, No Puede Quedar Vacio");
        vistaCliente.jtCedula.requestFocus();
    }else{
        if(vistaCliente.jtNombres.getText().length()==0){
          JOptionPane.showMessageDialog(null, "El Campo, No Puede Quedar Vacio");
           vistaCliente.jtNombres.requestFocus(); 
        }else{
            if(vistaCliente.jtTelefonos.getText().length()==0){
                JOptionPane.showMessageDialog(null, "El Campo, No Puede Quedar Vacio");
                vistaCliente.jtTelefonos.requestFocus(); 
            }else{
                if(vistaCliente.jtDireccion.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "El Campo, No Puede Quedar Vacio");
                    vistaCliente.jtDireccion.requestFocus(); 
                }else{
                    obtenerDatosGUI();
                    if(operaciones.guardar(cedula, nombre, telefonos, direccion)){
                    util.informar(this.vistaCliente,"Datos Guardados Correctamente",vistaCliente.getTitle());
                    vistaCliente.jtCedula.setText("");
                    vistaCliente.jtNombres.setText("");
                    vistaCliente.jtTelefonos.setText("");
                    vistaCliente.jtDireccion.setText("");
                    operaciones.llenarTabla(vistaCliente.jtClientes);
                    }else{
                      util.advertir(this.vistaCliente,"No Se Guardaron Los Datos",vistaCliente.getTitle());
                    }
                }
            }
        }
    }
}

public void validarActualizacion(){
    int Ced=vistaCliente.jtCedula.getText().length();
        int nomb=vistaCliente.jtNombres.getText().length();
        int telef=vistaCliente.jtTelefonos.getText().length();
        int direc=vistaCliente.jtDireccion.getText().length();
        System.out.print(Ced+" "+nomb+" "+telef+" "+direc);
        if(Ced==0||nomb==0||telef==0||direc==0){
          JOptionPane.showMessageDialog(null,"Campos Vacios, Por Favor Escoja Un Cliente");
        }else{
          obtenerDatosGUI();
          if(operaciones.actualizar(cedula, nombre, telefonos, direccion)){
            util.informar(this.vistaCliente,"Datos Actualizados Correctamente",vistaCliente.getTitle());
            vistaCliente.jtCedula.setText("");
            vistaCliente.jtNombres.setText("");
            vistaCliente.jtTelefonos.setText("");
            vistaCliente.jtDireccion.setText("");
            operaciones.llenarTabla(vistaCliente.jtClientes);
            vistaCliente.jButtonGuardar.setEnabled(true);
         
        }else{
           util.advertir(this.vistaCliente, "Error.. No Se Actualizaron Los Datos",vistaCliente.getTitle());
        }
       } 
}
}
