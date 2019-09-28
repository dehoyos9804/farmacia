/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Controlador.validarCampo;
import Modelo.OperacionesMedicamentos;
import java.awt.Image;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author Aldair
 */
public class medicamentos extends javax.swing.JFrame {

    public medicamentos() {
        initComponents();
        OperacionesMedicamentos operaciones= new OperacionesMedicamentos();
        this.setLocationRelativeTo(null);
        operaciones.llenarTabla(jTableRegistro);
        jtseguimiento.setEnabled(false);
        jtidmedicamento.setEnabled(false);
        jtseguimiento.setText(null);
        jccodlaboratorio.removeAllItems();
        jdfechavencimiento.setDate(new Date ());
        operaciones.generarId(jtidmedicamento);
        operaciones.obtenerLaboratorio(jccodlaboratorio);
       
        
        //cerrar con la tecla escape
        KeyboardFocusManager kb=KeyboardFocusManager.getCurrentKeyboardFocusManager();
        kb.addKeyEventPostProcessor(new KeyEventPostProcessor(){
            public boolean postProcessKeyEvent(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    dispose();
                    return true;
                }
                return false;
            }
        });
        
        jButtonGuardar.setToolTipText("Guardar Datos");
        jButtonActualizar.setToolTipText("Actualizar Datos");
        jButtonBuscar.setToolTipText("Buscar Datos");
        jButtonEliminar.setToolTipText("Eliminar Datos");
        jButtonAtras.setToolTipText("Salir");
        
    }
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/printlogo.png"));
        return retValue;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jtidmedicamento = new javax.swing.JTextField();
        jtnombre = new javax.swing.JTextField();
        jtprincipioactivo = new javax.swing.JTextField();
        jtgrupofarmacologico = new javax.swing.JTextField();
        jtconcentracion = new javax.swing.JTextField();
        jtcantidadmedicamento = new javax.swing.JTextField();
        jtseguimiento = new javax.swing.JTextField();
        jdfechavencimiento = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jtPrecioUnidad = new javax.swing.JTextField();
        jcformafarmaceutica = new javax.swing.JComboBox<>();
        jccodlaboratorio = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lblImagenMedicamento = new javax.swing.JLabel();
        btnCargarImagenMedicamento = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonGuardar = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonAtras = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRegistro = new javax.swing.JTable();
        jbfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("Id medicamento");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 15, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel3.setText("Principio Activo");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 66, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel4.setText("Grupo Farmacologico");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 89, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel5.setText("Laboratorio");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 122, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setText("Concentración");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 146, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel7.setText("Forma Farmaceutica");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 171, -1, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel8.setText("Fecha Vencimiento");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 202, -1, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel9.setText("cantidad Medicamento");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel10.setText("Precio Compra");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        jtidmedicamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtidmedicamentoKeyTyped(evt);
            }
        });
        jPanel1.add(jtidmedicamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 14, 280, -1));
        jPanel1.add(jtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 39, 280, -1));
        jPanel1.add(jtprincipioactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 64, 280, -1));
        jPanel1.add(jtgrupofarmacologico, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 89, 280, -1));
        jPanel1.add(jtconcentracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 280, -1));

        jtcantidadmedicamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtcantidadmedicamentoKeyTyped(evt);
            }
        });
        jPanel1.add(jtcantidadmedicamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 280, -1));
        jPanel1.add(jtseguimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 280, -1));
        jPanel1.add(jdfechavencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 280, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Precio Unidad");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        jtPrecioUnidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtPrecioUnidadKeyTyped(evt);
            }
        });
        jPanel1.add(jtPrecioUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 280, -1));

        jcformafarmaceutica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*", "TABLETAS", "CÁPSULAS", "TABLETAS RECUBIERTAS", "POLVOS", "GRANULADOS", "COMPRIMIDOS", "SUPOSITORES", "POMADAS", "CREMAS", "JALEAS", "SOLUCIÓN ORAL", "INYECCIONES", "JARABES ", "EMULSIONES", "OVULOS", "TABLETAS EFERVECENTES", "CÁPSULAS LIQUIDAS", "GRAGEAS", "GELES", "EMPLASTOS", "SUSPENSION", "GOTAS OFTALMICAS", "LOCIONES", "ELIXIR", "AEROSOLES", "AMPOLLAS" }));
        jcformafarmaceutica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcformafarmaceuticaActionPerformed(evt);
            }
        });
        jPanel1.add(jcformafarmaceutica, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 280, -1));

        jccodlaboratorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*" }));
        jccodlaboratorio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jccodlaboratorioItemStateChanged(evt);
            }
        });
        jPanel1.add(jccodlaboratorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 280, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel12.setText("seguimiento");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));
        jPanel1.add(txtPrecioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 280, -1));

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lblImagenMedicamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Foto", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        btnCargarImagenMedicamento.setText("Cargar Imagen");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblImagenMedicamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCargarImagenMedicamento, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblImagenMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCargarImagenMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 190, 350));

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 418, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 420, 350));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 640, 370));

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar1.png"))); // NOI18N
        jButtonGuardar.setText("Guardar");

        jButtonActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar1.png"))); // NOI18N
        jButtonActualizar.setText("Actualizar");

        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Buscar1.png"))); // NOI18N
        jButtonBuscar.setText("Buscar");

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jButtonGuardar)
                .addGap(18, 18, 18)
                .addComponent(jButtonActualizar)
                .addGap(18, 18, 18)
                .addComponent(jButtonBuscar)
                .addGap(18, 18, 18)
                .addComponent(jButtonEliminar)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, -1, 370));

        jButtonAtras.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/regresar.png"))); // NOI18N
        jButtonAtras.setText("Atras");
        getContentPane().add(jButtonAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 90, 25));

        jLabel13.setFont(new java.awt.Font("Footlight MT Light", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/medicamen.png"))); // NOI18N
        jLabel13.setText("REGISTRAR MEDICAMENTOS");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 12, -1, 36));

        jTableRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRegistroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableRegistro);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 800, 100));

        jbfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.jpg"))); // NOI18N
        jbfondo.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));
        getContentPane().add(jbfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtidmedicamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtidmedicamentoKeyTyped
       validarCampo.validar(evt);
    }//GEN-LAST:event_jtidmedicamentoKeyTyped

    private void jtcantidadmedicamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtcantidadmedicamentoKeyTyped
       validarCampo.validar(evt);
    }//GEN-LAST:event_jtcantidadmedicamentoKeyTyped

    private void jtPrecioUnidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtPrecioUnidadKeyTyped
        validarCampo.validar(evt);
    }//GEN-LAST:event_jtPrecioUnidadKeyTyped

    private void jccodlaboratorioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jccodlaboratorioItemStateChanged
        
    }//GEN-LAST:event_jccodlaboratorioItemStateChanged

    private void jTableRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRegistroMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableRegistroMouseClicked

    private void jcformafarmaceuticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcformafarmaceuticaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcformafarmaceuticaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(medicamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(medicamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(medicamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(medicamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new medicamentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCargarImagenMedicamento;
    public javax.swing.JButton jButtonActualizar;
    public javax.swing.JButton jButtonAtras;
    public javax.swing.JButton jButtonBuscar;
    public javax.swing.JButton jButtonEliminar;
    public javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTableRegistro;
    public javax.swing.JLabel jbfondo;
    public javax.swing.JComboBox<String> jccodlaboratorio;
    public javax.swing.JComboBox<String> jcformafarmaceutica;
    public com.toedter.calendar.JDateChooser jdfechavencimiento;
    public javax.swing.JTextField jtPrecioUnidad;
    public javax.swing.JTextField jtcantidadmedicamento;
    public javax.swing.JTextField jtconcentracion;
    public javax.swing.JTextField jtgrupofarmacologico;
    public javax.swing.JTextField jtidmedicamento;
    public javax.swing.JTextField jtnombre;
    public javax.swing.JTextField jtprincipioactivo;
    public javax.swing.JTextField jtseguimiento;
    public javax.swing.JLabel lblImagenMedicamento;
    public javax.swing.JTextField txtPrecioCompra;
    // End of variables declaration//GEN-END:variables

}

