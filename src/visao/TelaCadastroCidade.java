/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.CidadeCtrl;
import controle.MovimentacaoUsuarioCtrl;
import dao.CidadeDao;
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
import modelo.Cidade;
import modelo.ItensMovimentacaoUsuario;
import modelo.MovimentacaoUsuario;
import modelo.Uf;

/**
 *
 * @author Suporte T.I
 */
public class TelaCadastroCidade extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroBairro
     */
    //VARIÁVEIS QUE IREMOS UTILIZAR NESSA TELA
    //Utilizada para fazermos a comparação de registros
    String Nome_cidade,
            cod_uf,
            ipDaMaquina;
    int Cod_movimentacao,
            Cod_cidade,
            Cod_usuario;


    //Pegar data e hora no padrão que o Mysql usa
    Date d = new Date();
    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public TelaCadastroCidade() {
        initComponents();
        PreebcherComboUf();
        CarregaTabela();
        jRNOme.setSelected(true);
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
        jTCidade.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTCidade.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTCidade.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTCidade.getColumnModel().getColumn(3).setPreferredWidth(60);
        DefaultTableModel modelo = (DefaultTableModel) jTCidade.getModel();
        modelo.setNumRows(0);
        CidadeDao cdao = new CidadeDao();

        cdao.MostrarCidade().forEach((c) -> {
            modelo.addRow(new Object[]{
                c.getCod_cidade(),
                c.getNome_cidade(),
                c.getCod_uf(),
                c.getDesc_uf()
            });
        });
    }

    public void MostrarBuscaCodigo(int cod_cidade) {
        jTCidade.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTCidade.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTCidade.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTCidade.getColumnModel().getColumn(3).setPreferredWidth(60);
        DefaultTableModel modelo = (DefaultTableModel) jTCidade.getModel();
        modelo.setNumRows(0);
        CidadeDao cdao = new CidadeDao();

        cdao.BuscaCidadeCodigo(cod_cidade).forEach((c) -> {
            modelo.addRow(new Object[]{
                c.getCod_cidade(),
                c.getNome_cidade(),
                c.getCod_uf(),
                c.getDesc_uf()
            });
        });
    }

    public void MostrarBuscaNome(String nome_cidade) {
        jTCidade.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTCidade.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTCidade.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTCidade.getColumnModel().getColumn(3).setPreferredWidth(60);
        DefaultTableModel modelo = (DefaultTableModel) jTCidade.getModel();
        modelo.setNumRows(0);
        CidadeDao cdao = new CidadeDao();

        cdao.BuscaCidadeNome(nome_cidade).forEach((c) -> {
            modelo.addRow(new Object[]{
                c.getCod_cidade(),
                c.getNome_cidade(),
                c.getCod_uf(),
                c.getDesc_uf()
            });
        });
    }

    public void PreebcherComboUf() {
        UfDao udao = new UfDao();
        udao.Mostrar().forEach((uf) -> {
            jcUf.addItem(uf);
        });

    }

    public void LimparCampos() {
        txtCodCidade.setText("");
        txtCod_uf.setText("");
        txtNome_Cidade.setText("");
        txtUf.setText("");
    }

    public void SalvarCidade() {
        Cidade c = new Cidade();
        CidadeCtrl ctrl = new CidadeCtrl();

        if (txtNome_Cidade.getText() == null || txtNome_Cidade.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Nome Cidade é Obrigatório!");
            txtNome_Cidade.requestFocus();
        } else if (txtCod_uf.getText() == null || txtCod_uf.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Uf é Obrigatório!");
            jcUf.requestFocus();
        } else {
            c.setNome_cidade(txtNome_Cidade.getText());
            c.setCod_uf(Integer.parseInt(txtCod_uf.getText()));

            int i = JOptionPane.showConfirmDialog(this, "Deseja realmente Salvar??");
            if (i == 0) {
                ctrl.SalvarCtrlCidade(c);
                AbrirMovimentacaoUsuario();

                //Busca o código que acabou de ser gerado para inserir na tabela de movimentação_usuario
                CidadeDao cdao = new CidadeDao();
                cdao.MostrarUltimaCidade().forEach((f) -> {
                    Cod_cidade = f.getCod_cidade();
                });
                SalvarMovimentacaoUsuario_Salvar();
                LimparCampos();
                CarregaTabela();
            }
        }
    }

    public void ExcluirCidade() {
        CidadeCtrl ctrl = new CidadeCtrl();
        if (txtCodCidade.getText() == null || txtCodCidade.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            int i = JOptionPane.showConfirmDialog(this, "Deseja realmente Ecluir??");
            if (i == 0) {
                ctrl.ExcluirCtrlCidade(Integer.parseInt(txtCodCidade.getText()));
                AbrirMovimentacaoUsuario();
                SalvarMovimentacaoUsuario_Exclusao();
                CarregaTabela();
                LimparCampos();
            }
        }
    }

    public void AtualizaCidade() {
        Cidade c = new Cidade();
        CidadeCtrl ctrl = new CidadeCtrl();
        if (txtNome_Cidade.getText() == null || txtNome_Cidade.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Nome Cidade é Obrigatório!");
            txtNome_Cidade.requestFocus();
        } else if (txtCod_uf.getText() == null || txtCod_uf.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Uf é Obrigatório!");
            jcUf.requestFocus();
        } else {
            c.setNome_cidade(txtNome_Cidade.getText());
            c.setCod_uf(Integer.parseInt(txtCod_uf.getText()));
            c.setCod_cidade(Integer.parseInt(txtCodCidade.getText()));
            int i = JOptionPane.showConfirmDialog(this, "Deseja realmente Atualizar??");
            if (i == 0) {
                ctrl.AtualizarCtrlCidade(c);
                AbrirMovimentacaoUsuario();
                SalvarMovimentacaoUsuario_Atualizacao();
                CompararDadosAtualizados();
                LimparCampos();
                CarregaTabela();
            }
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
        m.setTabela_alterada("cidade");
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
        m.setTabela_alterada("cidade");
        m.setCod_registro_alterado(Integer.parseInt(txtCod_uf.getText()));
        m.setData_Hora_movimentacao(form.format(d));
        m.setCod_usuario(Cod_usuario);
        m.setCod_movimentacao(Cod_movimentacao);
        ctrl.SalvarMovimentacaoCtrl(m);

    }

    public void SalvarMovimentacaoUsuario_Salvar() {
        if (Cod_cidade == 0) {
            JOptionPane.showMessageDialog(null, "Não deu tempo!!");
        } else {
            MovimentacaoUsuario m = new MovimentacaoUsuario();
            MovimentacaoUsuarioCtrl ctrl = new MovimentacaoUsuarioCtrl();

            m.setTipo_movimentacao("Salvar");
            m.setTabela_alterada("cidade");
            m.setCod_registro_alterado(Cod_cidade);
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

    }

    public void Consultar() {
        if (jRCodigo.isSelected()) {
            if (TxtConsulta.getText() == null || TxtConsulta.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Campo código, é obrigatório para a consulta !!");
                TxtConsulta.requestFocus();
            } else {
                MostrarBuscaCodigo(Integer.parseInt(TxtConsulta.getText()));
            }
        } else if (jRNOme.isSelected()) {
            MostrarBuscaNome(TxtConsulta.getText());
        }
    }

    public void SelecionarItemTabela() {
        if (jTCidade.getSelectedRow() != -1) {
            txtCodCidade.setText(jTCidade.getValueAt(jTCidade.getSelectedRow(), 0).toString());
            txtNome_Cidade.setText(jTCidade.getValueAt(jTCidade.getSelectedRow(), 1).toString());
            txtCod_uf.setText(jTCidade.getValueAt(jTCidade.getSelectedRow(), 2).toString());
            txtUf.setText(jTCidade.getValueAt(jTCidade.getSelectedRow(), 3).toString());
            Nome_cidade = jTCidade.getValueAt(jTCidade.getSelectedRow(), 1).toString();
            cod_uf = jTCidade.getValueAt(jTCidade.getSelectedRow(), 2).toString();
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
        Cod_cidade2 = new javax.swing.JLabel();
        txtCodCidade = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNome_Cidade = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jBSalvar = new javax.swing.JButton();
        jBAtualizar = new javax.swing.JButton();
        jBExcluir = new javax.swing.JButton();
        jBLimparCampos = new javax.swing.JButton();
        jBSair = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtCod_uf = new javax.swing.JTextField();
        jcUf = new javax.swing.JComboBox<>();
        txtUf = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTCidade = new javax.swing.JTable();
        jRCodigo = new javax.swing.JRadioButton();
        jRNOme = new javax.swing.JRadioButton();
        TxtConsulta = new javax.swing.JTextField();
        jBConsultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cidades", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N

        Cod_cidade2.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        Cod_cidade2.setText("Código");

        txtCodCidade.setEditable(false);
        txtCodCidade.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        txtCodCidade.setPreferredSize(new java.awt.Dimension(21, 21));

        jLabel1.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel1.setText("Nome Cidade");

        txtNome_Cidade.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        txtNome_Cidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNome_CidadeKeyPressed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(150, 414));

        jBSalvar.setText("Salvar");
        jBSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalvarActionPerformed(evt);
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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBLimparCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jBSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBLimparCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(255, 255, 255)
                .addComponent(jBSair))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel2.setText("UF");

        txtCod_uf.setEditable(false);
        txtCod_uf.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        jcUf.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jcUf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcUfMouseClicked(evt);
            }
        });
        jcUf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcUfActionPerformed(evt);
            }
        });
        jcUf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcUfKeyPressed(evt);
            }
        });

        txtUf.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        txtUf.setForeground(new java.awt.Color(0, 51, 204));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTCidade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome Cidade", "Código Uf", "Uf"
            }
        ));
        jTCidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTCidadeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTCidade);

        buttonGroup1.add(jRCodigo);
        jRCodigo.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jRCodigo.setText("Código");

        buttonGroup1.add(jRNOme);
        jRNOme.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jRNOme.setText("Nome");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRNOme)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtConsulta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBConsultar))
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Cod_cidade2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCod_uf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(txtUf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jcUf, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 300, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNome_Cidade))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cod_cidade2)
                            .addComponent(txtCodCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(txtNome_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtCod_uf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jcUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtUf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
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

    private void jBSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSairActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jBSairActionPerformed

    private void jBLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimparCamposActionPerformed
        LimparCampos();        // TODO add your handling code here:
    }//GEN-LAST:event_jBLimparCamposActionPerformed

    private void jcUfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcUfActionPerformed
        Uf u = (Uf) jcUf.getSelectedItem();
        txtCod_uf.setText(Integer.toString(u.getCod_uf()).trim());
        txtUf.setText(u.getDesc_uf());
    }//GEN-LAST:event_jcUfActionPerformed

    private void jcUfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcUfMouseClicked
        Uf u = (Uf) jcUf.getSelectedItem();
        txtCod_uf.setText(Integer.toString(u.getCod_uf()).trim());
        txtUf.setText(u.getDesc_uf());
    }//GEN-LAST:event_jcUfMouseClicked

    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed
        SalvarCidade();        // TODO add your handling code here:
    }//GEN-LAST:event_jBSalvarActionPerformed

    private void jBExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcluirActionPerformed
        ExcluirCidade();
    }//GEN-LAST:event_jBExcluirActionPerformed

    private void jBAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizarActionPerformed
        AtualizaCidade();
    }//GEN-LAST:event_jBAtualizarActionPerformed

    private void txtNome_CidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNome_CidadeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jcUf.requestFocus();
        }
    }//GEN-LAST:event_txtNome_CidadeKeyPressed

    private void jcUfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcUfKeyPressed
        Consultar();
    }//GEN-LAST:event_jcUfKeyPressed

    private void TxtConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtConsultaActionPerformed
        Consultar();
    }//GEN-LAST:event_TxtConsultaActionPerformed

    private void jBConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConsultarActionPerformed
        Consultar();
    }//GEN-LAST:event_jBConsultarActionPerformed

    private void jTCidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTCidadeMouseClicked
        SelecionarItemTabela();
    }//GEN-LAST:event_jTCidadeMouseClicked

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
            java.util.logging.Logger.getLogger(TelaCadastroCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroCidade().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cod_cidade2;
    private javax.swing.JTextField TxtConsulta;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBAtualizar;
    private javax.swing.JButton jBConsultar;
    private javax.swing.JButton jBExcluir;
    private javax.swing.JButton jBLimparCampos;
    private javax.swing.JButton jBSair;
    private javax.swing.JButton jBSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRCodigo;
    private javax.swing.JRadioButton jRNOme;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTCidade;
    private javax.swing.JComboBox<Object> jcUf;
    private javax.swing.JTextField txtCodCidade;
    private javax.swing.JTextField txtCod_uf;
    private javax.swing.JTextField txtNome_Cidade;
    private javax.swing.JLabel txtUf;
    // End of variables declaration//GEN-END:variables
}
