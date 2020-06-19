/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.CargoCtrl;
import controle.MovimentacaoUsuarioCtrl;
import dao.CargoDao;
import dao.MovimentacaoUsuarioDao;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cargo;
import modelo.MovimentacaoUsuario;

/**
 *
 * @author Guilherme
 */
public class TelaCargo extends javax.swing.JFrame {

    /**
     * Creates new form TelaCargo
     */
    //Caputar data e hora do computador 
    Date d = new Date();
    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//Edita no formato do SLQSERVER
    int cod_cargo;
    int Cod_movimentacao;
    TelaPrincipal tp = new TelaPrincipal();

    public TelaCargo() {
        initComponents();
        CarregarTabela();
        jRNOme.setSelected(true);
    }

    public void CarregarTabela() {
        JTCargos.getColumnModel().getColumn(0).setPreferredWidth(60);
        JTCargos.getColumnModel().getColumn(1).setPreferredWidth(300);
        DefaultTableModel modelo = (DefaultTableModel) JTCargos.getModel();
        modelo.setNumRows(0);
        CargoDao cdao = new CargoDao();

        cdao.MostrarCargo().forEach((cargo) -> {
            modelo.addRow(new Object[]{
                cargo.getCod_cargo(),
                cargo.getDesc_cargo()});
        });

    }

    public void MostrarBuscarCodigo(int codigo) {
        JTCargos.getColumnModel().getColumn(0).setPreferredWidth(60);
        JTCargos.getColumnModel().getColumn(1).setPreferredWidth(300);
        DefaultTableModel modelo = (DefaultTableModel) JTCargos.getModel();
        modelo.setNumRows(0);
        Cargo c = new Cargo();
        CargoDao cdao = new CargoDao();

        cdao.BuscarCargoCodigo(codigo).forEach((cargo) -> {
            modelo.addRow(new Object[]{
                cargo.getCod_cargo(),
                cargo.getDesc_cargo()});
        });

    }

    public void MostrarBuscarNome(String nome) {
        JTCargos.getColumnModel().getColumn(0).setPreferredWidth(60);
        JTCargos.getColumnModel().getColumn(1).setPreferredWidth(300);
        DefaultTableModel modelo = (DefaultTableModel) JTCargos.getModel();
        modelo.setNumRows(0);
        Cargo c = new Cargo();
        CargoDao cdao = new CargoDao();

        cdao.BuscarCargoNome(nome).forEach((cargo) -> {
            modelo.addRow(new Object[]{
                cargo.getCod_cargo(),
                cargo.getDesc_cargo()});
        });

    }

    public void LimparCampos() {
        TxtCod.setText("");
        TxtNome_Cargo.setText("");
    }

    public void SalvarCargo() {
        Cargo c = new Cargo();
        CargoCtrl ctrl = new CargoCtrl();
        //Primeiro verifica se tem dados nos campos
        if (TxtNome_Cargo.getText() == null || TxtNome_Cargo.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Nome Cargo é Obrigatório!");
            TxtNome_Cargo.requestFocus();
        } else {
            c.setDesc_cargo(TxtNome_Cargo.getText());
            int i = JOptionPane.showConfirmDialog(this, "Deseja realmente Salvar??");
            if (i == 0) {
                ctrl.SalvarCargoCtrl(c);
                CarregarTabela();
                LimparCampos();

                CargoDao cdao = new CargoDao();
                cdao.MostrarUltimoCargo().forEach((cargo) -> {
                    cod_cargo = cargo.getCod_cargo();
                    JOptionPane.showMessageDialog(null, "Código do cargo: " + cod_cargo);
                });
                MovimentacaoSalvar();
            }
        }
    }

    public void AtualizaCargo() {
        Cargo c = new Cargo();
        CargoCtrl ctrl = new CargoCtrl();
        //Primeiro verifica se tem dados nos campos e se foi selecionado algum registro
        if (TxtCod.getText() == null || TxtCod.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione um Registro!!");
            JTCargos.requestFocus();
        } else if (TxtNome_Cargo.getText() == null || TxtNome_Cargo.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Nome Cargo é Obrigatório!");
            TxtNome_Cargo.requestFocus();
        } else {
            c.setCod_cargo(Integer.parseInt(TxtCod.getText()));
            c.setDesc_cargo(TxtNome_Cargo.getText());
            int i = JOptionPane.showConfirmDialog(this, "Deseja realmente Atualizar??");
            if (i == 0) {
                ctrl.AtualizarCargoCtrl(c);
                MovimentacaoAtualizar();
                CarregarTabela();
                LimparCampos();
            }
        }
    }

    public void ExcluirCargo() {
        Cargo c = new Cargo();
        CargoCtrl ctrl = new CargoCtrl();
        //Primeiro verifica se tem dados nos campos e se foi selecionado algum registro
        if (TxtCod.getText() == null || TxtCod.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione um Registro!!");
            JTCargos.requestFocus();
        } else {
            int i = JOptionPane.showConfirmDialog(this, "Deseja realmente Excluir??");
            if (i == 0) {
                ctrl.ExcluirCargoCtrl(Integer.parseInt(TxtCod.getText()));
                MovimentacaoExcluir();
                CarregarTabela();
                LimparCampos();
            }
        }
    }

    public void BuscarCargo() {
        if (jRCodigo.isSelected()) {
            MostrarBuscarCodigo(Integer.parseInt(TxtConsulta.getText()));
            TxtConsulta.setText("");
        } else if (jRNOme.isSelected()) {
            MostrarBuscarNome(TxtConsulta.getText());
            TxtConsulta.setText("");
        }

    }

    public void MovimentacaoSalvar() {
        MovimentacaoUsuario m1 = new MovimentacaoUsuario();
        MovimentacaoUsuarioCtrl ctrl = new MovimentacaoUsuarioCtrl();
        MovimentacaoUsuarioDao dao = new MovimentacaoUsuarioDao();

        //m.setCod_usuario(Integer.parseInt(TxtCodUsuario.getText()));
        m1.setCod_usuario(1);
        ctrl.AbrirMovimentacaoCtrl(m1);

        //Pega o cod da movimentação que acabou de ser aberta nas linhas de cima
        dao.BuscarUltimaMovimentacao().forEach((t) -> {
            Cod_movimentacao = t.getCod_movimentacao();
        });
        //Salva os dados da movimentação
        MovimentacaoUsuario m = new MovimentacaoUsuario();

        m.setTipo_movimentacao("Salvar");
        m.setTabela_alterada("cargo");
        m.setCod_registro_alterado(cod_cargo);
        m.setData_Hora_movimentacao(form.format(d));
        m.setCod_usuario(tp.Cod_usuario);
        m.setCod_movimentacao(Cod_movimentacao);
        ctrl.SalvarMovimentacaoCtrl(m);
    }

    public void MovimentacaoAtualizar() {
        MovimentacaoUsuario m1 = new MovimentacaoUsuario();
        MovimentacaoUsuarioCtrl ctrl = new MovimentacaoUsuarioCtrl();
        MovimentacaoUsuarioDao dao = new MovimentacaoUsuarioDao();

        //m.setCod_usuario(Integer.parseInt(TxtCodUsuario.getText()));
        m1.setCod_usuario(1);
        ctrl.AbrirMovimentacaoCtrl(m1);

        //Pega o cod da movimentação que acabou de ser aberta nas linhas de cima
        dao.BuscarUltimaMovimentacao().forEach((t) -> {
            Cod_movimentacao = t.getCod_movimentacao();
        });
        //Salva os dados da movimentação
        MovimentacaoUsuario m = new MovimentacaoUsuario();

        m.setTipo_movimentacao("Atualizar");
        m.setTabela_alterada("cargo");
        m.setCod_registro_alterado(Integer.parseInt(TxtCod.getText()));
        m.setData_Hora_movimentacao(form.format(d));
        m.setCod_usuario(tp.Cod_usuario);
        m.setCod_movimentacao(Cod_movimentacao);
        ctrl.SalvarMovimentacaoCtrl(m);
    }

    public void MovimentacaoExcluir() {
        MovimentacaoUsuario m1 = new MovimentacaoUsuario();
        MovimentacaoUsuarioCtrl ctrl = new MovimentacaoUsuarioCtrl();
        MovimentacaoUsuarioDao dao = new MovimentacaoUsuarioDao();

        //m.setCod_usuario(Integer.parseInt(TxtCodUsuario.getText()));
        m1.setCod_usuario(1);
        ctrl.AbrirMovimentacaoCtrl(m1);

        //Pega o cod da movimentação que acabou de ser aberta nas linhas de cima
        dao.BuscarUltimaMovimentacao().forEach((t) -> {
            Cod_movimentacao = t.getCod_movimentacao();
        });
        //Salva os dados da movimentação
        MovimentacaoUsuario m = new MovimentacaoUsuario();

        m.setTipo_movimentacao("Excluir");
        m.setTabela_alterada("cargo");
        m.setCod_registro_alterado(Integer.parseInt(TxtCod.getText()));
        m.setData_Hora_movimentacao(form.format(d));
        m.setCod_usuario(tp.Cod_usuario);
        m.setCod_movimentacao(Cod_movimentacao);
        ctrl.SalvarMovimentacaoCtrl(m);
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
        TxtCod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtNome_Cargo = new javax.swing.JTextField();
        Opcoes = new javax.swing.JPanel();
        Salvar = new javax.swing.JButton();
        jBAtualizar = new javax.swing.JButton();
        jBExcluir = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        JBSair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTCargos = new javax.swing.JTable();
        jRCodigo = new javax.swing.JRadioButton();
        jRNOme = new javax.swing.JRadioButton();
        TxtConsulta = new javax.swing.JTextField();
        jBConsultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cargos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel1.setText("Código");

        TxtCod.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel2.setText("Nome Cargo");

        TxtNome_Cargo.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        TxtNome_Cargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNome_CargoKeyPressed(evt);
            }
        });

        Opcoes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N
        Opcoes.setPreferredSize(new java.awt.Dimension(130, 190));

        Salvar.setText("Salvar");
        Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarActionPerformed(evt);
            }
        });

        jBAtualizar.setText("Atualizar");
        jBAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizarActionPerformed(evt);
            }
        });

        jBExcluir.setText("Excluir");
        jBExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExcluirActionPerformed(evt);
            }
        });

        jButton4.setText("Limpar Campos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        JBSair.setText("Sair");
        JBSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OpcoesLayout = new javax.swing.GroupLayout(Opcoes);
        Opcoes.setLayout(OpcoesLayout);
        OpcoesLayout.setHorizontalGroup(
            OpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Salvar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBAtualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
            .addComponent(JBSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        OpcoesLayout.setVerticalGroup(
            OpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OpcoesLayout.createSequentialGroup()
                .addComponent(Salvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAtualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JBSair))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        JTCargos.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        JTCargos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome Cargo"
            }
        ));
        JTCargos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTCargosMouseClicked(evt);
            }
        });
        JTCargos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTCargosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(JTCargos);

        buttonGroup1.add(jRCodigo);
        jRCodigo.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jRCodigo.setText("Código");
        jRCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRCodigoActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRNOme);
        jRNOme.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jRNOme.setText("Nome");
        jRNOme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRNOmeActionPerformed(evt);
            }
        });

        TxtConsulta.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        TxtConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtConsultaActionPerformed(evt);
            }
        });

        jBConsultar.setText("Consultar");
        jBConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRNOme)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtConsulta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBConsultar))
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRCodigo)
                    .addComponent(jRNOme)
                    .addComponent(TxtConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBConsultar)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtNome_Cargo, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Opcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(TxtNome_Cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Opcoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
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

    private void jBAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizarActionPerformed
        AtualizaCargo();
    }//GEN-LAST:event_jBAtualizarActionPerformed

    private void SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarActionPerformed
        SalvarCargo();
    }//GEN-LAST:event_SalvarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LimparCampos();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void JTCargosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTCargosMouseClicked
        if (JTCargos.getSelectedRow() != -1) {
            TxtCod.setText(JTCargos.getValueAt(JTCargos.getSelectedRow(), 0).toString());
            TxtNome_Cargo.setText(JTCargos.getValueAt(JTCargos.getSelectedRow(), 1).toString());
        }
    }//GEN-LAST:event_JTCargosMouseClicked

    private void JBSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSairActionPerformed
        this.dispose();        // Fecha a tela
    }//GEN-LAST:event_JBSairActionPerformed

    private void jBExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcluirActionPerformed
        ExcluirCargo();
    }//GEN-LAST:event_jBExcluirActionPerformed

    private void jBConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConsultarActionPerformed
        BuscarCargo();
    }//GEN-LAST:event_jBConsultarActionPerformed

    private void TxtConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtConsultaActionPerformed
        BuscarCargo();
    }//GEN-LAST:event_TxtConsultaActionPerformed

    private void jRCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRCodigoActionPerformed
        CarregarTabela();
    }//GEN-LAST:event_jRCodigoActionPerformed

    private void jRNOmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRNOmeActionPerformed
        CarregarTabela();
    }//GEN-LAST:event_jRNOmeActionPerformed

    private void TxtNome_CargoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNome_CargoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (TxtCod.getText() == null || TxtCod.getText().trim().equals("")) {
                SalvarCargo();
            } else if (TxtCod.getText() != null) {
                AtualizaCargo();
            }
        }
    }//GEN-LAST:event_TxtNome_CargoKeyPressed

    private void JTCargosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTCargosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            ExcluirCargo();
        }
    }//GEN-LAST:event_JTCargosKeyPressed

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
            java.util.logging.Logger.getLogger(TelaCargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCargo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBSair;
    private javax.swing.JTable JTCargos;
    private javax.swing.JPanel Opcoes;
    private javax.swing.JButton Salvar;
    private javax.swing.JTextField TxtCod;
    private javax.swing.JTextField TxtConsulta;
    private javax.swing.JTextField TxtNome_Cargo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBAtualizar;
    private javax.swing.JButton jBConsultar;
    private javax.swing.JButton jBExcluir;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRCodigo;
    private javax.swing.JRadioButton jRNOme;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
