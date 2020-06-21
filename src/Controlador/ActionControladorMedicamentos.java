
package Controlador;

import Modelo.OperacionesMedicamentos;
import Modelo.util;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import vista.laboratorios;
import vista.medicamentos;

/**
 *
 * @author Aldair
 */
public class ActionControladorMedicamentos implements ActionListener{
    medicamentos vtMedicamento = new medicamentos();
    OperacionesMedicamentos operaciones = new OperacionesMedicamentos();
    public String idmedicamento;
    public String nombre;
    public String principioactivo;
    public String grupofarmacologico;
    public String codlaboratorio;
    public String concentracion;
    public String formafarmaceutica;
    public String fechavencimiento;
    public String cantidadmedicamento;
    public String preciounidad;
    public String preciocompra;
    public String seguimiento;

    File ruta_absoluta;
    String url = "";
    String destino = "";
    String tipoimagen;
    String auxtipo = "png";
    public String ruta = "";

    public ActionControladorMedicamentos() {

    }

    //constructor
    public ActionControladorMedicamentos(medicamentos vtMedicamento, OperacionesMedicamentos operaciones) {
        this.vtMedicamento = vtMedicamento;
        this.vtMedicamento = vtMedicamento;

        ruta_absoluta = new File("");

        cargarImagenDefecto();

        //agrego botones a la action
        this.vtMedicamento.jButtonGuardar.addActionListener(this);
        this.vtMedicamento.jButtonBuscar.addActionListener(this);
        this.vtMedicamento.jButtonActualizar.addActionListener(this);
        this.vtMedicamento.jButtonAtras.addActionListener(this);
        this.vtMedicamento.jButtonEliminar.addActionListener(this);
        this.vtMedicamento.btnCargarImagenMedicamento.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vtMedicamento.jButtonAtras) {
            vtMedicamento.dispose();
        }
        if (e.getSource() == vtMedicamento.jButtonGuardar) {
            validarCampos();
        }
        if (e.getSource() == vtMedicamento.jButtonBuscar) {
            String idmedic = "";
            int variable = vtMedicamento.jTableRegistro.getSelectedRow();
            if (variable == -1) {
                JOptionPane.showMessageDialog(null, "Por Favor Seleccione Un Medicamento");
            } else {
                idmedic = vtMedicamento.jTableRegistro.getValueAt(variable, 0).toString();
                if (operaciones.buscarMedicamento(idmedic, vtMedicamento.jtidmedicamento, vtMedicamento.jtnombre, vtMedicamento.jtprincipioactivo, vtMedicamento.jtgrupofarmacologico, vtMedicamento.jccodlaboratorio, vtMedicamento.jtconcentracion, vtMedicamento.jcformafarmaceutica, vtMedicamento.jdfechavencimiento, vtMedicamento.jtcantidadmedicamento, vtMedicamento.jtPrecioUnidad, vtMedicamento.txtPrecioCompra, vtMedicamento.jtseguimiento)) {
                    operaciones.buscarImagenMedicamento(vtMedicamento.lblImagenMedicamento, idmedic);
                    vtMedicamento.jButtonGuardar.setEnabled(false);
                } else {
                    util.informar(this.vtMedicamento, "Error.. No Se Encontraron Los Datos", vtMedicamento.getTitle());
                }
            }
        }

        if (e.getSource() == vtMedicamento.jButtonActualizar) {
            validarActualizacion();
        }
        if (e.getSource() == vtMedicamento.jButtonEliminar) {
            validarEliminado();
        }

        if (e.getSource() == vtMedicamento.btnCargarImagenMedicamento) {
            buscarImagenPc();
        }
    }

    //metodos para la vista medicamentos
    public void obtenerDatosGUI() {
        idmedicamento = vtMedicamento.jtidmedicamento.getText().trim();
        nombre = vtMedicamento.jtnombre.getText().trim().toUpperCase();
        principioactivo = vtMedicamento.jtprincipioactivo.getText().trim().toUpperCase();
        grupofarmacologico = vtMedicamento.jtgrupofarmacologico.getText().trim().toUpperCase();
        codlaboratorio = vtMedicamento.jccodlaboratorio.getSelectedItem().toString().trim();
        concentracion = vtMedicamento.jtconcentracion.getText().trim().toUpperCase();
        formafarmaceutica = vtMedicamento.jcformafarmaceutica.getSelectedItem().toString().trim();
        fechavencimiento = util.aFechaMYSQL(vtMedicamento.jdfechavencimiento.getDate());
        cantidadmedicamento = vtMedicamento.jtcantidadmedicamento.getText().trim();
        preciounidad = vtMedicamento.jtPrecioUnidad.getText().trim();
        preciocompra = vtMedicamento.txtPrecioCompra.getText().trim();
        seguimiento = vtMedicamento.jtseguimiento.getText().trim().toUpperCase();
    }

    public void validarCampos() {
        if (vtMedicamento.jtidmedicamento.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Campo Vacio");
            operaciones.generarId(vtMedicamento.jtidmedicamento);
        } else {
            if (vtMedicamento.jtnombre.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Campo Vacio");
                vtMedicamento.jtnombre.requestFocus();
            } else {
                if (vtMedicamento.jtprincipioactivo.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Campo Vacio");
                    vtMedicamento.jtprincipioactivo.requestFocus();
                } else {
                    if (vtMedicamento.jtgrupofarmacologico.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Campo Vacio");
                        vtMedicamento.jtgrupofarmacologico.requestFocus();
                    } else {
                        if (vtMedicamento.jccodlaboratorio.getSelectedItem().toString().length() == 0) {
                            JOptionPane.showMessageDialog(null, "No Existe Ningun Laboratorio, Ingrese Uno");
                            laboratorios l = new laboratorios();
                            l.setVisible(true);
                            operaciones.obtenerLaboratorio(vtMedicamento.jccodlaboratorio);
                        } else {
                            if (vtMedicamento.jtconcentracion.getText().length() == 0) {
                                JOptionPane.showMessageDialog(null, "Campo Vacio");
                                vtMedicamento.jtconcentracion.requestFocus();
                            } else {
                                if (vtMedicamento.jcformafarmaceutica.getSelectedIndex() == 0) {
                                    JOptionPane.showMessageDialog(null, "Escoja Una Forma Farmacéutica");
                                    vtMedicamento.jcformafarmaceutica.setPopupVisible(true);
                                } else {
                                    if (vtMedicamento.jdfechavencimiento.getDate() == null) {
                                        JOptionPane.showMessageDialog(null, "Campo Vacio");
                                        vtMedicamento.jdfechavencimiento.requestFocus();
                                    } else {
                                        if (vtMedicamento.jtcantidadmedicamento.getText().length() == 0) {
                                            JOptionPane.showMessageDialog(null, "Campo Vacio");
                                            vtMedicamento.jtcantidadmedicamento.requestFocus();
                                        } else {
                                            if (vtMedicamento.jtPrecioUnidad.getText().length() == 0) {
                                                JOptionPane.showMessageDialog(null, "Campo Vacio");
                                                vtMedicamento.jtPrecioUnidad.requestFocus();
                                            } else {
                                                obtenerDatosGUI();
                                                if (operaciones.guardar(idmedicamento, nombre, principioactivo, grupofarmacologico, codlaboratorio, concentracion, formafarmaceutica, fechavencimiento, cantidadmedicamento, preciounidad, preciocompra, seguimiento)) {
                                                    
                                                    try {
                                                        String name = auxtipo.substring(0, auxtipo.indexOf("."));//obtengo una cadena que va desde el inicio, hasta que encuentre un .
                                                        tipoimagen = auxtipo.substring(auxtipo.indexOf(".") + 1, auxtipo.length());
                                                        
                                                        url = "\\recursos\\medicamentos\\" + System.currentTimeMillis() + name + "." + tipoimagen;
                                                        destino = ruta_absoluta.getAbsolutePath() + url;
                                                        
                                                        Path origin_path = Paths.get(ruta);
                                                        Path destino_path = Paths.get(destino);
                                                        //copiamos el archivo
                                                        Files.copy(origin_path, destino_path);
                                                        
                                                    } catch (IOException e) {
                                                        url = "\\recursos\\medicamentos\\default.jpg";
                                                        tipoimagen = "jpg";
                                                    }
                                                    
                                                    CorregirRuta r = new CorregirRuta(url, "\\", "\\\\");
                                                    url = r.obtenerRutaCorregidaWindows();
                                                                
                                                    if(operaciones.guardarImagenMedicamento(url, tipoimagen, idmedicamento)){
                                                        operaciones.generarId(vtMedicamento.jtidmedicamento);
                                                        operaciones.llenarTabla(vtMedicamento.jTableRegistro);
                                                        vtMedicamento.jtnombre.setText("");
                                                        vtMedicamento.jtprincipioactivo.setText("");
                                                        vtMedicamento.jtgrupofarmacologico.setText("");
                                                        vtMedicamento.jccodlaboratorio.setSelectedItem("*");
                                                        vtMedicamento.jtconcentracion.setText("");
                                                        vtMedicamento.jcformafarmaceutica.setSelectedItem("*");
                                                        vtMedicamento.jdfechavencimiento.setDate(new Date());
                                                        vtMedicamento.jtPrecioUnidad.setText("");
                                                        vtMedicamento.txtPrecioCompra.setText("");
                                                        vtMedicamento.jtcantidadmedicamento.setText("");
                                                        util.informar(this.vtMedicamento, "Datos Guardados Correctamento", vtMedicamento.getTitle());
                                                    }

                                                    cargarImagenDefecto();
                                                } else {
                                                    util.advertir(this.vtMedicamento, "Error!... No Se Guardaron Los Datos", vtMedicamento.getTitle());
                                                }
                                                if (operaciones.ejecutarSentencia()) {

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

    public void validarActualizacion() {
        int idmedic = vtMedicamento.jtidmedicamento.getText().length();
        int nomb = vtMedicamento.jtnombre.getText().length();
        int principi = vtMedicamento.jtprincipioactivo.getText().length();
        int grupofarmaco = vtMedicamento.jtgrupofarmacologico.getText().length();
        int codlabor = vtMedicamento.jccodlaboratorio.getSelectedItem().toString().length();
        int conce = vtMedicamento.jtconcentracion.getText().length();
        int formafarmac = vtMedicamento.jcformafarmaceutica.getSelectedItem().toString().length();
        int fechavenc = vtMedicamento.jdfechavencimiento.getDate().toString().length();
        int cantidadmedic = vtMedicamento.jtcantidadmedicamento.getText().length();
        int preciouni = vtMedicamento.jtPrecioUnidad.getText().length();
        int seguimo = vtMedicamento.jtseguimiento.getText().length();
        if (idmedic == 0 || nomb == 0 || principi == 0 || grupofarmaco == 0 || codlabor == 0 || conce == 0 || formafarmac == 0 || fechavenc == 0 || cantidadmedic == 0 || preciouni == 0 || seguimo == 0) {
            JOptionPane.showMessageDialog(null, "Campos Vacios, Por Favor Escoja Un Cliente");
        } else {
            obtenerDatosGUI();
            System.out.println(preciocompra);
            if (operaciones.actualizar(idmedicamento, nombre, principioactivo, grupofarmacologico, codlaboratorio, concentracion, formafarmaceutica, fechavencimiento, cantidadmedicamento, preciounidad, preciocompra, seguimiento)) {
                
                try {
                    String name = auxtipo.substring(0, auxtipo.indexOf("."));//obtengo una cadena que va desde el inicio, hasta que encuentre un .
                    tipoimagen = auxtipo.substring(auxtipo.indexOf(".") + 1, auxtipo.length());

                    url = "\\recursos\\medicamentos\\" + System.currentTimeMillis() + name + "." + tipoimagen;
                    destino = ruta_absoluta.getAbsolutePath() + url;

                    Path origin_path = Paths.get(ruta);
                    Path destino_path = Paths.get(destino);
                    //copiamos el archivo
                    Files.copy(origin_path, destino_path);

                } catch (IOException e) {
                    url = "\\recursos\\medicamentos\\default.jpg";
                    tipoimagen = "jpg";
                    System.err.println("Error al guardar la imagen" + e.getMessage());
                }

                CorregirRuta r = new CorregirRuta(url, "\\", "\\\\");
                url = r.obtenerRutaCorregidaWindows();
                
                if(operaciones.actualizarImagenMedicamento(url, tipoimagen, idmedicamento)){
                    operaciones.llenarTabla(vtMedicamento.jTableRegistro);
                    vtMedicamento.jButtonGuardar.setEnabled(true);
                    operaciones.generarId(vtMedicamento.jtidmedicamento);
                    vtMedicamento.jtnombre.setText("");
                    vtMedicamento.jtprincipioactivo.setText("");
                    vtMedicamento.jtgrupofarmacologico.setText("");
                    vtMedicamento.jccodlaboratorio.setSelectedIndex(0);
                    vtMedicamento.jtconcentracion.setText("");
                    vtMedicamento.jcformafarmaceutica.setSelectedItem("*");
                    vtMedicamento.jdfechavencimiento.setDate(new Date());
                    vtMedicamento.jtPrecioUnidad.setText("");
                    vtMedicamento.txtPrecioCompra.setText("");
                    vtMedicamento.jtcantidadmedicamento.setText("");
                    util.informar(this.vtMedicamento, "Datos Actualizados correctamente.", vtMedicamento.getTitle());
                }
                
                cargarImagenDefecto();
            } else {
                util.advertir(this.vtMedicamento, " ERROR: no se actualizaron los datos.", vtMedicamento.getTitle());
            }
            if (operaciones.ejecutarSentencia()) {

            }
        }
    }

    public void validarEliminado() {
        int max = 0;//declaro una variable maximo que va a contener el numero maximo de idmedicamentos
        //tomo el maximo de los archivos, registrados en la bases de datos. 
        for (int i = 0; i < vtMedicamento.jTableRegistro.getRowCount(); i++) {
            max = (int) vtMedicamento.jTableRegistro.getValueAt(i, 0);
        }
        int cod = Integer.parseInt(vtMedicamento.jtidmedicamento.getText());//tomo el valor del campo de texto
        if (cod > max) {//comparo si el codigo con el valor del campo de texto
            JOptionPane.showMessageDialog(null, "Campos Vacios, Por Favor Escoja Un Medicamento");
        } else {
            obtenerDatosGUI();
            if (operaciones.eliminar(idmedicamento)) {
                util.informar(this.vtMedicamento, "Datos Eliminados Correctamente", vtMedicamento.getTitle());
                operaciones.llenarTabla(vtMedicamento.jTableRegistro);
                operaciones.generarId(vtMedicamento.jtidmedicamento);
                vtMedicamento.jtnombre.setText("");
                vtMedicamento.jtprincipioactivo.setText("");
                vtMedicamento.jtgrupofarmacologico.setText("");
                vtMedicamento.jccodlaboratorio.setSelectedIndex(0);
                vtMedicamento.jtconcentracion.setText("");
                vtMedicamento.jcformafarmaceutica.setSelectedItem("*");
                vtMedicamento.jdfechavencimiento.setDate(new Date());
                vtMedicamento.jtPrecioUnidad.setText("");
                vtMedicamento.jtcantidadmedicamento.setText("");
                vtMedicamento.jtseguimiento.setText("");
                vtMedicamento.jButtonGuardar.setEnabled(true);
            } else {
                util.advertir(this.vtMedicamento, "Error.. Los Datos No Fueron Eliminados", vtMedicamento.getTitle());
            }
        }
    }

    public void buscarImagenPc() {
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        jfc.setFileFilter(filtro);
        jfc.setDialogTitle("buscar Imagen Medicamento");
        int abrir = jfc.showOpenDialog(vtMedicamento);

        if (abrir == JFileChooser.APPROVE_OPTION) {
            File variableFile = jfc.getSelectedFile();
            auxtipo = jfc.getSelectedFile().getName();
            ruta = String.valueOf(variableFile);
            ImageIcon img = new ImageIcon(ruta);
            Image icon = img.getImage();
            Image newImg = icon.getScaledInstance(160, 160, Image.SCALE_DEFAULT);
            ImageIcon newIcon = new ImageIcon(newImg);
            vtMedicamento.lblImagenMedicamento.setIcon(newIcon);
        }
    }

    private void cargarImagenDefecto() {
        //cargar imagen por defecto
        ImageIcon defecto = new ImageIcon(ruta_absoluta.getAbsolutePath() + "\\recursos\\medicamentos\\default.jpg");
        ImageIcon icono = new ImageIcon(defecto.getImage().getScaledInstance(150, 130, Image.SCALE_DEFAULT));
        vtMedicamento.lblImagenMedicamento.setIcon(icono);
    }
}
