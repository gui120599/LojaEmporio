/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.MovimentacaoUsuarioCtrl;
import controle.UfCtrl;
import dao.ItensMovimentacaoUsuarioDao;
import dao.LoginSessaoDao;
import dao.MovimentacaoUsuarioDao;
import dao.UfDao;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ItensMovimentacaoUsuario;
import modelo.MovimentacaoUsuario;
import modelo.Uf;

/**
 *
 * @author Suporte T.I
 */
public class TelaCadastroUf extends javax.swing.JFrame {

    /**
     * Creates new form TelaUf
     */
    //VARIÁVEIS QUE IREMOS UTILIZAR NESSA TELA
    String desc_uf;//Utilizada para fazermos a comparação de registros
    String ipDaMaquina;//Utilizada para guardar o ip da maquina em quanto a tela está aberta

    int Cod_movimentacao,
            Cod_uf,
            Cod_usuario;
    

    //Pegar data e hora no padrão que o Mysql usa
    Date d = new Date();
    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public TelaCadastroUf() {
        initComponents();
        //Ao abrir a tela ele executa os metodo abaixo 
        CarregaTabela();
        PegarIp();
    }
    
    public void PegarIp() {
        Enumeration nis = null;
        String ip = null;
        try {
            nis = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
        }

        while (nis.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) nis.nextElement();
            Enumeration ias = ni.getInetAddresses();

            while (ias.hasMoreElements()) {
                InetAddress ia = (InetAddress) ias.nextElement();

                if (ia.getHostAddress().contains("192.168")) {//Nesse if está a charada, sendo que eu sei que meu ip começa com 10.132, por exemplo
                    ip = ia.getHostAddress();
                }
                //System.out.println("Teste " + ia.getHostAddress());
                if (!ni.getName().equals("lo")) {
                    //System.out.println(ia.getHostAddress());
                }
            }
        }

        //System.out.println("IP: " + ip);
        ipDaMaquina = ip;
        
        LoginSessaoDao dao = new LoginSessaoDao();
        dao.BuscarSessao(ipDaMaquina).forEach((c) -> {
            Cod_usuario = c.getCod_usuario();
        });
    }

    public void CarregaTabela() {
        //Seta a largura das colunas da tabela
        jTUf.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTUf.getColumnModel().getColumn(1).setPreferredWidth(300);
        //Instância a tabela para ser usada na tela
        DefaultTableModel modelo = (DefaultTableModel) jTUf.getModel();
        modelo.setNumRows(0);
        //Executa o método de busca que foi criado no Dao
        UfDao udao = new UfDao();
        udao.Mostrar().forEach((uf) -> {
            modelo.addRow(new Object[]{
                uf.getCod_uf(),
                uf.getDesc_uf()});
        });

    }

    public void LimparCampos() {
        //Método para limpar campos da tela
        txtCod_uf.setText("");
        txtDesc_uf.setText("");
    }

    public void SalvarUf() {
        Uf u = new Uf();
        UfCtrl ctrl = new UfCtrl();
        if (txtDesc_uf.getText() == null || txtDesc_uf.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Sigla Uf é Obrigatório!");
            txtDesc_uf.requestFocus();
        } else {
            u.setDesc_uf(txtDesc_uf.getText());
            ctrl.SalvarUfCtrl(u);
            AbrirMovimentacaoUsuario();
            
            //Busca o código que acabou de ser gerado para inserir na tabela de movimentação_usuario
            UfDao udao = new UfDao();
            udao.Mostrar().forEach((uf) -> {

                Cod_uf = uf.getCod_uf();
            });
            
            SalvarMovimentacaoUsuario_Salvar();
            LimparCampos();
            CarregaTabela();

        }
    }

    public void ExcluirUf() {
        Uf u = new Uf();
        UfCtrl ctrl = new UfCtrl();
        if (txtCod_uf.getText() == null || txtCod_uf.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!!");
            jTUf.requestFocus();
        } else if (txtDesc_uf.getText() == null || txtDesc_uf.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Sigla Uf é Obrigatório!");
            txtDesc_uf.requestFocus();
        } else {
            ctrl.ExcluirUfCtrl(Integer.parseInt(txtCod_uf.getText()));
            AbrirMovimentacaoUsuario();
            SalvarMovimentacaoUsuario_Exclusao();
            CarregaTabela();
            LimparCampos();
        }
    }

    public void AtualizarUf() {
        Uf u = new Uf();
        UfCtrl ctrl = new UfCtrl();
        if (txtCod_uf.getText() == null || txtCod_uf.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!!");
            jTUf.requestFocus();
        } else if (txtDesc_uf.getText() == null || txtDesc_uf.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Sigla Uf é Obrigatório!");
            txtDesc_uf.requestFocus();
        } else {
            u.setCod_uf(Integer.parseInt(txtCod_uf.getText()));
            u.setDesc_uf(txtDesc_uf.getText());
            ctrl.AtualizarUfCtrl(u);
            AbrirMovimentacaoUsuario();
            SalvarMovimentacaoUsuario_Atualizacao();
            CompararDadosAtualizados();
            LimparCampos();
            CarregaTabela();
        }
    }

    public void AbrirMovimentacaoUsuario() {
        MovimentacaoUsuario m = new MovimentacaoUsuario();
        MovimentacaoUsuarioCtrl ctrl = new MovimentacaoUsuarioCtrl();
        MovimentacaoUsuarioDao dao = new MovimentacaoUsuarioDao();

        //insere o cod_usuario para abrir um registro na tabela de movimentação
        m.setCod_usuario(Cod_usuario);
        ctrl.AbrirMovimentacaoCtrl(m);

        //Pega o cod da movimentação que acabou de ser aberta nas linhas de cima
        dao.BuscarUltimaMovimentacao().forEach((t) -> {
            Cod_movimentacao = t.getCod_movimentacao();
        });

    }

    public void SalvarMovimentacaoUsuario_Atualizacao() {
        MovimentacaoUsuario m = new MovimentacaoUsuario();
        MovimentacaoUsuarioCtrl ctrl = new MovimentacaoUsuarioCtrl();

        m.setTipo_movimentacao("Atualização");
        m.setTabela_alterada("uf");
        m.setCod_registro_alterado(Integer.parseInt(txtCod_uf.getText()));
        m.setData_Hora_movimentacao(form.format(d));
        m.setCod_usuario(Cod_usuario);
        m.setCod_movimentacao(Cod_movimentacao);
        ctrl.SalvarMovimentacaoCtrl(m);

    }

    public void SalvarMovimentacaoUsuario_Exclusao() {
        MovimentacaoUsuario m = new MovimentacaoUsuario();
        MovimentacaoUsuarioCtrl ctrl = new MovimentacaoUsuarioCtrl();

        m.setTipo_movimentacao("Excluir");
        m.setTabela_alterada("uf");
        m.setCod_registro_alterado(Integer.parseInt(txtCod_uf.getText()));
        m.setData_Hora_movimentacao(form.format(d));
        m.setCod_usuario(Cod_usuario);
        m.setCod_movimentacao(Cod_movimentacao);
        ctrl.SalvarMovimentacaoCtrl(m);

    }

    public void SalvarMovimentacaoUsuario_Salvar() {
        if (Cod_uf == 0) {
            JOptionPane.showMessageDialog(null, "Não deu tempo!!");
        } else {
            MovimentacaoUsuario m = new MovimentacaoUsuario();
            MovimentacaoUsuarioCtrl ctrl = new MovimentacaoUsuarioCtrl();

            m.setTipo_movimentacao("Salvar");
            m.setTabela_alterada("uf");
            m.setCod_registro_alterado(Cod_uf);
            m.setData_Hora_movimentacao(form.format(d));
            m.setCod_usuario(Cod_usuario);
            m.setCod_movimentacao(Cod_movimentacao);
            ctrl.SalvarMovimentacaoCtrl(m);
        }

    }

    public void CompararDadosAtualizados() {
        ItensMovimentacaoUsuario m = new ItensMovimentacaoUsuario();
        ItensMovimentacaoUsuarioDao dao = new ItensMovimentacaoUsuarioDao();
        MovimentacaoUsuarioDao dao2 = new MovimentacaoUsuarioDao();

        if (!desc_uf.equals(txtDesc_uf.getText())) {
            
            dao2.BuscarUltimaMovimentacao().forEach((t) -> {
                m.setCod_movimentacao(t.getCod_movimentacao());
            });

            m.setValor_antigo(desc_uf);
            m.setValor_novo(txtDesc_uf.getText());
            m.setNome_coluna("Desc_uf");

            dao.SalavarItensMovimentacao(m);

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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCod_uf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDesc_uf = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jBSalvar = new javax.swing.JButton();
        jBsAIR = new javax.swing.JButton();
        jBExcluir = new javax.swing.JButton();
        jBLimparCampos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTUf = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro Uf´s", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel1.setText("Código Uf");

        txtCod_uf.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel2.setText("Sigla Uf");

        txtDesc_uf.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        txtDesc_uf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDesc_ufKeyPressed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(130, 376));

        jBSalvar.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jBSalvar.setText("Salvar");
        jBSalvar.setPreferredSize(new java.awt.Dimension(57, 23));
        jBSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalvarActionPerformed(evt);
            }
        });

        jBsAIR.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jBsAIR.setText("Sair");
        jBsAIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsAIRActionPerformed(evt);
            }
        });

        jBExcluir.setText("Excluir");
        jBExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExcluirActionPerformed(evt);
            }
        });

        jBLimparCampos.setText("Limpar Campos");
        jBLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimparCamposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBsAIR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jBSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBLimparCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBLimparCampos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
                .addComponent(jBsAIR))
        );

        jTUf.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jTUf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código UF", "Sigla UF"
            }
        ));
        jTUf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTUfMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTUf);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCod_uf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDesc_uf, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCod_uf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtDesc_uf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        //Verifica se o registra está sendo criado ou se está sendo atualizado
        if (txtCod_uf.getText() == null || txtCod_uf.getText().trim().equals("")) {
            SalvarUf();
        } else {
            AtualizarUf();
        }

    }//GEN-LAST:event_jBSalvarActionPerformed

    private void jTUfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTUfMouseClicked
        if (jTUf.getSelectedRow() != -1) {
            txtCod_uf.setText(jTUf.getValueAt(jTUf.getSelectedRow(), 0).toString());
            txtDesc_uf.setText(jTUf.getValueAt(jTUf.getSelectedRow(), 1).toString());
            desc_uf = jTUf.getValueAt(jTUf.getSelectedRow(), 1).toString();
        }
    }//GEN-LAST:event_jTUfMouseClicked

    private void jBLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimparCamposActionPerformed
        LimparCampos();
    }//GEN-LAST:event_jBLimparCamposActionPerformed

    private void jBExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcluirActionPerformed
        ExcluirUf();
    }//GEN-LAST:event_jBExcluirActionPerformed

    private void jBsAIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsAIRActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBsAIRActionPerformed

    private void txtDesc_ufKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesc_ufKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCod_uf.getText() == null || txtCod_uf.getText().trim().equals("")) {
                SalvarUf();
            } else {
                AtualizarUf();
            }
        }
    }//GEN-LAST:event_txtDesc_ufKeyPressed

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
            java.util.logging.Logger.getLogger(TelaCadastroUf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroUf().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBExcluir;
    private javax.swing.JButton jBLimparCampos;
    private javax.swing.JButton jBSalvar;
    private javax.swing.JButton jBsAIR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTUf;
    private javax.swing.JTextField txtCod_uf;
    private javax.swing.JTextField txtDesc_uf;
    // End of variables declaration//GEN-END:variables
}
