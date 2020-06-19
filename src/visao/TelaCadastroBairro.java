/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.BairroCtrl;
import dao.BairroDao;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Bairro;

/**
 *
 * @author Suporte T.I
 */
public class TelaCadastroBairro extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroCidade
     */
    public TelaCadastroBairro() {
        initComponents();
        CarregarTabela();
        jRNome.setSelected(true);
        TxtNomeBairro.requestFocus();
    }

    public void CarregarTabela() {
        jTBairro.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTBairro.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTBairro.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTBairro.getColumnModel().getColumn(3).setPreferredWidth(100);
        DefaultTableModel modelo = (DefaultTableModel) jTBairro.getModel();
        modelo.setNumRows(0);
        BairroDao bdao = new BairroDao();

        bdao.MostrarBairro().forEach((b) -> {
            modelo.addRow(new Object[]{
                b.getCod_bairro(),
                b.getDesc_bairro(),
                b.getCod_cidade(),
                b.getNome_cidade()
            });
        });
    }

    public void MostrarBairroCodigo(int codigo) {
        jTBairro.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTBairro.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTBairro.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTBairro.getColumnModel().getColumn(3).setPreferredWidth(100);
        DefaultTableModel modelo = (DefaultTableModel) jTBairro.getModel();
        modelo.setNumRows(0);
        BairroDao bdao = new BairroDao();

        bdao.MostrarBairroCodigo(codigo).forEach((b) -> {
            modelo.addRow(new Object[]{
                b.getCod_bairro(),
                b.getDesc_bairro(),
                b.getCod_cidade(),
                b.getNome_cidade()
            });
        });
    }

    public void MostrarBairroNome(String nome) {
        jTBairro.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTBairro.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTBairro.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTBairro.getColumnModel().getColumn(3).setPreferredWidth(100);
        DefaultTableModel modelo = (DefaultTableModel) jTBairro.getModel();
        modelo.setNumRows(0);
        BairroDao bdao = new BairroDao();

        bdao.MostrarBairroNome(nome).forEach((b) -> {
            modelo.addRow(new Object[]{
                b.getCod_bairro(),
                b.getDesc_bairro(),
                b.getCod_cidade(),
                b.getNome_cidade()
            });
        });
    }

    public void LimparCampos() {
        txtCod_Bairro.setText("");
        TxtNomeBairro.setText("");
        txtCodCidade.setText("");
        txtConsulta.setText("");
        txtCidade.setText("");
    }

    public void SalvarBairro() {
        Bairro b = new Bairro();
        BairroCtrl ctrl = new BairroCtrl();
        if (TxtNomeBairro.getText() == null || TxtNomeBairro.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Nome Bairro é obrigatório !!");
            TxtNomeBairro.requestFocus();
        } else if (txtCodCidade.getText() == null || txtCodCidade.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Cidade, é obrigatório !!");
            jBBuscarCidade.requestFocus();
        } else {
            b.setDesc_bairro(TxtNomeBairro.getText());
            b.setCod_cidade(Integer.parseInt(txtCodCidade.getText()));

            int i = JOptionPane.showConfirmDialog(this, "Deseja realmente salvar?");
            // 0=SIM 1==NÃO 2==Cancelar
            if (i == 0) {
                ctrl.SalvarCtlrBairro(b);
                LimparCampos();
                CarregarTabela();
                TxtNomeBairro.requestFocus();
            }
        }
    }

    public void AtualizaBairro() {
        Bairro b = new Bairro();
        BairroCtrl ctrl = new BairroCtrl();
        if (TxtNomeBairro.getText() == null || TxtNomeBairro.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Nome Bairro é obrigatório !!");
            TxtNomeBairro.requestFocus();
        } else if (txtCodCidade.getText() == null || txtCodCidade.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Cidade, é obrigatório !!");
            jBBuscarCidade.requestFocus();
        } else {
            b.setCod_bairro(Integer.parseInt(txtCod_Bairro.getText()));
            b.setDesc_bairro(TxtNomeBairro.getText());
            b.setCod_cidade(Integer.parseInt(txtCodCidade.getText()));

            int i = JOptionPane.showConfirmDialog(this, "Deseja realmente atualizar?");
            // 0=SIM 1==NÃO 2==Cancelar
            if (i == 0) {
                ctrl.AtualizarCtrlBairro(b);
                LimparCampos();
                CarregarTabela();
                jTBairro.setRowSelectionInterval(0, 0);
            }
        }
    }

    public void ExcluirBairro() {
        Bairro b = new Bairro();
        BairroCtrl ctrl = new BairroCtrl();
        if (txtCod_Bairro.getText() == null || txtCod_Bairro.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!!");
            jTBairro.setRowSelectionInterval(0, 0);
        } else {
            int i = JOptionPane.showConfirmDialog(this, "Deseja realmente Excluir??");
            if (i == 0) {
                ctrl.ExcluirCtrlBairro(Integer.parseInt(txtCod_Bairro.getText()));
            }
        }
    }

    public void TelaPesquisaCidade() {
        TelaPesquisaCidade tlm = new TelaPesquisaCidade(this, true);
        tlm.setVisible(true);
        txtCidade.setText(tlm.Cidade);
        txtCodCidade.setText(tlm.Cod);
    }

    public void SelecionarItemTabelaMouse() {
        if (jTBairro.getSelectedRow() != -1) {
            txtCod_Bairro.setText(jTBairro.getValueAt(jTBairro.getSelectedRow(), 0).toString());
            TxtNomeBairro.setText(jTBairro.getValueAt(jTBairro.getSelectedRow(), 1).toString());
            txtCodCidade.setText(jTBairro.getValueAt(jTBairro.getSelectedRow(), 2).toString());
            txtCidade.setText(jTBairro.getValueAt(jTBairro.getSelectedRow(), 3).toString());
            //txtCod_Bairro.requestFocus();
        }

    }

    public void Consultar() {
        if (jRCodigo.isSelected()) {
            MostrarBairroCodigo(Integer.parseInt(txtConsulta.getText()));
            LimparCampos();
        } else if (jRNome.isSelected()) {
            MostrarBairroNome(txtConsulta.getText());
            LimparCampos();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCod_Bairro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtNomeBairro = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jBSalvar = new javax.swing.JButton();
        jBAtualizar = new javax.swing.JButton();
        jBExcluir = new javax.swing.JButton();
        jBLimparCampos = new javax.swing.JButton();
        jBSair = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCodCidade = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jRCodigo = new javax.swing.JRadioButton();
        jRNome = new javax.swing.JRadioButton();
        txtConsulta = new javax.swing.JTextField();
        jBConsultar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBairro = new javax.swing.JTable();
        txtCidade = new javax.swing.JTextField();
        jBBuscarCidade = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bairros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel1.setText("Código");

        txtCod_Bairro.setEditable(false);
        txtCod_Bairro.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel2.setText("Nome Bairro");

        TxtNomeBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNomeBairroActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N

        jBSalvar.setText("Salvar");
        jBSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalvarActionPerformed(evt);
            }
        });

        jBAtualizar.setText("Atualizar");

        jBExcluir.setText("Excluir");

        jBLimparCampos.setText("Limpar Campos");
        jBLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimparCamposActionPerformed(evt);
            }
        });

        jBSair.setText("Sair");
        jBSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBLimparCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
            .addComponent(jBSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jBSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAtualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBLimparCampos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 301, Short.MAX_VALUE)
                .addComponent(jBSair))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel3.setText("Cidade");

        txtCodCidade.setEditable(false);
        txtCodCidade.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonGroup1.add(jRCodigo);
        jRCodigo.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jRCodigo.setText("Código");
        jRCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRCodigoActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRNome);
        jRNome.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jRNome.setText("Nome");
        jRNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRNomeActionPerformed(evt);
            }
        });

        txtConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultaActionPerformed(evt);
            }
        });

        jBConsultar.setText("Consultar");
        jBConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConsultarActionPerformed(evt);
            }
        });

        jTBairro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Bairro", "Código Cidade", "Nome Cidade"
            }
        ));
        jTBairro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBairroMouseClicked(evt);
            }
        });
        jTBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTBairroKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTBairro);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jRCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBConsultar))
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRCodigo)
                    .addComponent(jRNome)
                    .addComponent(txtConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBConsultar))
                .addContainerGap())
        );

        txtCidade.setEditable(false);
        txtCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidadeActionPerformed(evt);
            }
        });

        jBBuscarCidade.setText("+");
        jBBuscarCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarCidadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCod_Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtNomeBairro))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBBuscarCidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCidade)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCod_Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(TxtNomeBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCodCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBBuscarCidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed
        SalvarBairro();
    }//GEN-LAST:event_jBSalvarActionPerformed

    private void jBSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBSairActionPerformed

    private void jBBuscarCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarCidadeActionPerformed
        if (txtCidade.getText() == null || txtCidade.getText().trim().equals("")) {
            TelaPesquisaCidade();
        } else if (txtCod_Bairro.getText() == null || txtCod_Bairro.getText().trim().equals("")) {
            SalvarBairro();
        } else if (txtCod_Bairro.getText() != null) {
            TelaPesquisaCidade();
            if(txtCod_Bairro.getText() != null){
                AtualizaBairro();
            }
            
        }
    }//GEN-LAST:event_jBBuscarCidadeActionPerformed

    private void TxtNomeBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNomeBairroActionPerformed
        TelaPesquisaCidade();
        jBBuscarCidade.requestFocus();
    }//GEN-LAST:event_TxtNomeBairroActionPerformed

    private void txtCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidadeActionPerformed
        if (txtCod_Bairro.getText() == null) {
            SalvarBairro();
        } else if (txtCod_Bairro.getText() != null) {
            AtualizaBairro();
            
        }
    }//GEN-LAST:event_txtCidadeActionPerformed

    private void jTBairroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBairroMouseClicked
        SelecionarItemTabelaMouse();
    }//GEN-LAST:event_jTBairroMouseClicked

    private void jTBairroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTBairroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCod_Bairro.setText(jTBairro.getValueAt(jTBairro.getSelectedRow(), 0).toString());
            TxtNomeBairro.setText(jTBairro.getValueAt(jTBairro.getSelectedRow(), 1).toString());
            txtCodCidade.setText(jTBairro.getValueAt(jTBairro.getSelectedRow(), 2).toString());
            txtCidade.setText(jTBairro.getValueAt(jTBairro.getSelectedRow(), 3).toString());
            
        } else if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            ExcluirBairro();
        }
    }//GEN-LAST:event_jTBairroKeyPressed

    private void jBLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimparCamposActionPerformed
        LimparCampos();
    }//GEN-LAST:event_jBLimparCamposActionPerformed

    private void txtConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultaActionPerformed
        Consultar();
    }//GEN-LAST:event_txtConsultaActionPerformed

    private void jBConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConsultarActionPerformed
        Consultar();
    }//GEN-LAST:event_jBConsultarActionPerformed

    private void jRCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRCodigoActionPerformed
        LimparCampos();
    }//GEN-LAST:event_jRCodigoActionPerformed

    private void jRNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRNomeActionPerformed
        LimparCampos();
    }//GEN-LAST:event_jRNomeActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroBairro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroBairro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroBairro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroBairro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroBairro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtNomeBairro;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBAtualizar;
    private javax.swing.JButton jBBuscarCidade;
    private javax.swing.JButton jBConsultar;
    private javax.swing.JButton jBExcluir;
    private javax.swing.JButton jBLimparCampos;
    private javax.swing.JButton jBSair;
    private javax.swing.JButton jBSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRCodigo;
    private javax.swing.JRadioButton jRNome;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTBairro;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCodCidade;
    private javax.swing.JTextField txtCod_Bairro;
    private javax.swing.JTextField txtConsulta;
    // End of variables declaration//GEN-END:variables
}
