
package Controlador;

import Modelo.OperacionesVendedores;
import Modelo.util;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import vista.vendedores;

/**
 *
 * @author Aldair
 */
public class ActionControladorVendedores implements ActionListener{
    vendedores vtVendedores = new vendedores();
    OperacionesVendedores operaciones = new OperacionesVendedores();
    private int cc;
    String nombres;
    String apellidos;
    String cargo;
    String telefonos;
    String fechanacimiento;
    String direccion;
    String correoelectronico;
    String usuario;
    String contraseña;
    String tipousuario;
    
    File ruta_absoluta;
    String tipoimagen;
    
    String auxtipo = "jpg";
    
    String ruta = "";
    
    String url = "";
    String destino = "";

    public ActionControladorVendedores(vendedores vtVendedores, OperacionesVendedores operaciones) {
        this.vtVendedores = vtVendedores;
        this.operaciones = operaciones;
        
        ruta_absoluta = new File("");
        
        this.vtVendedores.jButtonEliminar.setEnabled(false);
        this.vtVendedores.jButtonGuardar.addActionListener(this);
        this.vtVendedores.jButtonActualizar.addActionListener(this);
        this.vtVendedores.jButtonBuscar.addActionListener(this);
        /*this.vtVendedores.jButtonEliminar.addActionListener(this);*/
        this.vtVendedores.jbatras.addActionListener(this);
        this.vtVendedores.btnBuscarImagen.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vtVendedores.jButtonGuardar) {
            validarCampos();
        }

        if (e.getSource() == vtVendedores.btnBuscarImagen) {
            
            JFileChooser fileChooser = new JFileChooser();//creo un objeto de tipo JFileChooser
            //Hago un filtro para buscar archivos de tipo JPG, PNG & GIF
            FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
            fileChooser.setFileFilter(fil);//agrego el filtro a mi ventana de dialogo JFileChooSer
            fileChooser.setDialogTitle("Abrir Imagenes");//coloco un titulo
            int s = fileChooser.showOpenDialog(vtVendedores);//Abro mi ventana de dialogo
            
            switch(s){
                case JFileChooser.APPROVE_OPTION://si digito abrir el archivo
                    File file = fileChooser.getSelectedFile();//obtengo una variable de tipo File
                    
                    auxtipo = fileChooser.getSelectedFile().getName();//extraigo el nombre del archivo
                    ruta = String.valueOf(file);//convierto a string mi variable file y lo agrago a ruta
                    
                    ImageIcon img = new ImageIcon(ruta);//convierto mi ruta a imagen
                    Image icon = img.getImage();//extraigo la imagen
                    Image newimg = icon.getScaledInstance(120, 105, Image.SCALE_DEFAULT);//agrego las dimensiones de la imagen
                    ImageIcon newicon = new ImageIcon(newimg);//convierto mi imagen en icono
                    vtVendedores.Foto.setIcon(newicon);//visualizo mi imagen en un JLabel
                    break;
            }
            
        }
        if (e.getSource() == vtVendedores.jButtonActualizar) {
            validarActualizacion();
        }

        if (e.getSource() == vtVendedores.jButtonBuscar) {
            String cec = "";
            int variable = vtVendedores.jTableRegistro.getSelectedRow();
            if (variable == -1) {
                JOptionPane.showMessageDialog(null, "Por Favor Seleccione Un Vendedor");
            } else {
                cec = vtVendedores.jTableRegistro.getValueAt(variable, 0).toString();
                if (operaciones.buscar(cec, vtVendedores.jtCc, vtVendedores.jtNombres, vtVendedores.jtApellidos, vtVendedores.jtCargo, vtVendedores.jtTelefonos, vtVendedores.jdFechaNacimiento, vtVendedores.jtDireccion, vtVendedores.jtCorreoElectronico, vtVendedores.Foto, vtVendedores.comboTipoUsuario, vtVendedores.txtUsuario, vtVendedores.txtContraseña, vtVendedores.txtCofirmarContraseña, ruta_absoluta.getAbsolutePath())) {
                    vtVendedores.btnBuscarImagen.setEnabled(false);
                    vtVendedores.comboTipoUsuario.setEnabled(false);
                    vtVendedores.txtUsuario.setEnabled(false);
                    vtVendedores.txtContraseña.setEnabled(false);
                    vtVendedores.txtCofirmarContraseña.setEnabled(false);
                    vtVendedores.jButtonGuardar.setEnabled(false);
                } else {
                    util.informar(this.vtVendedores, "Error.. No Se Encontraron Los Datos", vtVendedores.getTitle());
                }
            }
        }

        /*if(e.getSource()==vtVendedores.jButtonEliminar){
            if(vtVendedores.jtCc.getText().length()==0){
                JOptionPane.showMessageDialog(null,"Favor Escojer Un Cliente"); 
            }else{
                obtenerDatosGUI();
                if(operaciones.eliminar(cc)){
                    util.informar(this.vtVendedores, "Datos Eliminados Correctamente", vtVendedores.getTitle());
                    vtVendedores.jButtonGuardar.setEnabled(true);
                    operaciones.llenarTabla(vtVendedores.jTableRegistro);
                    vtVendedores.jtCc.setText("");
                    vtVendedores.jtNombres.setText("");
                    vtVendedores.jtApellidos.setText("");
                    vtVendedores.jtCargo.setText("");
                    vtVendedores.jtTelefonos.setText("");
                    vtVendedores.jdFechaNacimiento.setDate(new Date());
                    vtVendedores.jtDireccion.setText("");
                    vtVendedores.jtCorreoElectronico.setText("");
                    vtVendedores.jButtonGuardar.setEnabled(true);
                }else{
                    util.advertir(this.vtVendedores, "Error.. Los Datos No Fueron Eliminados", vtVendedores.getTitle());
                }
            }
        }*/
        if (e.getSource() == vtVendedores.jbatras) {
            vtVendedores.dispose();
        }
    }

    public void obtenerDatosGUI() {
        cc = Integer.parseInt(vtVendedores.jtCc.getText().trim().toUpperCase());
        nombres = vtVendedores.jtNombres.getText().trim().toUpperCase();
        apellidos = vtVendedores.jtApellidos.getText().trim().toUpperCase();
        cargo = vtVendedores.jtCargo.getText().trim().toUpperCase();
        telefonos = vtVendedores.jtTelefonos.getText().trim().toUpperCase();
        fechanacimiento = util.aFechaMYSQL(vtVendedores.jdFechaNacimiento.getDate());
        direccion = vtVendedores.jtDireccion.getText().trim().toUpperCase();
        correoelectronico = vtVendedores.jtCorreoElectronico.getText().trim().toUpperCase();
        usuario = vtVendedores.txtUsuario.getText();
        contraseña = vtVendedores.txtContraseña.getText();
        tipousuario = vtVendedores.comboTipoUsuario.getSelectedItem().toString();
    }

    public void validarCampos() {
        String contra = vtVendedores.txtContraseña.getText();
        String confircontra = vtVendedores.txtCofirmarContraseña.getText();
        if (vtVendedores.jtCc.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Campo Cedula De Ciudadania Vacio...");
            vtVendedores.jtCc.requestFocusInWindow();
        } else {
            if (vtVendedores.jtNombres.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Nombres  Vacios...");
                vtVendedores.jtNombres.requestFocusInWindow();
            } else {
                if (vtVendedores.jtApellidos.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Apellidos Vacios...");
                    vtVendedores.jtApellidos.requestFocusInWindow();
                } else {
                    if (vtVendedores.jtCargo.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Campo Cargo Vacio...");
                        vtVendedores.jtCargo.requestFocusInWindow();
                    } else {
                        if (vtVendedores.jtTelefonos.getText().length() == 0) {
                            JOptionPane.showMessageDialog(null, "Campo Telefonos Vacio...");
                            vtVendedores.jtTelefonos.requestFocusInWindow();
                        } else {
                            if (vtVendedores.jdFechaNacimiento.getDate() == null) {
                                JOptionPane.showMessageDialog(null, "Campo Fecha Nacimiento Vacio...");
                                vtVendedores.jdFechaNacimiento.requestFocusInWindow();
                            } else {
                                if (vtVendedores.jtDireccion.getText().length() == 0) {
                                    JOptionPane.showMessageDialog(null, "Campo Direccion Vacio...");
                                    vtVendedores.jtDireccion.requestFocusInWindow();
                                } else {
                                    if (vtVendedores.jtCorreoElectronico.getText().length() == 0) {
                                        JOptionPane.showMessageDialog(null, "Campo Correo Electronico Vacio...");
                                        vtVendedores.jtCorreoElectronico.requestFocusInWindow();
                                    } else {
                                        if (vtVendedores.comboTipoUsuario.getSelectedItem() == "*") {
                                            JOptionPane.showMessageDialog(null, "Escoja El Tipo De Usuario");
                                            vtVendedores.comboTipoUsuario.requestFocus();
                                        } else {
                                            if (vtVendedores.txtUsuario.getText().length() == 0) {
                                                JOptionPane.showMessageDialog(null, "Usuario No Creado");
                                                vtVendedores.requestFocus();
                                            } else {
                                                if (vtVendedores.txtContraseña.getText().length() == 0) {
                                                    JOptionPane.showMessageDialog(null, "Digite Una Contraseña");
                                                    vtVendedores.txtContraseña.requestFocus();
                                                } else {
                                                    if (contra == null ? confircontra != null : !contra.equals(confircontra)) {
                                                        JOptionPane.showMessageDialog(null, "La Contraseña no coiciden");
                                                        vtVendedores.txtCofirmarContraseña.requestFocus();
                                                    } else {
                                                        obtenerDatosGUI();
                                                        if (operaciones.guardar(cc, nombres, apellidos, cargo, telefonos, fechanacimiento, direccion, correoelectronico)) {

                                                            if(operaciones.guardarusuarios(cc, usuario, contraseña, tipousuario)){

                                                                try {
                                                                    String name = auxtipo.substring(0, auxtipo.indexOf("."));//obtengo un cadena que va desde el inicio, hasta que encuentre un .
                                                                    tipoimagen = auxtipo.substring(auxtipo.indexOf(".") + 1, auxtipo.length());
                                                                    
                                                                    url = "\\recursos\\vendedores\\" + cc + name + "." + tipoimagen;
                                                                    destino = ruta_absoluta.getAbsolutePath() + url;
                                                                    
                                                                    System.out.println("ruta" + ruta);
                                                                    System.out.println("DESTINO ==>" + destino);

                                                                    Path origin_path = Paths.get(ruta);
                                                                    Path destino_path = Paths.get(destino);
                                                                    //copiamos el archivo
                                                                    Files.copy(origin_path, destino_path);

                                                                } catch (IOException e) {
                                                                    url = "\\recursos\\vendedores\\defecto.jpg";
                                                                    tipoimagen = "jpg";
                                                                    System.err.println("Error al guardar la imagen" + e.getMessage());
                                                                }
                                                                
                                                                CorregirRuta r = new CorregirRuta(url, "\\", "\\\\");
                                                                url = r.obtenerRutaCorregidaWindows();
                                                                
                                                                if(operaciones.guardarImagen(url, tipoimagen, cc)){
                                                                    
                                                                    operaciones.llenarTabla(vtVendedores.jTableRegistro);
                                                                    vtVendedores.jtCc.setText("");
                                                                    vtVendedores.jtNombres.setText("");
                                                                    vtVendedores.jtApellidos.setText("");
                                                                    vtVendedores.jtCargo.setText("");
                                                                    vtVendedores.jtTelefonos.setText("");
                                                                    vtVendedores.jdFechaNacimiento.setDate(new Date());
                                                                    vtVendedores.jtDireccion.setText("");
                                                                    vtVendedores.jtCorreoElectronico.setText("");

                                                                    //imagen por defecto
                                                                    ImageIcon defec = new ImageIcon(ruta_absoluta.getAbsolutePath() + "\\recursos\\vendedores\\defecto.jpg");
                                                                    ImageIcon icono = new ImageIcon(defec.getImage().getScaledInstance(120, 105, Image.SCALE_DEFAULT));
                                                                    vtVendedores.Foto.setIcon(icono);
                                                                    
                                                                    vtVendedores.txtUsuario.setText("");
                                                                    vtVendedores.comboTipoUsuario.setSelectedIndex(0);
                                                                    vtVendedores.txtContraseña.setText("");
                                                                    vtVendedores.txtCofirmarContraseña.setText("");

                                                                    util.advertir(this.vtVendedores, "Datos Guardados Correctamente", vtVendedores.getTitle());
                                                                }
                                                            }
                                                        } else {
                                                            util.advertir(this.vtVendedores, "No Se Guardaron Los Datos", vtVendedores.getTitle());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void validarActualizacion() {
        int cod = vtVendedores.jtCc.getText().length();
        int nombr = vtVendedores.jtNombres.getText().length();
        int apelli = vtVendedores.jtApellidos.getText().length();
        int carg = vtVendedores.jtCargo.getText().length();
        int telefo = vtVendedores.jtTelefonos.getText().length();
        int direc = vtVendedores.jtDireccion.getText().length();
        int email = vtVendedores.jtCorreoElectronico.getText().length();
        if (cod == 0 || nombr == 0 || apelli == 0 || carg == 0 || telefo == 0 || direc == 0 || email == 0) {
            JOptionPane.showMessageDialog(null, "Campos Vacios, Por Favor Escoja Un Vendedor");
        } else {
            obtenerDatosGUI();
            if (operaciones.actualizar(cc, nombres, apellidos, cargo, telefonos, fechanacimiento, direccion, correoelectronico)) {
                util.informar(this.vtVendedores, "Datos Actualizados Correctamente", vtVendedores.getTitle());
                vtVendedores.btnBuscarImagen.setEnabled(true);
                vtVendedores.comboTipoUsuario.setEnabled(true);
                vtVendedores.txtCofirmarContraseña.setEnabled(true);
                vtVendedores.txtContraseña.setEnabled(true);
                vtVendedores.txtUsuario.setEnabled(true);
                vtVendedores.comboTipoUsuario.setSelectedIndex(0);
                vtVendedores.txtUsuario.setText("");
                vtVendedores.txtContraseña.setText("");
                vtVendedores.txtCofirmarContraseña.setText("");

                vtVendedores.jButtonGuardar.setEnabled(true);
                operaciones.llenarTabla(vtVendedores.jTableRegistro);;
                vtVendedores.jtCc.setText("");
                vtVendedores.jtNombres.setText("");
                vtVendedores.jtApellidos.setText("");
                vtVendedores.jtCargo.setText("");
                vtVendedores.jtTelefonos.setText("");
                vtVendedores.jdFechaNacimiento.setDate(new Date());
                vtVendedores.jtDireccion.setText("");
                vtVendedores.jtCorreoElectronico.setText("");
                ImageIcon defec = new ImageIcon(ruta_absoluta.getAbsolutePath() + "\\recursos\\vendedores\\defecto.jpg");
                ImageIcon icono = new ImageIcon(defec.getImage().getScaledInstance(120, 105, Image.SCALE_DEFAULT));
                vtVendedores.Foto.setIcon(icono);
            } else {
                util.advertir(this.vtVendedores, "Error.. No Se Actualizaron Los Datos", vtVendedores.getTitle());
            }
        }
    }
    
}
