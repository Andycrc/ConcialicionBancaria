/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;
import Conexion.conector;
import java.awt.Color;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Andy
 */
public class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form FrmMain
     */
    int xMouse, yMouse;
    public static String usuario, empresa,banco;
    public FrmMain() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        usuario_label2 = new javax.swing.JLabel();
        jTextField2_usuario2 = new javax.swing.JTextField();
        jTextField2_empresa = new javax.swing.JTextField();
        contra_label1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        contra_label2 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jTextField2_banco = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Rectangle.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, -1, 490));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Avatar_Container.png"))); // NOI18N
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 80, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Card_Header.png"))); // NOI18N
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, -1));

        usuario_label2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        usuario_label2.setText("Usuario:");
        jPanel4.add(usuario_label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));

        jTextField2_usuario2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextField2_usuario2.setForeground(new java.awt.Color(51, 51, 51));
        jTextField2_usuario2.setText("User");
        jTextField2_usuario2.setBorder(null);
        jTextField2_usuario2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField2_usuario2_usuarioMousePressed(evt);
            }
        });
        jPanel4.add(jTextField2_usuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 280, 40));

        jTextField2_empresa.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextField2_empresa.setForeground(new java.awt.Color(51, 51, 51));
        jTextField2_empresa.setText("Uniaces");
        jTextField2_empresa.setBorder(null);
        jTextField2_empresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField2_empresa_usuarioMousePressed(evt);
            }
        });
        jPanel4.add(jTextField2_empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 280, 40));

        contra_label1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        contra_label1.setText("Empresa:");
        jPanel4.add(contra_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, -1));
        jPanel4.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 280, 10));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 280, 10));

        jPanel3.setBackground(new java.awt.Color(0, 139, 190));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel8.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("ACCEDER");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 130, 40));

        jPanel6.setBackground(new java.awt.Color(0, 139, 190));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel9.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("SALIR");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9jLabel2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 130, -1));

        contra_label2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        contra_label2.setText("Banco:");
        jPanel4.add(contra_label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));
        jPanel4.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 280, 10));

        jTextField2_banco.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextField2_banco.setForeground(new java.awt.Color(51, 51, 51));
        jTextField2_banco.setText("Davivienda");
        jTextField2_banco.setBorder(null);
        jTextField2_banco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField2_banco_usuarioMousePressed(evt);
            }
        });
        jPanel4.add(jTextField2_banco, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 280, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Card_Content.png"))); // NOI18N
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 450, 380));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // TODO add your handling code here:

        this.jPanel3.setBackground(new Color(0,139,190));
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
        this.jPanel3.setBackground(new Color(0, 156, 223));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        
        usuario = this.jTextField2_usuario2.getText();
        empresa = this.jTextField2_empresa.getText();
        banco = this.jTextField2_banco.getText();
        
        Dashboard menu = new Dashboard();
        menu.setVisible(true);
        this.setVisible(false);
        
//        conector cc = new conector();
//        Connection cn = cc.conectar();
//       try{
//
//           String qry1 = "UPDATE test set nombre = 'rivea' where id_jola = 1 ";
//           Statement st = cn.createStatement();
//           st.addBatch(qry1);
//           st.executeBatch();
//           JOptionPane.showMessageDialog(this, "Libro nuevo iniciado"); 
//           
//       }catch(Exception e){
//           JOptionPane.showMessageDialog(null, "error");
//       }
//       cc.close();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jTextField2_empresa_usuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2_empresa_usuarioMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField2_empresa_usuarioMousePressed

    private void jTextField2_usuario2_usuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2_usuario2_usuarioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2_usuario2_usuarioMousePressed

    private void jLabel9jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9jLabel2MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel9jLabel2MouseClicked

    private void jLabel9jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9jLabel2MouseEntered
        // TODO add your handling code here:
        this.jPanel6.setBackground(new Color(0, 156, 223));
    }//GEN-LAST:event_jLabel9jLabel2MouseEntered

    private void jLabel9jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9jLabel2MouseExited
        // TODO add your handling code here:
        this.jPanel6.setBackground(new Color(0,139,190));
    }//GEN-LAST:event_jLabel9jLabel2MouseExited

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseEntered

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        this.xMouse = evt.getX();
        this.yMouse = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_formMouseDragged

    private void jTextField2_banco_usuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2_banco_usuarioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2_banco_usuarioMousePressed

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
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel contra_label1;
    private javax.swing.JLabel contra_label2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField jTextField2_banco;
    private javax.swing.JTextField jTextField2_empresa;
    private javax.swing.JTextField jTextField2_usuario2;
    private javax.swing.JLabel usuario_label2;
    // End of variables declaration//GEN-END:variables
}
