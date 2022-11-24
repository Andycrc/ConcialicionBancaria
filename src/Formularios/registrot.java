/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Formularios;

import Entidades.Render;
import Conexion.conector;
import Models.AsientoModel;
import Models.AsientoModel2;
import com.lowagie.text.Anchor;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author gergo
 */
public class registrot extends javax.swing.JPanel {

    /**
     * Creates new form registrot
     */
    String vFecha,vDescriP,vNP,vSaldoi,vCargo,vAbono;
    DefaultTableModel jtModelo,JTableModel2;
    AsientoModel cn;
    AsientoModel2 cn2;
    int contador=0;
    double contador1,contador2,contador3,contador4,FinalTot;

    
    public registrot() throws SQLException{
        initComponents();
            cn = new AsientoModel();
            cn2 = new AsientoModel2();
        this.cargoabono();
       jtModelo = (DefaultTableModel) this.tablaLB.getModel();
       JTableModel2 = (DefaultTableModel) this.tablaEC.getModel();
       this.cargarDatos();
       this.saldoInic();


        
    }
    //cargamos tablas desde db
     public void cargarDatos() throws SQLException {

        jtModelo = cn.obtenerDatos();
        this.tablaLB.setModel(jtModelo);
        this.tablaLB.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        
        JTableModel2 = cn2.obtenerDatos();
        this.tablaEC.setModel(JTableModel2);
        this.tablaEC.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        
        

    }
     //validamos que ingresen bonos o saldos de forma corretxta
    public void cargoabono(){
        if(cargoB.isSelected()==true){
                cargoJ.setEnabled(true);
                cargoJ.setText("");
                abonoJ.setText("0");
                abonoJ.setEnabled(false);
        }
        if(abonoB.isSelected()==true){
                abonoJ.setEnabled(true);
                abonoJ.setText("");
                cargoJ.setText("0");
                cargoJ.setEnabled(false);
        }
        
    }
    
    //limpio datos de ingreso
    public void limpiarjtex() {

        this.Descrip.setText("");
        fechFC.setCalendar(null);

    }
    public void saldoInic(){
        if (tablaEC.getRowCount() == 0){
//            this.panelEC.setVisible(false);
//            this.panelLB.setVisible(false);
//            this.panel1.setVisible(false);
            this.LBA.setVisible(false);
            this.ECB.setVisible(false);
            
            
        }else{
            this.procesar();
            this.IniciarB.setEnabled(false);
            this.Saldoi.setEnabled(false);
            this.labelSI.setEnabled(false);
           

            
        }
    }
    
    //procesamos datop en la tablas
    public void procesar(){
        double sumA = 0, sumC = 0,saldoInicial=0;

         //sacamos totales de abonos y cargos TABLA LB
        for (int i = 0; i < tablaEC.getRowCount(); i++) {
            if(i==0){
              saldoInicial= Double.valueOf((String) tablaEC.getValueAt(i, 5));
            }
            
            sumA = sumA + Double.valueOf((String) tablaEC.getValueAt(i, 4));
            sumC = sumC + Double.valueOf((String) tablaEC.getValueAt(i, 3));

        }
        this.abonosEC.setText(""+sumA);
        this.cargosEC.setText(""+sumC);
        this.saldoIniciarEC.setText(""+saldoInicial);
        Double VTotalEC=saldoInicial+sumC-sumA;
        this.totalEC.setText(""+VTotalEC);
        
        sumA = 0;
        sumC = 0;
        saldoInicial=0;
        //sacamos totales de abonos y cargos de TABLA EC
        for (int i = 0; i < tablaLB.getRowCount(); i++) {
            if(i==0){
                saldoInicial= Double.valueOf((String) tablaLB.getValueAt(i, 5));
                System.out.println(""+saldoInicial);

            }
            sumA = sumA + Double.valueOf((String) tablaLB.getValueAt(i, 4));
            sumC = sumC + Double.valueOf((String) tablaLB.getValueAt(i, 3));

        }
           //DATOS FINALES TABLA LB
        this.abonoslb.setText(""+sumA);
        this.cargoslb.setText(""+sumC);
        this.saldosLb.setText(""+saldoInicial);
        Double VTotalLB= saldoInicial+sumA-sumC;
        this.TotalLB.setText(""+VTotalLB);
        
    
    }
    //cuando limpiamos habilita inic
    public void InicioDinamico(){
            this.LBA.setVisible(false);
            this.ECB.setVisible(false);
            this.IniciarB.setEnabled(true);
            this.Saldoi.setEnabled(true);
            this.labelSI.setEnabled(true);
            this.Saldoi.setVisible(true);
            this.labelSI.setVisible(true);
            this.IniciarB.setVisible(true);


            
    
    }
    public void LimpiarTablas(){
        
            for (int i = 0; i < tablaLB.getRowCount(); i++) {
                jtModelo.removeRow(i);
                i-=1;
            }
            for (int i = 0; i < tablaEC.getRowCount(); i++) {
                JTableModel2.removeRow(i);
                i-=1;
            }
            
//            tablaLB.setModel(new DefaultTableModel());
//            tablaEC.setModel(new DefaultTableModel());
        
    
    }
    
    public void conci(){
        for (int i = 0; i < this.tablaLB.getRowCount(); i++) {
            for (int j = 0; j < this.tablaEC.getRowCount(); j++) {
                if ((this.tablaLB.getValueAt(i, 0).equals(this.tablaEC.getValueAt(j, 0))) && (this.tablaLB.getValueAt(i, 4).equals(this.tablaEC.getValueAt(j, 4)) && (this.tablaLB.getValueAt(i, 3).equals(this.tablaEC.getValueAt(j, 3))))) {
                    this.tablaLB.setValueAt("Conciliado", i, 6);
                    this.tablaEC.setValueAt("Conciliado", j, 6);
                    break;
                } else {
                    if (Float.parseFloat(this.tablaLB.getValueAt(i, 4).toString()) > 0) {
                        this.tablaLB.setValueAt(1, i, 6);
                        
                    } else if (Float.parseFloat(this.tablaLB.getValueAt(i, 3).toString()) > 0) {
                        this.tablaLB.setValueAt(2, i, 6);

                    }

                }

            }

        }
        for (int i = 0; i < this.tablaEC.getRowCount(); i++) {
            for (int j = 0; j < this.tablaLB.getRowCount(); j++) {
                if ((this.tablaEC.getValueAt(i, 0).equals(this.tablaLB.getValueAt(j, 0))) && (this.tablaEC.getValueAt(i, 4).equals(this.tablaLB.getValueAt(j, 4)) && (this.tablaEC.getValueAt(i, 3).equals(this.tablaLB.getValueAt(j, 3))))) {
                    this.tablaLB.setValueAt("Conciliado", i, 6);
                    this.tablaEC.setValueAt("Conciliado", j, 6);
                    break;
                } else {
                    if (Float.parseFloat(this.tablaEC.getValueAt(i, 4).toString()) > 0) {
                        this.tablaEC.setValueAt(3, i, 6);
                    } else if (Float.parseFloat(this.tablaEC.getValueAt(i, 3).toString()) > 0) {
                        this.tablaEC.setValueAt(4, i, 6);

                    }


                }

            }

        }
        
        for (int i = 0; i < this.tablaLB.getRowCount(); i++) {
            if(this.tablaLB.getValueAt(i, 6).toString()!="Conciliado"){
                 if(Float.parseFloat(this.tablaLB.getValueAt(i, 6).toString()) == 1){
                contador1+=Float.parseFloat(this.tablaLB.getValueAt(i, 4).toString());
            }
            else if(Float.parseFloat(this.tablaLB.getValueAt(i, 6).toString()) == 2){

                contador2+=Float.parseFloat(this.tablaLB.getValueAt(i, 3).toString());
                
            }
            }
           
        
        }
       

        for (int i = 0; i < this.tablaEC.getRowCount(); i++) {
                    if(this.tablaEC.getValueAt(i, 6).toString()!="Conciliado"){
                 if(Float.parseFloat(this.tablaEC.getValueAt(i, 6).toString()) == 3){
                contador3+=Float.parseFloat(this.tablaEC.getValueAt(i, 4).toString());
            }
            else if(Float.parseFloat(this.tablaEC.getValueAt(i, 6).toString()) == 4){
                                System.out.println("entre aca");

                contador4+=Float.parseFloat(this.tablaEC.getValueAt(i, 3).toString());
                
            }
            }
        
        }
//        System.out.println("El valor de los 1 es:"+contador1);
//        System.out.println("El valor de los 2 es:"+contador2);
//         System.out.println("El valor de los 3 es:"+contador3);
//        System.out.println("El valor de los 4 es:"+contador4);
//        
        
    
    
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEC = new javax.swing.JTable();
        panel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fechFC = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        np = new javax.swing.JTextField();
        Descrip = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cargoB = new javax.swing.JRadioButton();
        abonoB = new javax.swing.JRadioButton();
        cargoJ = new javax.swing.JTextField();
        abonoJ = new javax.swing.JTextField();
        LBA = new javax.swing.JButton();
        ECB = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaLB = new javax.swing.JTable();
        panelLB = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        saldosLb = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cargoslb = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        abonoslb = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TotalLB = new javax.swing.JTextField();
        panelEC = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        saldoIniciarEC = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cargosEC = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        abonosEC = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        totalEC = new javax.swing.JTextField();
        jPanel1oc = new javax.swing.JPanel();
        IniciarB = new javax.swing.JButton();
        Saldoi = new javax.swing.JTextField();
        labelSI = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setPreferredSize(getPreferredSize());

        tablaEC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaEC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero op", "Fecha ", "Descripcion", "Cargo", "Abono", "Saldo", "Eliminar"
            }
        ));
        tablaEC.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaEC.setGridColor(new java.awt.Color(255, 255, 255));
        tablaEC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaECMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaEC);
        if (tablaEC.getColumnModel().getColumnCount() > 0) {
            tablaEC.getColumnModel().getColumn(0).setPreferredWidth(50);
        }

        panel1.setBackground(new java.awt.Color(255, 255, 255));
        panel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        jLabel1.setText("Fecha de operaición:");

        fechFC.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        jLabel12.setText("Numero de operacion:");

        jLabel3.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        jLabel3.setText("Decripción:");

        cargoB.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(cargoB);
        cargoB.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        cargoB.setText("Cargo");
        cargoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargoBActionPerformed(evt);
            }
        });

        abonoB.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(abonoB);
        abonoB.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        abonoB.setText("Abono");
        abonoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abonoBActionPerformed(evt);
            }
        });

        abonoJ.setEnabled(false);

        LBA.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        LBA.setText("Agregar Libro Banco");
        LBA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LBAActionPerformed(evt);
            }
        });

        ECB.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        ECB.setText("Agregar a estado de cuenta");
        ECB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ECBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                            .addComponent(fechFC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(np)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addComponent(Descrip, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(cargoB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(cargoJ, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(abonoB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(abonoJ, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(LBA)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ECB)))
                        .addContainerGap())))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel12)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fechFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(np, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cargoB)
                            .addComponent(cargoJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(abonoB)
                            .addComponent(abonoJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(Descrip)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LBA)
                    .addComponent(ECB))
                .addContainerGap())
        );

        fechFC.getAccessibleContext().setAccessibleName("");

        tablaLB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaLB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.op", "Fecha op.", "Desc", "Cargo", "Abono", "Saldo", "Estado"
            }
        ));
        tablaLB.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaLB.setGridColor(new java.awt.Color(255, 255, 255));
        tablaLB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaLBMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaLB);
        if (tablaLB.getColumnModel().getColumnCount() > 0) {
            tablaLB.getColumnModel().getColumn(0).setPreferredWidth(50);
        }

        panelLB.setBackground(new java.awt.Color(255, 255, 255));
        panelLB.setBorder(javax.swing.BorderFactory.createTitledBorder("Resume Libro Banco"));
        panelLB.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel4.setText("Saldo Inicial:");

        saldosLb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saldosLbActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel5.setText("Total Cargos:");

        jLabel6.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel6.setText("Total Abonos:");

        jLabel7.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel7.setText("Saldo segun libro de bancos");

        javax.swing.GroupLayout panelLBLayout = new javax.swing.GroupLayout(panelLB);
        panelLB.setLayout(panelLBLayout);
        panelLBLayout.setHorizontalGroup(
            panelLBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLBLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLBLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TotalLB, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLBLayout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(abonoslb, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLBLayout.createSequentialGroup()
                            .addGroup(panelLBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(panelLBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(saldosLb, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                .addComponent(cargoslb)))))
                .addGap(0, 34, Short.MAX_VALUE))
        );
        panelLBLayout.setVerticalGroup(
            panelLBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLBLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(saldosLb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cargoslb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(panelLBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(abonoslb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(panelLBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalLB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelEC.setBackground(new java.awt.Color(255, 255, 255));
        panelEC.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen Estado de cuenta"));
        panelEC.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel8.setText("Saldo Inicial:");

        jLabel9.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel9.setText("Total Cargos:");

        jLabel10.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel10.setText("Total Abonos:");

        abonosEC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abonosECActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel11.setText("Saldo segun  Estado de cuenta");

        javax.swing.GroupLayout panelECLayout = new javax.swing.GroupLayout(panelEC);
        panelEC.setLayout(panelECLayout);
        panelECLayout.setHorizontalGroup(
            panelECLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelECLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelECLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelECLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalEC, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelECLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelECLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(abonosEC))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelECLayout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(3, 3, 3)
                            .addComponent(cargosEC))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelECLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(saldoIniciarEC, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        panelECLayout.setVerticalGroup(
            panelECLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelECLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelECLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(saldoIniciarEC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(panelECLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cargosEC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelECLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abonosEC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelECLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(totalEC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jPanel1oc.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1oc.setName(""); // NOI18N

        IniciarB.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        IniciarB.setText("Agregar");
        IniciarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarBActionPerformed(evt);
            }
        });

        labelSI.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        labelSI.setText("Saldo Inicial:");

        javax.swing.GroupLayout jPanel1ocLayout = new javax.swing.GroupLayout(jPanel1oc);
        jPanel1oc.setLayout(jPanel1ocLayout);
        jPanel1ocLayout.setHorizontalGroup(
            jPanel1ocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1ocLayout.createSequentialGroup()
                .addGroup(jPanel1ocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1ocLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelSI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Saldoi, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1ocLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(IniciarB, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1ocLayout.setVerticalGroup(
            jPanel1ocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1ocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1ocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSI)
                    .addComponent(Saldoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(IniciarB)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        jButton3.setText("Procesar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        jButton1.setText("Limpiar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        jButton2.setText("Exportar PDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        jButton5.setText("Generar informe");

        jButton6.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        jButton6.setText("Bancos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Libro Banco");

        jLabel13.setText("Libro Estado de cuenta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel1oc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel13))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelLB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelEC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel1oc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelLB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelEC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelEC.getAccessibleContext().setAccessibleName("");
        jPanel1oc.getAccessibleContext().setAccessibleName("");

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void LBAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LBAActionPerformed
        // TODO add your handling code here:
        //CREACION DE VARIABLES PARA TEXTO
        vDescriP = this.Descrip.getText();
        vCargo = this.cargoJ.getText();
        vAbono = this.abonoJ.getText();
        vNP = this.np.getText();
        vSaldoi = this.Saldoi.getText();
        
        //VALIDACIONES DEL INGRESO DE DATOS A LAS TABLAS
        if(fechFC.getDate()==null ||(Descrip.getText().trim().length() == 0) 
                || (vCargo.trim().length()==0) || (vAbono.trim().length()==0)|| (vNP.trim().length()==0)
                ){
            //MENSAJE SI ESTAN NULOS
             JOptionPane.showMessageDialog(null, "Por favor Complete los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);

    
        }
        else{
            //renderizamos boton eleminar
            tablaLB.setDefaultRenderer(Object.class, new Render());


            
            
                SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
                vFecha = DateFor.format(fechFC.getDate());

            //Iniciamos variables para convertir datos a float o double
            double vvCargo, vvAbono;
            vvCargo = Double.valueOf(vCargo);
            vvAbono = Double.valueOf(vAbono);
            //System.out.println("" + vvdebe + "" + vvhaber);

                //verficacion de numeros negativos
                if (vvCargo < 0 || vvAbono < 0) {
                    
                    JOptionPane.showMessageDialog(null, "Los valores no deben ser negativos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    JButton btn2 = new JButton("Eliminar");
                    btn2.setName("e");

                    jtModelo.addRow(new Object[]{
                        vNP, vFecha,vDescriP, vCargo,vAbono,'0','0',btn2
                    });
                    this.limpiarjtex();

                }
               
        }
       
        
    }//GEN-LAST:event_LBAActionPerformed

    private void cargoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargoBActionPerformed
        // TODO add your handling code here:
        this.cargoabono();
    }//GEN-LAST:event_cargoBActionPerformed

    private void abonoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abonoBActionPerformed
        // TODO add your handling code here:
                this.cargoabono();

    }//GEN-LAST:event_abonoBActionPerformed

    private void saldosLbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saldosLbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saldosLbActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.procesar();
        this.conci();
        conector cc = new conector();
        Connection cn = cc.conectar();
        int cont = 0;
        String consult = "insert into librobanco(op,saldoinicial,cargo,abono,descrip,fecha) values(?,?,?,?,?,?)";
        String consult2 ="insert into estadocuenta(op,saldoinicial,cargo,abono,descrip,fecha) values(?,?,?,?,?,?)";

       
        //insertamos en la base en tabla LB
             try {
                //Statement stmt = cn.createStatement();

                if (tablaEC.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "No Existen Datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    this.procesar();
                    for (int i = 0; i < tablaEC.getRowCount(); i++) {
                        vNP = (String) tablaEC.getValueAt(i, 0);
                        vFecha = (String) tablaEC.getValueAt(i, 1);
                        vDescriP = (String) tablaEC.getValueAt(i, 2);
                        vCargo = (String) tablaEC.getValueAt(i, 3);
                        vAbono = (String) tablaEC.getValueAt(i, 4);

                        PreparedStatement ps = cn.prepareStatement(consult);
                        
  
     
                            ps.setInt(1, Integer.parseInt(vNP));
                            if(tablaEC.getRowCount() > 0){
                              ps.setDouble(2, 0);

                            }else{
                                ps.setDouble(2, Double.parseDouble(vSaldoi));
                            }
                            ps.setDouble(3, Double.parseDouble(vCargo));
                            ps.setDouble(4, Double.parseDouble(vAbono));
                            ps.setString(5, vDescriP);
                            ps.setString(6, vFecha);

                       



                 
                        ps.executeUpdate();

                    }
                    JOptionPane.showMessageDialog(null, "Cambios guardados con exito. EC", "Con exito", JOptionPane.INFORMATION_MESSAGE);

                }

            } catch (Exception e) {

                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        //Insertamos en base en table EC
            try {
                //Statement stmt = cn.createStatement();

                if (tablaLB.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "No Existen Datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    for (int i = 0; i < tablaLB.getRowCount(); i++) {
                        vNP = (String) tablaLB.getValueAt(i, 0);
                        vFecha = (String) tablaLB.getValueAt(i, 1);
                        vDescriP = (String) tablaLB.getValueAt(i, 2);
                        vCargo = (String) tablaLB.getValueAt(i, 3);
                        vAbono = (String) tablaLB.getValueAt(i, 4);
                                                System.out.println(""+vNP+vFecha+vDescriP+vCargo+vAbono);


                        PreparedStatement ps1 = cn.prepareStatement(consult2);
                        
  
     
                            ps1.setInt(1, Integer.parseInt(vNP));
                            if(tablaLB.getRowCount() > 0){
                              ps1.setDouble(2, 0);

                            }else{
                                ps1.setDouble(2, Double.parseDouble(vSaldoi));
                            }
                            
                            ps1.setDouble(3, Double.parseDouble(vCargo));
                            ps1.setDouble(4, Double.parseDouble(vAbono));

                            ps1.setString(5, vDescriP);
                            ps1.setString(6, vFecha);

                       



                 
                        ps1.executeUpdate();

                    }
                    JOptionPane.showMessageDialog(null, "Cambios guardados con exito. LB", "Con exito", JOptionPane.INFORMATION_MESSAGE);

                }
                this.jButton3.setEnabled(false);
            } catch (Exception e) {

                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void IniciarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarBActionPerformed
        // TODO add your handling code here:
           
            vSaldoi = this.Saldoi.getText();
//            this.panelEC.setVisible(true);
//            this.panelLB.setVisible(true);
//            this.panel1.setVisible(true);
            this.LBA.setVisible(true);
            this.ECB.setVisible(true);
            
//            this.jPanel1oc.setVisible(false);
            
            
            
            this.IniciarB.setEnabled(false);
            this.Saldoi.setEnabled(false);
            this.labelSI.setEnabled(false);
//             

                    jtModelo.addRow(new Object[]{
                        "0", "0","Saldo Inicial","0","0",vSaldoi
                    });
                    JTableModel2.addRow(new Object[]{
                        "0", "0","Saldo Inicial","0","0",vSaldoi

                    });
        
    }//GEN-LAST:event_IniciarBActionPerformed

    private void abonosECActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abonosECActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_abonosECActionPerformed

    private void ECBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ECBActionPerformed
        // TODO add your handling code here:
        vDescriP = this.Descrip.getText();
        vCargo = this.cargoJ.getText();
        vAbono = this.abonoJ.getText();
        vNP = this.np.getText();
        vSaldoi = this.Saldoi.getText();
        
        //VALIDACIONES DEL INGRESO DE DATOS A LAS TABLAS
        if(fechFC.getDate()==null ||(Descrip.getText().trim().length() == 0) 
                || (vCargo.trim().length()==0) || (vAbono.trim().length()==0)|| (vNP.trim().length()==0)
                ){
            //MENSAJE SI ESTAN NULOS
             JOptionPane.showMessageDialog(null, "Por favor Complete los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);

    
        }
        else{
            //renderizamos boton eleminar
             tablaEC.setDefaultRenderer(Object.class, new Render());

            
            
                SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
                vFecha = DateFor.format(fechFC.getDate());

            //Iniciamos variables para convertir datos a float o double
            double vvCargo, vvAbono;
            vvCargo = Double.valueOf(vCargo);
            vvAbono = Double.valueOf(vAbono);
            //System.out.println("" + vvdebe + "" + vvhaber);

                //verficacion de numeros negativos
                if (vvCargo < 0 || vvAbono < 0) {
                    
                    JOptionPane.showMessageDialog(null, "Los valores no deben ser negativos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    JButton btn2 = new JButton("Eliminar");
                    btn2.setName("e");

                    JTableModel2.addRow(new Object[]{
                        vNP, vFecha,vDescriP, vAbono,vCargo,"0","",btn2
                    });
                    this.limpiarjtex();

                }
        }    
        
    }//GEN-LAST:event_ECBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        conector cc = new conector();
        Connection cn = cc.conectar();
        
        //dropeamos ambas tablas 
        String BorrarTablaLB = "DELETE From estadocuenta";
        String BorrarTablaEC = "DELETE From librobanco";
//        String CreamosTablaLb = "CREATE TABLE \"estadoCuenta\" (\n" +
//                        "    \"op\" INTEGER PRIMARY KEY  NOT NULL ,\n" +
//                        "    \"saldoinicial\"REAL,\n" +
//                        "    \"cargo\" REAL,\n" +
//                        "    \"abono\" REAL,\n" +
//                        "    \"descrip\" TEXT NOT NULL,\n" +
//                        "    \"fecha\" TEXT NOT NULL\n" +
//                        "\n" +
//                            ")";
//        String CreamosTablaEC = "CREATE TABLE \"librobanco\" (\n" +
//                        "    \"op\" INTEGER PRIMARY KEY  NOT NULL ,\n" +
//                        "    \"saldoinicial\"REAL,\n" +
//                        "    \"cargo\" REAL,\n" +
//                        "    \"abono\" REAL,\n" +
//                        "    \"descrip\" TEXT NOT NULL,\n" +
//                        "    \"fecha\" TEXT NOT NULL\n" +
//                        "\n" +
//                            ")";
        
        try {
           
           PreparedStatement psLB = cn.prepareStatement(BorrarTablaLB);
           PreparedStatement psEC = cn.prepareStatement(BorrarTablaEC);
           psLB.executeUpdate();
           psEC.executeUpdate();
//           PreparedStatement ps1LB = cn.prepareStatement(CreamosTablaLb);
//           PreparedStatement ps1EC = cn.prepareStatement(CreamosTablaEC);
//           ps1LB.executeUpdate();
//           ps1EC.executeUpdate();
           this.InicioDinamico();
           this.LimpiarTablas();
           this.jButton3.setEnabled(true);
          
           
           
        } catch (Exception e) {
            
        }

        
        
        




        
        
        //creamos ambas tablas
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        FrmMain main = new FrmMain();
        
        this.procesar();
        this.conci();
        Document document = new Document();
        try { 
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("Ejemplo_pdf_java.pdf"));
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            Graphics g = cb.createGraphicsShapes(PageSize.LETTER.getWidth(), PageSize.LETTER.getHeight());
            
                  //--------------------- pagina 1 --------------------------
            g.setColor(Color.GREEN);
            g.drawRect(1, 1, 593, 790);    
            
            g.setColor(new Color(154, 171, 237));
            g.fillOval(290, 90, 280, 100);
            Font font = new Font("Tahoma", Font.BOLD + Font.ITALIC, 12);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("Usuario:"+main.usuario, 50, 20);
            g.setColor(Color.BLACK);
            g.drawString("Empresa:"+main.empresa, 50, 40);

                        
            Font font1 = new Font("Tahoma", Font.BOLD + Font.ITALIC, 35);
            g.setFont(font1);
           
            g.setColor(Color.RED);
            g.drawString("Banco:", 150, 150);
            
            g.setColor(Color.WHITE);
            g.drawString(""+main.banco, 290, 150);
            g.setColor(Color.BLACK);
            Font font2 = new Font("Tahoma", Font.PLAIN, 12);
            g.setFont(font2);
            g.drawString("Saldo Según libros de bancos:", 50, 200);
            g.drawString(""+ this.TotalLB.getText(), 230, 200);
            
            g.drawString("1-Depositos no abonados:", 50, 220);
            g.drawString(""+contador1,200,220);
            g.drawString("2+Cheques no cobrados:", 50, 240);
            g.drawString(""+contador2,200,240);

            g.drawString("3-Nota de cargo no considerada libro en banco:", 50, 260);
            g.drawString(""+contador3,330,260);

            g.drawString("4+Nota de abono no considerada libro en banco:", 50, 280);
            g.drawString(""+contador4,330,280);
            double saldosegunbanco=Double.parseDouble(this.TotalLB.getText());
            double resta,suma;
            resta = contador1 + contador3;
            suma = contador2 + contador4;
            FinalTot=((saldosegunbanco+suma)-resta);
            g.drawString("Saldo de cueta validado:", 50, 320);
            g.drawString(""+FinalTot,200,320);

            
            Font font3 = new Font("Tahoma", Font.PLAIN, 15);
            document.newPage();
            Paragraph nexo = new Paragraph("NEXOS:");
            Paragraph nexo1 = new Paragraph("1-Depositos no abonados:");
            Paragraph nexo2 = new Paragraph("2-Cheques no cobrados / Retiros no considerados en Bancos:");
            Paragraph nexo3 = new Paragraph("3- Nota de Cargo no considerada en Libro Bancos (Contabilidad):");
            Paragraph nexo4 = new Paragraph("4- Nota de Abono no considerada en Libro Bancos  (Contabilidad):");

            Paragraph Separador = new Paragraph(" ");
            document.add(Separador);
            document.add(nexo);
            document.add(Separador);
            document.add(nexo1);
            
            PdfPTable t1 = new PdfPTable(6);
            t1.addCell("Numero de op");
            t1.addCell("Fecha");
            t1.addCell("Descripcion");
            t1.addCell("Cargo");
            document.add(t1);
            PdfPTable t2 = new PdfPTable(6);
            document.add(Separador);
            document.add(nexo2);
            document.add(Separador);
            t2.addCell("Numero de op");
            t2.addCell("Fecha");
            t2.addCell("Descripcion");
            t2.addCell("abonos");
            document.add(t2);
            PdfPTable t3 = new PdfPTable(6);
            document.add(Separador);
            document.add(nexo3);
            document.add(Separador);
            t3.addCell("Numero de op");
            t3.addCell("Fecha");
            t3.addCell("Descripcion");
            t3.addCell("cargos");
            document.add(t3);

            PdfPTable t4 = new PdfPTable(6);
            document.add(Separador);
            document.add(nexo4);
            document.add(Separador);
            t4.addCell("Numero de op");
            t4.addCell("Fecha");
            t4.addCell("Descripcion");
            t4.addCell("cargos");
            document.add(t4);

            
            
            

            



            

      
        document.newPage();
        document.addTitle("TABLAS DE CONTENIDOS");
        
        Paragraph paragraph = new Paragraph("Tabla de libro bancario:");

        document.add(Separador);
        document.add(paragraph);
                document.add(Separador);

        PdfPTable tabla = new PdfPTable(6);
        tabla.addCell("Numero de op");
        tabla.addCell("Fecha");
        tabla.addCell("Descripcion");
        tabla.addCell("Cargo");
        tabla.addCell("Abono");
        tabla.addCell("saldo");
            System.out.println(""+tablaLB.getRowCount());
        for (int i = 0; i < tablaLB.getRowCount(); i++) {

            
            tabla.addCell((String) tablaLB.getValueAt(i, 0));
            tabla.addCell((String) tablaLB.getValueAt(i, 1));
            tabla.addCell(tablaLB.getValueAt(i, 2).toString());
            tabla.addCell(tablaLB.getValueAt(i, 3).toString());
            tabla.addCell(tablaLB.getValueAt(i, 4).toString());
            tabla.addCell(tablaLB.getValueAt(i, 5).toString());

        }
        
        document.add(tabla);
        String lb1,lb2,lb3,lb4;
        lb1=this.saldosLb.getText();
        lb2 =this.cargoslb.getText();
        lb3=this.abonoslb.getText();
        lb4=this.TotalLB.getText();
        Paragraph saldoInicLB = new Paragraph("Saldo Inicial: "+lb1);
        Paragraph cargosLBP = new Paragraph("Total Cargos Libro bancos: "+lb2);
        Paragraph abonosLBP = new Paragraph("Total abonos Libro bancos: "+lb3);
        Paragraph totalLBP = new Paragraph("Total saldo segun Libro bancos: "+lb4);
        
        document.add(Separador);
        document.add(saldoInicLB);
         document.add(cargosLBP);
          document.add(abonosLBP);
           document.add(totalLBP);
           document.add(Separador);


        



        PdfPTable tabla1 = new PdfPTable(6);
        tabla1.addCell("Numero de op");
        tabla1.addCell("Fecha");
        tabla1.addCell("Descripcion");
        tabla1.addCell("Cargo");
        tabla1.addCell("Abono");
        tabla1.addCell("saldo");
        for (int i = 0; i < tablaEC.getRowCount(); i++) {
                        
            tabla1.addCell((String) tablaEC.getValueAt(i, 0));
            tabla1.addCell((String) tablaEC.getValueAt(i, 1));
            tabla1.addCell(tablaEC.getValueAt(i, 2).toString());
            tabla1.addCell(tablaEC.getValueAt(i, 3).toString());
            tabla1.addCell(tablaEC.getValueAt(i, 4).toString());
            tabla1.addCell(tablaEC.getValueAt(i, 5).toString());
        }
        document.add(tabla1);
        
        String ec1,ec2,ec3,ec4;
        ec1=this.saldoIniciarEC.getText();
        ec2 =this.cargosEC.getText();
        ec3 =this.abonosEC.getText();
        ec4=this.totalEC.getText();

        Paragraph saldoInicECP = new Paragraph("Saldo Inicial: "+ec1);
        Paragraph cargosECP = new Paragraph("Total Cargos Libro bancos: "+ec2);
        Paragraph abonosECP = new Paragraph("Total abonos Libro bancos: "+ec3);
        Paragraph totalECP = new Paragraph("Total saldo segun Libro bancos: "+ec4);
        
        document.add(Separador);
        document.add(saldoInicLB);
         document.add(cargosECP);
          document.add(abonosECP);
           document.add(totalECP);
           document.add(Separador);



        
   
        
        document.close();
           
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(registrot.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (DocumentException ex) {
            Logger.getLogger(registrot.class.getName()).log(Level.SEVERE, null, ex);
        }
   


      



 
        try{
            File path = new File ("Ejemplo_pdf_java.pdf");
            Desktop.getDesktop().open(path);
            
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void tablaLBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaLBMouseClicked
        // TODO add your handling code here:
           int column = tablaLB.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tablaLB.getRowHeight();

        if (row < tablaLB.getRowCount() && row >= 0 && column < tablaLB.getColumnCount() && column >= 0) {
            Object value = tablaLB.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

               
                if (boton.getName().equals("e")) {
                    int a = JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                    if (a == JOptionPane.OK_OPTION) {
                        jtModelo.removeRow(tablaLB.getSelectedRow());

                    }
                    //EVENTOS ELIMINAR
                }
            }
        }
    }//GEN-LAST:event_tablaLBMouseClicked

    private void tablaECMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaECMouseClicked
        // TODO add your handling code here:
         int column = tablaEC.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tablaEC.getRowHeight();

        if (row < tablaEC.getRowCount() && row >= 0 && column < tablaEC.getColumnCount() && column >= 0) {
            Object value = tablaEC.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getName().equals("e")) {
                    int a = JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                    if (a == JOptionPane.OK_OPTION) {
                        jtModelo.removeRow(tablaEC.getSelectedRow());

                    }
                    //EVENTOS ELIMINAR
                }
            }
        }
    }//GEN-LAST:event_tablaECMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Descrip;
    private javax.swing.JButton ECB;
    private javax.swing.JButton IniciarB;
    private javax.swing.JButton LBA;
    private javax.swing.JTextField Saldoi;
    private javax.swing.JTextField TotalLB;
    private javax.swing.JRadioButton abonoB;
    private javax.swing.JTextField abonoJ;
    private javax.swing.JTextField abonosEC;
    private javax.swing.JTextField abonoslb;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton cargoB;
    private javax.swing.JTextField cargoJ;
    private javax.swing.JTextField cargosEC;
    private javax.swing.JTextField cargoslb;
    private com.toedter.calendar.JDateChooser fechFC;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JPanel jPanel1oc;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelSI;
    private javax.swing.JTextField np;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panelEC;
    private javax.swing.JPanel panelLB;
    private javax.swing.JTextField saldoIniciarEC;
    private javax.swing.JTextField saldosLb;
    private javax.swing.JTable tablaEC;
    private javax.swing.JTable tablaLB;
    private javax.swing.JTextField totalEC;
    // End of variables declaration//GEN-END:variables
}
