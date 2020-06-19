/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.MovimentacaoUsuarioCtrl;
import controle.UsuarioCtrl;
import dao.CidadeDao;
import dao.ItensMovimentacaoUsuarioDao;
import dao.LoginSessaoDao;
import dao.MovimentacaoUsuarioDao;
import dao.UsuarioDao;
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
import modelo.Usuario;

/**
 *
 * @author Suporte T.I
 */
public class TelaCadastroUsuario extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroUsuario
     */
    String Login,
            senha,
            ipDaMaquina;
    int Cod_movimentacao,
            Cod_usuario,
            Cod_registro;

    //Pegar data e hora no padrão que o Mysql usa
    Date d = new Date();
    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    TelaPesquisaFuncionario tpf = new TelaPesquisaFuncionario(this, true);

    public TelaCadastroUsuario() {
        initComponents();
        CarregarTabela();
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

    public void CarregarTabela() {
        jTUsuario.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTUsuario.getColumnModel().getColumn(1).setPreferredWidth(300);
        DefaultTableModel modelo = (DefaultTableModel) jTUsuario.getModel();
        modelo.setNumRows(0);
        UsuarioDao dao = new UsuarioDao();

        dao.MostrarUsuario().forEach((c) -> {
            modelo.addRow(new Object[]{
                c.getCod_usuario(),
                c.getLogin_usuario()
            });
        });
    }

    public void SalvarUsuario() {
        Usuario u = new Usuario();
        UsuarioCtrl ctrl = new UsuarioCtrl();
        if (TxtCodFunc.getText() == null || TxtCodFunc.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione um Funcionário!!");
            tpf.setVisible(true);
        } else if (TxtLogin.getText() == null || TxtLogin.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira um login!!");
            TxtLogin.requestFocus();
        } else if (TxtSenha.getText() == null || TxtSenha.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira uma senha!!");
            TxtSenha.requestFocus();
        } else if (!TxtSenha.getText().equals(TxtConfirmaSenha.getText())) {
            JOptionPane.showMessageDialog(null, "As senham inseridas são diferentes!!");
            TxtSenha.setText("");
            TxtConfirmaSenha.setText("");
            TxtSenha.requestFocus();
        } else {
            u.setCod_funcionario(Integer.parseInt(TxtCodFunc.getText()));
            u.setLogin_usuario(TxtLogin.getText());
            u.setSenha_usuario(TxtSenha.getText());
            int i = JOptionPane.showConfirmDialog(null, "Confirma a inserção do usuario :" + TxtLogin.getText());
            if (i == 0) {
                ctrl.SalvarUsuarioCtrl(u);
                AbrirMovimentacaoUsuario();
                UsuarioDao dao = new UsuarioDao();

                dao.MostrarUsuario().forEach((c) -> {
                    Cod_registro = c.getCod_usuario();
                });

                SalvarMovimentacaoUsuario_Salvar();
                CarregarTabela();
                LimpaCampos();
            }
        }

    }

    public void LimpaCampos() {
        TxtLogin.setText("");
        TxtCodFunc.setText("");
        txtNome_Func.setText("");
        TxtSenha.setText("");
        TxtConfirmaSenha.setText("");
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
        m.setTabela_alterada("usuario");
        m.setCod_registro_alterado(Integer.parseInt(TxtCodUsuario.getText()));
        m.setData_Hora_movimentacao(form.format(d));
        m.setCod_usuario(Cod_usuario);
        m.setCod_movimentacao(Cod_movimentacao);
        ctrl.SalvarMovimentacaoCtrl(m);

    }

    public void SalvarMovimentacaoUsuario_Exclusao() {
        MovimentacaoUsuario m = new MovimentacaoUsuario();
        MovimentacaoUsuarioCtrl ctrl = new MovimentacaoUsuarioCtrl();

        m.setTipo_movimentacao("Excluir");
        m.setTabela_alterada("usuario");
        m.setCod_registro_alterado(Integer.parseInt(TxtCodUsuario.getText()));
        m.setData_Hora_movimentacao(form.format(d));
        m.setCod_usuario(Cod_usuario);
        m.setCod_movimentacao(Cod_movimentacao);
        ctrl.SalvarMovimentacaoCtrl(m);

    }
    public void SalvarMovimentacaoUsuario_Salvar() {
        if (Cod_usuario == 0) {
            JOptionPane.showMessageDialog(null, "Não deu tempo!!");
        } else {
            MovimentacaoUsuario m = new MovimentacaoUsuario();
            MovimentacaoUsuarioCtrl ctrl = new MovimentacaoUsuarioCtrl();

            m.setTipo_movimentacao("Salvar");
            m.setTabela_alterada("usuario");
            m.setCod_registro_alterado(Cod_registro);
            m.setData_Hora_movimentacao(form.format(d));
            m.setCod_usuario(Cod_usuario);
            m.setCod_movimentacao(Cod_movimentacao);
            ctrl.SalvarMovimentacaoCtrl(m);
        }

    }

    /*public void CompararDadosAtualizados() {
        ItensMovimentacaoUsuario m = new ItensMovimentacaoUsuario();
        ItensMovimentacaoUsuarioDao dao = new ItensMovimentacaoUsuarioDao();
        MovimentacaoUsuarioDao dao2 = new MovimentacaoUsuarioDao();

        if (!Nome_cidade.equals(txtNome_Cidade.getText())) {
            
            dao2.BuscarUltimaMovimentacao().forEach((t) -> {
                m.setCod_movimentacao(t.getCod_movimentacao());
            });
            
            m.setValor_antigo(Nome_cidade);
            m.setValor_novo(txtNome_Cidade.getText());
            m.setNome_coluna("Nome_cidade");

            dao.SalavarItensMovimentacao(m);

        }
        if (!cod_uf.equals(txtCod_uf.getText())) {

            dao2.BuscarUltimaMovimentacao().forEach((t) -> {
                m.setCod_movimentacao(t.getCod_movimentacao());
            });

            m.setValor_antigo(cod_uf);
            m.setValor_novo(txtCod_uf.getText());
            m.setNome_coluna("Cod_uf");

            dao.SalavarItensMovimentacao(m);

        }

    }*/
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
        TxtCodUsuario = new javax.swing.JTextField();
        Funcionário = new javax.swing.JButton();
        TxtCodFunc = new javax.swing.JTextField();
        txtNome_Func = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        Sair = new javax.swing.JLabel();
        Atualizar = new javax.swing.JLabel();
        Salvar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TxtLogin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtSenha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TxtConfirmaSenha = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTUsuario = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        TxtPesquisar = new javax.swing.JTextField();
        jBPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuários", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel1.setText("Código Usuário");

        TxtCodUsuario.setEditable(false);

        Funcionário.setText("Funcionário");
        Funcionário.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuncionárioActionPerformed(evt);
            }
        });

        TxtCodFunc.setEditable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N

        Sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair.png"))); // NOI18N
        Sair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SairMouseClicked(evt);
            }
        });

        Atualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Atualizar.png"))); // NOI18N
        Atualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar.png"))); // NOI18N
        Salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Salvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalvarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Sair, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Atualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Salvar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Sair, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel2.setText("Login");

        jLabel3.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel3.setText("Senha");

        jLabel4.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel4.setText("Confirma Senha");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuários Cadastrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N

        jTUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Usuário", "Login"
            }
        ));
        jScrollPane1.setViewportView(jTUsuario);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jRadioButton1.setText("Código");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jRadioButton2.setText("Nome");

        jBPesquisar.setText("Pesquisar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtPesquisar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBPesquisar))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(TxtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBPesquisar)))
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
                        .addComponent(TxtCodUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Funcionário)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtCodFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome_Func, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtSenha)
                            .addComponent(TxtLogin)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtConfirmaSenha))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtCodUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Funcionário)
                    .addComponent(TxtCodFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNome_Func, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void SairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SairMouseClicked
        this.dispose();
    }//GEN-LAST:event_SairMouseClicked

    private void FuncionárioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FuncionárioActionPerformed
        tpf.setVisible(true);
        TxtCodFunc.setText(tpf.Cod_Func);
        txtNome_Func.setText(tpf.Nome_Func);
        tpf.dispose();

    }//GEN-LAST:event_FuncionárioActionPerformed

    private void SalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalvarMouseClicked
        SalvarUsuario();        
    }//GEN-LAST:event_SalvarMouseClicked

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
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Atualizar;
    private javax.swing.JButton Funcionário;
    private javax.swing.JLabel Sair;
    private javax.swing.JLabel Salvar;
    private javax.swing.JTextField TxtCodFunc;
    private javax.swing.JTextField TxtCodUsuario;
    private javax.swing.JTextField TxtConfirmaSenha;
    private javax.swing.JTextField TxtLogin;
    private javax.swing.JTextField TxtPesquisar;
    private javax.swing.JTextField TxtSenha;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTUsuario;
    private javax.swing.JTextField txtNome_Func;
    // End of variables declaration//GEN-END:variables
}
