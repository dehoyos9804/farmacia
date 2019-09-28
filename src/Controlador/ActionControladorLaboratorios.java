
package Controlador;

import Modelo.OperacionesLaboratorios;
import Modelo.util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.laboratorios;

/**
 *
 * @author Aldair
 */
public class ActionControladorLaboratorios implements ActionListener {
      laboratorios vtLaboratorios=new laboratorios();
      OperacionesLaboratorios operaciones=new OperacionesLaboratorios();
      public int idlaboratorio;
      public String nombrelaboratorio;
      public String direccion;
      public String telefonos;
      public String email;
      
      public ActionControladorLaboratorios(laboratorios vtLaboratorios,OperacionesLaboratorios operaciones){
          this.vtLaboratorios=vtLaboratorios;
          this.operaciones=operaciones;
          
          this.vtLaboratorios.jButtonBuscar.addActionListener(this);
          this.vtLaboratorios.jButtonGuardar.addActionListener(this);
          this.vtLaboratorios.jButtonActualizar.addActionListener(this);
          this.vtLaboratorios.jButtonEliminar.addActionListener(this);
          this.vtLaboratorios.jbatras.addActionListener(this);
      }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vtLaboratorios.jButtonBuscar){
            String idlaborat="";
            int variable=vtLaboratorios.jTableRegistro.getSelectedRow();
           if(variable==-1){
              JOptionPane.showMessageDialog(null, "Por Favor Seleccione Un Laboratorio");
           }else{
                idlaborat=vtLaboratorios.jTableRegistro.getValueAt(variable,0).toString();
               if(operaciones.buscar(idlaborat, vtLaboratorios.jtIdLaboratorio, vtLaboratorios.jtNombreLaboratorio, vtLaboratorios.jtDireccion, vtLaboratorios.jtTelefonos, vtLaboratorios.jtEmail)){
                  vtLaboratorios.jButtonGuardar.setEnabled(false);
               }else{
                 util.informar(this.vtLaboratorios,"Error.. No Se Encontraron Los Datos", vtLaboratorios.getTitle());
               }
             }
        }
        
        if(e.getSource()==vtLaboratorios.jButtonGuardar){
             validarCampos();
        }
        
        if(e.getSource()==vtLaboratorios.jButtonActualizar){
              int idlabora=vtLaboratorios.jtIdLaboratorio.getText().length();
              int nomb=vtLaboratorios.jtNombreLaboratorio.getText().length();
              int direcc=vtLaboratorios.jtDireccion.getText().length();
              int telef=vtLaboratorios.jtTelefonos.getText().length();
              int ema=vtLaboratorios.jtEmail.getText().length();
              if(idlabora==0||nomb==0||direcc==0||telef==0||ema==0){
                  JOptionPane.showMessageDialog(null,"Campos Vacios, Por Favor Escoja Un laboratorio");   
              }else{
                  obtenerDatosGUI();
                  if(operaciones.actualizar(idlaboratorio, nombrelaboratorio, direccion, telefonos, email)){
                      util.informar(this.vtLaboratorios,"Datos Actualizados Correctamente", vtLaboratorios.getTitle());
                      operaciones.llenarTabla(vtLaboratorios.jTableRegistro);
                      vtLaboratorios.jButtonGuardar.setEnabled(true);
                      operaciones.obtenerIdLaboratorio(vtLaboratorios.jtIdLaboratorio);
                      vtLaboratorios.jtNombreLaboratorio.setText("");
                      vtLaboratorios.jtDireccion.setText("");
                      vtLaboratorios.jtTelefonos.setText("");
                      vtLaboratorios.jtEmail.setText("");
                  }else{
                      util.advertir(this.vtLaboratorios, "Error.. No Se Actualizaron Los Datos", vtLaboratorios.getTitle());
                  }
              }
        }
        
        if(e.getSource()==vtLaboratorios.jButtonEliminar){
            validarEliminado();
        }
        
        if(e.getSource()==vtLaboratorios.jbatras){
            vtLaboratorios.dispose();
        }
    }
    

public void obtenerDatosGUI(){
    idlaboratorio=Integer.parseInt(vtLaboratorios.jtIdLaboratorio.getText());
    nombrelaboratorio=vtLaboratorios.jtNombreLaboratorio.getText().toUpperCase();
    direccion=vtLaboratorios.jtDireccion.getText().toUpperCase();
    telefonos=vtLaboratorios.jtTelefonos.getText().toUpperCase();
    email=vtLaboratorios.jtEmail.getText().toUpperCase();
}

public void validarCampos(){
    if(vtLaboratorios.jtNombreLaboratorio.getText().length()==0){
        JOptionPane.showMessageDialog(null,"Campo nombre Laboratorio vacio...");
        vtLaboratorios.jtNombreLaboratorio.requestFocusInWindow();
    }else{
        if(vtLaboratorios.jtDireccion.getText().length()==0){
           JOptionPane.showMessageDialog(null,"Campo dirección vacio...");
           vtLaboratorios.jtDireccion.requestFocusInWindow();
        }else{
            if(vtLaboratorios.jtTelefonos.getText().length()==0){
               JOptionPane.showMessageDialog(null,"Campo telefono vacio...");
               vtLaboratorios.jtTelefonos.requestFocusInWindow();
            }else{
                if(vtLaboratorios.jtEmail.getText().length()==0){
                     JOptionPane.showMessageDialog(null,"Campo jtEmail vacio...");
                     vtLaboratorios.jtEmail.requestFocusInWindow();
                }else{
                    obtenerDatosGUI();
                    if(operaciones.guardar(idlaboratorio, nombrelaboratorio, direccion, telefonos, email)){
                    util.advertir(this.vtLaboratorios,"Datos Guardados Correctamente", vtLaboratorios.getTitle());
                    operaciones.llenarTabla(vtLaboratorios.jTableRegistro);
                    operaciones.obtenerIdLaboratorio(vtLaboratorios.jtIdLaboratorio);
                    vtLaboratorios.jtNombreLaboratorio.setText("");
                    vtLaboratorios.jtDireccion.setText("");
                    vtLaboratorios.jtTelefonos.setText("");
                    vtLaboratorios.jtEmail.setText("");
                    vtLaboratorios.jButtonGuardar.setEnabled(true);
                    }else{
                        util.advertir(this.vtLaboratorios,"No Se Guardaron Los Datos", vtLaboratorios.getTitle());
                    }
                }
            }
        }
    }
}
public void validarEliminado(){
    int max=0;//declaro una variable maximo que va a contener el numero maximo de idmedicamentos
        //tomo el maximo de los archivos, registrados en la bases de datos. 
        for(int i=0;i<vtLaboratorios.jTableRegistro.getRowCount();i++){
            max=(int)vtLaboratorios.jTableRegistro.getValueAt(i, 0);
        }
        int cod=Integer.parseInt(vtLaboratorios.jtIdLaboratorio.getText());//tomo el valor del campo de texto
        if(cod>max){//comparo si el codigo con el valor del campo de texto
            JOptionPane.showMessageDialog(null,"Campos Vacios, Por Favor Escoja Un laboratorio");
        }else{
            obtenerDatosGUI();
            if(operaciones.eliminar(vtLaboratorios.jtIdLaboratorio, idlaboratorio)){
              util.informar(this.vtLaboratorios,"Datos Eliminados", vtLaboratorios.getTitle());
              operaciones.llenarTabla(vtLaboratorios.jTableRegistro);
              vtLaboratorios.jButtonGuardar.setEnabled(true);
              operaciones.obtenerIdLaboratorio(vtLaboratorios.jtIdLaboratorio);
              vtLaboratorios.jtNombreLaboratorio.setText("");
              vtLaboratorios.jtDireccion.setText("");
              vtLaboratorios.jtTelefonos.setText("");
              vtLaboratorios.jtEmail.setText("");
            }else{
              util.advertir(this.vtLaboratorios,"Error..Datos No Eliminados", vtLaboratorios.getTitle());
            }
        }
}

}
