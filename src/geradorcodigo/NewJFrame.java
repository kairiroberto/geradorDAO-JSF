/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorcodigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author kairi
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        
        String curDir = System.getProperty("user.dir");
        System.out.println(curDir);
        File file = new File(curDir + "\\crud\\model");
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(curDir + "\\crud\\model\\Pessoa.java");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpBD = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcbSGBD = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jtfHost = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfPorta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfBD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfLogin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfSenha = new javax.swing.JTextField();
        jbTestar = new javax.swing.JButton();
        jbGerar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jcbTabela = new javax.swing.JComboBox<>();
        jpClasses = new javax.swing.JPanel();
        jtpLinguagens = new javax.swing.JTabbedPane();
        jpJava = new javax.swing.JPanel();
        jtpMVCJava = new javax.swing.JTabbedPane();
        jpModelJava = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtaModelJava = new javax.swing.JTextArea();
        jpDAOJava = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtaDAOJava = new javax.swing.JTextArea();
        jpControllerJava = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jpViewJava = new javax.swing.JPanel();
        jtpFrameworkJava = new javax.swing.JTabbedPane();
        jpJSF = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaViewJSFJava = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Banco de dados: ");

        jcbSGBD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MySQL", "PostgreSQL", "MongoDB" }));

        jLabel2.setText("Server Host:");

        jtfHost.setText("localhost");

        jLabel3.setText("Porta:");

        jtfPorta.setText("3306");

        jLabel4.setText("Nome do banco:");

        jtfBD.setText("test");

        jLabel5.setText("Nome do usuário:");

        jtfLogin.setText("root");

        jLabel6.setText("Senha:");

        jbTestar.setText("Testar Conexao");
        jbTestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTestarActionPerformed(evt);
            }
        });

        jbGerar.setText("OK");
        jbGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGerarActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jLabel7.setText("tabela:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(20, 20, 20)
                        .addComponent(jtfLogin))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(21, 21, 21)
                                        .addComponent(jcbSGBD, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(48, 48, 48)
                                        .addComponent(jtfHost, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtfPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(26, 26, 26)
                                        .addComponent(jtfBD))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(79, 79, 79)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jcbTabela, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jtfSenha)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jbTestar)
                                                .addGap(18, 18, 18)
                                                .addComponent(jbGerar)
                                                .addGap(18, 18, 18)
                                                .addComponent(jbCancelar)
                                                .addGap(0, 0, Short.MAX_VALUE))))))
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(jcbSGBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(jtfBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel5))
                    .addComponent(jtfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addComponent(jtfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jcbTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbTestar)
                    .addComponent(jbGerar)
                    .addComponent(jbCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpBDLayout = new javax.swing.GroupLayout(jpBD);
        jpBD.setLayout(jpBDLayout);
        jpBDLayout.setHorizontalGroup(
            jpBDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jpBDLayout.setVerticalGroup(
            jpBDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Banco de dados", jpBD);

        jtaModelJava.setColumns(20);
        jtaModelJava.setRows(5);
        jScrollPane5.setViewportView(jtaModelJava);

        javax.swing.GroupLayout jpModelJavaLayout = new javax.swing.GroupLayout(jpModelJava);
        jpModelJava.setLayout(jpModelJavaLayout);
        jpModelJavaLayout.setHorizontalGroup(
            jpModelJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpModelJavaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpModelJavaLayout.setVerticalGroup(
            jpModelJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpModelJavaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpMVCJava.addTab("Model", jpModelJava);

        jtaDAOJava.setColumns(20);
        jtaDAOJava.setRows(5);
        jScrollPane4.setViewportView(jtaDAOJava);

        javax.swing.GroupLayout jpDAOJavaLayout = new javax.swing.GroupLayout(jpDAOJava);
        jpDAOJava.setLayout(jpDAOJavaLayout);
        jpDAOJavaLayout.setHorizontalGroup(
            jpDAOJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDAOJavaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpDAOJavaLayout.setVerticalGroup(
            jpDAOJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDAOJavaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpMVCJava.addTab("DAO", jpDAOJava);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        javax.swing.GroupLayout jpControllerJavaLayout = new javax.swing.GroupLayout(jpControllerJava);
        jpControllerJava.setLayout(jpControllerJavaLayout);
        jpControllerJavaLayout.setHorizontalGroup(
            jpControllerJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpControllerJavaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpControllerJavaLayout.setVerticalGroup(
            jpControllerJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpControllerJavaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpMVCJava.addTab("Controller", jpControllerJava);

        jtaViewJSFJava.setColumns(20);
        jtaViewJSFJava.setRows(5);
        jScrollPane1.setViewportView(jtaViewJSFJava);

        javax.swing.GroupLayout jpJSFLayout = new javax.swing.GroupLayout(jpJSF);
        jpJSF.setLayout(jpJSFLayout);
        jpJSFLayout.setHorizontalGroup(
            jpJSFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpJSFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpJSFLayout.setVerticalGroup(
            jpJSFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpJSFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpFrameworkJava.addTab("JSF", jpJSF);

        javax.swing.GroupLayout jpViewJavaLayout = new javax.swing.GroupLayout(jpViewJava);
        jpViewJava.setLayout(jpViewJavaLayout);
        jpViewJavaLayout.setHorizontalGroup(
            jpViewJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 752, Short.MAX_VALUE)
            .addGroup(jpViewJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpViewJavaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jtpFrameworkJava)
                    .addContainerGap()))
        );
        jpViewJavaLayout.setVerticalGroup(
            jpViewJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
            .addGroup(jpViewJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpViewJavaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jtpFrameworkJava)
                    .addContainerGap()))
        );

        jtpMVCJava.addTab("View", jpViewJava);

        javax.swing.GroupLayout jpJavaLayout = new javax.swing.GroupLayout(jpJava);
        jpJava.setLayout(jpJavaLayout);
        jpJavaLayout.setHorizontalGroup(
            jpJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
            .addGroup(jpJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpJavaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jtpMVCJava)
                    .addContainerGap()))
        );
        jpJavaLayout.setVerticalGroup(
            jpJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
            .addGroup(jpJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpJavaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jtpMVCJava)
                    .addContainerGap()))
        );

        jtpLinguagens.addTab("Java", jpJava);

        javax.swing.GroupLayout jpClassesLayout = new javax.swing.GroupLayout(jpClasses);
        jpClasses.setLayout(jpClassesLayout);
        jpClassesLayout.setHorizontalGroup(
            jpClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
            .addGroup(jpClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpClassesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jtpLinguagens)
                    .addContainerGap()))
        );
        jpClassesLayout.setVerticalGroup(
            jpClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 457, Short.MAX_VALUE)
            .addGroup(jpClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpClassesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jtpLinguagens)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Classes", jpClasses);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton1.setText("Abrir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Visualizar", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String curDir = System.getProperty("user.dir");
        File file = new File(curDir + "\\crud");
        jFileChooser1.setCurrentDirectory(file);
        int result = jFileChooser1.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                // user selects a file
                Scanner input = new Scanner(jFileChooser1.getSelectedFile());
                StringBuffer sb = new StringBuffer();
                while (input.hasNext()) {
                    sb.append(input.nextLine() + "\n");
                }
                input.close();
                jTextArea1.setText(sb.toString());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbTestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTestarActionPerformed
        // TODO add your handling code here:
        jcbTabela.removeAllItems();
        GeradorModel geradorModel = new GeradorModel();
        for (String s : geradorModel.getTabelas(jcbSGBD.getSelectedItem().toString().toLowerCase(), 
                jtfLogin.getText(), 
                jtfSenha.getText(), 
                jtfBD.getText(), 
                jtfHost.getText(), 
                jtfPorta.getText())) {
            jcbTabela.addItem(s);
        }
    }//GEN-LAST:event_jbTestarActionPerformed

    private void jbGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGerarActionPerformed
        // TODO add your handling code here:
        GeradorModel gm = new GeradorModel();
        //System.out.println(jcbSGBD.getSelectedItem());
        //gm.gerarClasseModelo(sgbd, login, senha, bd, host, porta);
        ///*
        HashMap<String, List<HashMap<String, String>>> tabela = gm.getClasseModelo(jcbSGBD.getSelectedItem().toString().toLowerCase(), 
                jtfLogin.getText(), 
                jtfSenha.getText(), 
                jtfBD.getText().toLowerCase(), 
                jtfHost.getText().toLowerCase(), 
                jtfPorta.getText().toLowerCase(),
                jcbTabela.getSelectedItem().toString());
        //*/
        jtaModelJava.setText(gm.getArquivoClasse(tabela));
        GeradorDAO gdao = new GeradorDAO();
        jtaDAOJava.setText(gdao.getDAOAtributos(tabela));
        GeradorView gview = new GeradorView();
        jtaViewJSFJava.setText(gview.getJSF(tabela));
    }//GEN-LAST:event_jbGerarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbGerar;
    private javax.swing.JButton jbTestar;
    private javax.swing.JComboBox<String> jcbSGBD;
    private javax.swing.JComboBox<String> jcbTabela;
    private javax.swing.JPanel jpBD;
    private javax.swing.JPanel jpClasses;
    private javax.swing.JPanel jpControllerJava;
    private javax.swing.JPanel jpDAOJava;
    private javax.swing.JPanel jpJSF;
    private javax.swing.JPanel jpJava;
    private javax.swing.JPanel jpModelJava;
    private javax.swing.JPanel jpViewJava;
    private javax.swing.JTextArea jtaDAOJava;
    private javax.swing.JTextArea jtaModelJava;
    private javax.swing.JTextArea jtaViewJSFJava;
    private javax.swing.JTextField jtfBD;
    private javax.swing.JTextField jtfHost;
    private javax.swing.JTextField jtfLogin;
    private javax.swing.JTextField jtfPorta;
    private javax.swing.JTextField jtfSenha;
    private javax.swing.JTabbedPane jtpFrameworkJava;
    private javax.swing.JTabbedPane jtpLinguagens;
    private javax.swing.JTabbedPane jtpMVCJava;
    // End of variables declaration//GEN-END:variables
}
