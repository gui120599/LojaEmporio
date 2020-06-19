/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.FuncionarioCtrl;
import controle.MovimentacaoUsuarioCtrl;
import dao.CargoDao;
import dao.FuncionarioDao;
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
import modelo.Cargo;
import modelo.Funcionario;
import modelo.MovimentacaoUsuario;
import modelo.Uf;

/**
 *
 * @author Suporte T.I
 */
public class TelaCadastroFunc extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroFunc
     */
    String DataNascimentoConvertidaISO, 
           DataCadastroConvertidaISO, 
           DataNascimentoConvertidaBR, 
           DataNascimento,
            ipDaMaquina;
    
    int Cod_movimentacao, 
        Cod_funcionario,
            Cod_usuario;

    //Converção de data para o padrão do mysql
    Date d = new Date();
    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public TelaCadastroFunc() {
        initComponents();
        PegarIp();
        PreencherComboCargo();
        PreebcherComboUf();
        CarregarTabela();
        Feminio.setSelected(true);
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
        jTFuncionario.getColumnModel().getColumn(0).setPreferredWidth(5);
        jTFuncionario.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTFuncionario.getColumnModel().getColumn(2).setPreferredWidth(5);
        jTFuncionario.getColumnModel().getColumn(3).setPreferredWidth(200);
        DefaultTableModel modelo = (DefaultTableModel) jTFuncionario.getModel();
        modelo.setNumRows(0);
        FuncionarioDao fdao = new FuncionarioDao();

        fdao.MostrarFuncionario().forEach((f) -> {
            modelo.addRow(new Object[]{
                f.getCod_funcionario(),
                f.getNome_funcionario(),
                f.getCod_cargo(),
                f.getDesc_cargo()
            });
        });
    }

    public void PreencherComboCargo() {
        CargoDao cdao = new CargoDao();

        cdao.MostrarCargo().forEach((cargo) -> {
            JCCargo.addItem(cargo);
        });
    }

    public void PreebcherComboUf() {
        UfDao udao = new UfDao();
        udao.Mostrar().forEach((uf) -> {
            JCUf.addItem(uf);
        });

    }

    public void TelaPesquisaCidade() {
        TelaPesquisaCidade tpc = new TelaPesquisaCidade(this, true);
        tpc.setVisible(true);
        txtNomeCidade.setText(tpc.Cidade);
        TxtCod_Cidade.setText(tpc.Cod);
    }

    public void TelaPesquisaBairro() {
        TelaPesquisaBairro tpb = new TelaPesquisaBairro(this, true);
        tpb.setVisible(true);
        TxtNomeBairro.setText(tpb.Bairro);
        TxtCodBairro.setText(tpb.Cod);
    }

    public void ConverteDataNascimentoISO() {
        //Pega nesse formato 00/00/0000 e converte para esse  formato 0000-00-00
        String dia = TxtDataNascimento.getText().substring(0, 2);
        String mes = TxtDataNascimento.getText().substring(3, 5);
        String ano = TxtDataNascimento.getText().substring(6, 10);

        DataNascimentoConvertidaISO = ano + "-" + mes + "-" + dia;
    }

    public void ConverteDataNascimentoBR() {
        //Pega nesse formato 0000-00-00 e converte para esse  formato 00/00/0000
        String dia = DataNascimento.substring(8, 10);
        String mes = DataNascimento.substring(5, 7);
        String ano = DataNascimento.substring(0, 4);

        DataNascimentoConvertidaBR = dia + mes + ano;
        //Não precisa do / pq no campo formatado já possui

    }


    public void SalvarFunc() {
        Funcionario f = new Funcionario();
        FuncionarioCtrl ctrl = new FuncionarioCtrl();
        FuncionarioDao dao = new FuncionarioDao();
        if (TxtNomeFunc.getText() == null || TxtNomeFunc.getText().trim().equals("")) {//Verifica se tem informação no campo
            JOptionPane.showMessageDialog(null, "Campo Nome Cliente é Obrigatório!");//Retorna a Mensagem caso não tenha
            TxtNomeFunc.requestFocus();//Coloca o curso no campo sem informação
        } else if (TxtDataNascimento.getText() == null || TxtDataNascimento.getText().trim().equals("  /  /    ")) {
            JOptionPane.showMessageDialog(null, "Campo Data Nascimento é Obrigatório!");
            TxtDataNascimento.requestFocus();
        } else if (TxtCPF.getText() == null || TxtCPF.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo CPF é Obrigatório!");
            TxtCPF.requestFocus();
        } else if (txtCodCargo.getText() == null || txtCodCargo.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Cargo é Obrigatório!");
            JCCargo.requestFocus();
        } else if (TxtCelular.getText() == null || TxtCelular.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Celular e WhatsApp é Obrigatório!");
            TxtCelular.requestFocus();
        } else if (TxtCep.getText() == null || TxtCep.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo CEP é Obrigatório!");
            TxtCep.requestFocus();
        } else if (TxtCod_Uf.getText() == null || TxtCod_Uf.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo UF é obrigatório!");
            JCUf.requestFocus();
        } else if (TxtCod_Cidade.getText() == null || TxtCod_Cidade.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Cidade é Obrigatório!");
            TxtCod_Cidade.requestFocus();
        } else if (TxtCodBairro.getText() == null || TxtCodBairro.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Bairro é Obrigatório!");
            jBCidade.requestFocus();
        } else {
            f.setNome_funcionario(TxtNomeFunc.getText());
            f.setCpf_func(TxtCPF.getText());
            if (TxtRg.getText() == null || TxtRg.getText().trim().equals("")) {
                f.setRg_func("S/N");
            } else {
                f.setRg_func(TxtRg.getText());
            }
            f.setData_nascimento_func(DataNascimentoConvertidaISO);
            if (Feminio.isSelected()) {
                f.setSexo("F");
            } else {
                f.setSexo("M");
            }
            f.setEstado_civil_func(jCEstadoCivil.getSelectedItem().toString());
            f.setCod_cargo(Integer.parseInt(txtCodCargo.getText()));
            if (TxtTelefone.getText() == null || TxtTelefone.getText().trim().equals("")) {
                f.setTelefone("S/N");
            } else {
                f.setTelefone(TxtTelefone.getText());
            }
            f.setWhatsApp(TxtCelular.getText());
            if (TxtEmail.getText() == null || TxtEmail.getText().trim().equals("")) {
                f.setEmail("Não informado");
            } else {
                f.setEmail(TxtEmail.getText());
            }
            f.setCep(TxtCep.getText());
            f.setCod_uf(Integer.parseInt(TxtCod_Uf.getText()));
            f.setCod_cidade(Integer.parseInt(TxtCod_Cidade.getText()));
            f.setCod_bairro(Integer.parseInt(TxtCodBairro.getText()));
            f.setEndereco(TxtEndereco.getText());
            if (jCAtivoInativo.isSelected()) {
                f.setStatus_func(false);
            } else {
                f.setStatus_func(true);
            }
            ConverteDataNascimentoISO();
            ctrl.SalvarFuncionarioCtrl(f);

            //Retorna o código que acabou de gerar para salvar na tabela de movimentação
            dao.MostrarUltimoCodFuncionario().forEach((t) -> {
                Cod_funcionario = t.getCod_funcionario();
            });

            SalvarMovimentacaoUsuario_Salvar();
            LimpaCampos();
            CarregarTabela();
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
        m.setTabela_alterada("funcionario");
        m.setCod_registro_alterado(Cod_funcionario);
        m.setData_Hora_movimentacao(form.format(d));
        m.setCod_usuario(Cod_usuario);
        m.setCod_movimentacao(Cod_movimentacao);
        ctrl.SalvarMovimentacaoCtrl(m);

    }

    public void SalvarMovimentacaoUsuario_Salvar() {
        if (Cod_funcionario == 0) {
            JOptionPane.showMessageDialog(null, "Não deu tempo!!");
        } else {
            MovimentacaoUsuario m = new MovimentacaoUsuario();
            MovimentacaoUsuarioCtrl ctrl = new MovimentacaoUsuarioCtrl();

            m.setTipo_movimentacao("Salvar");
            m.setTabela_alterada("funcionario");
            m.setCod_registro_alterado(Cod_funcionario);
            m.setData_Hora_movimentacao(form.format(d));
            m.setCod_usuario(Cod_usuario);
            m.setCod_movimentacao(Cod_movimentacao);
            ctrl.SalvarMovimentacaoCtrl(m);
        }

    }

    public void LimpaCampos() {
        TxtNomeFunc.setText("");
        TxtDataNascimento.setText("");
        TxtCPF.setText("");
        TxtRg.setText("");
        txtCodCargo.setText("");
        TxtTelefone.setText("");
        TxtCelular.setText("");
        TxtEmail.setText("");
        TxtCep.setText("");
        TxtCod_Uf.setText("");
        JCUf.setSelectedItem(false);
        TxtCod_Cidade.setText("");
        txtNomeCidade.setText("");
        TxtCodBairro.setText("");
        TxtNomeBairro.setText("");
        TxtNumero.setText("");
        TxtComplemento.setText("");
        jCAtivoInativo.setSelected(false);
        TxtEndereco.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BGSexo = new javax.swing.ButtonGroup();
        PainelFormulario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TxtCodFunc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtNomeFunc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtDataNascimento = new javax.swing.JFormattedTextField();
        LCPF = new javax.swing.JLabel();
        TxtCPF = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtRg = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtTelefone = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        TxtCelular = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        TxtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TxtCep = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        TxtCod_Uf = new javax.swing.JTextField();
        JCUf = new javax.swing.JComboBox<>();
        TxtCod_Cidade = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        TxtComplemento = new javax.swing.JTextField();
        TxtCodBairro = new javax.swing.JTextField();
        jCAtivoInativo = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        TxtNumero = new javax.swing.JTextField();
        jPConsulta = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        TxtPesquisar = new javax.swing.JTextField();
        jBPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTFuncionario = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        TxtEndereco = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCodCargo = new javax.swing.JTextField();
        JCCargo = new javax.swing.JComboBox<>();
        txtNomeCidade = new javax.swing.JTextField();
        TxtNomeBairro = new javax.swing.JTextField();
        jBBairro = new javax.swing.JButton();
        jBCidade = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        Feminio = new javax.swing.JRadioButton();
        Masculino = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jCEstadoCivil = new javax.swing.JComboBox<>();
        PainelOpcoes = new javax.swing.JPanel();
        jBNovo = new javax.swing.JButton();
        jBSalvar = new javax.swing.JButton();
        jBExcluir = new javax.swing.JButton();
        jBAtualizar = new javax.swing.JButton();
        jBLimpaCampos = new javax.swing.JButton();
        jBFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Funcionários");
        setResizable(false);

        PainelFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro Funcionários", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel1.setText("Código");

        TxtCodFunc.setEditable(false);
        TxtCodFunc.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        TxtCodFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCodFuncActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel2.setText("Nome Funcionário");

        TxtNomeFunc.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        TxtNomeFunc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNomeFuncKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel3.setText("Data Nascimento");

        try {
            TxtDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtDataNascimento.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        TxtDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDataNascimentoActionPerformed(evt);
            }
        });
        TxtDataNascimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtDataNascimentoKeyPressed(evt);
            }
        });

        LCPF.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        LCPF.setText("CPF");

        try {
            TxtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtCPF.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        TxtCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCPFActionPerformed(evt);
            }
        });
        TxtCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCPFKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel5.setText("RG");

        try {
            TxtRg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtRg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtRgKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel6.setText("Telefone");

        try {
            TxtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtTelefoneKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel7.setText("Celular e WhatsApp");

        try {
            TxtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtCelular.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        TxtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCelularKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel8.setText("Email");

        TxtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtEmailKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel9.setText("CEP");

        try {
            TxtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtCep.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TxtCep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCepKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel10.setText("UF");

        TxtCod_Uf.setEditable(false);

        JCUf.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        JCUf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JCUfMouseClicked(evt);
            }
        });
        JCUf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCUfActionPerformed(evt);
            }
        });
        JCUf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JCUfKeyPressed(evt);
            }
        });

        TxtCod_Cidade.setEditable(false);
        TxtCod_Cidade.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel12.setText("Complemento");

        TxtComplemento.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        TxtComplemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtComplementoActionPerformed(evt);
            }
        });
        TxtComplemento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtComplementoKeyPressed(evt);
            }
        });

        TxtCodBairro.setEditable(false);
        TxtCodBairro.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        jCAtivoInativo.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jCAtivoInativo.setText("Inativo");

        jLabel17.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel17.setText("Número");

        TxtNumero.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        TxtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNumeroKeyPressed(evt);
            }
        });

        jPConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Funcionários Cadastrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N

        jRadioButton1.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jRadioButton1.setText("Código ");

        jRadioButton2.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jRadioButton2.setText("Nome ");

        jBPesquisar.setText("Pesquisar");

        jTFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Email", "Celular e WhatsApp"
            }
        ));
        jTFuncionario.setName(""); // NOI18N
        jTFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTFuncionarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTFuncionario);

        javax.swing.GroupLayout jPConsultaLayout = new javax.swing.GroupLayout(jPConsulta);
        jPConsulta.setLayout(jPConsultaLayout);
        jPConsultaLayout.setHorizontalGroup(
            jPConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtPesquisar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBPesquisar)
                .addContainerGap())
        );
        jPConsultaLayout.setVerticalGroup(
            jPConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPConsultaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(TxtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBPesquisar)))
        );

        jLabel18.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel18.setText("Endereço");

        TxtEndereco.setEditable(false);
        TxtEndereco.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel4.setText("Cargo");

        txtCodCargo.setEditable(false);

        JCCargo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JCCargoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JCCargoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                JCCargoMouseReleased(evt);
            }
        });
        JCCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCCargoActionPerformed(evt);
            }
        });
        JCCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JCCargoKeyPressed(evt);
            }
        });

        txtNomeCidade.setEditable(false);
        txtNomeCidade.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        txtNomeCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeCidadeActionPerformed(evt);
            }
        });

        TxtNomeBairro.setEditable(false);
        TxtNomeBairro.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        jBBairro.setText("Bairro");
        jBBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBairroActionPerformed(evt);
            }
        });

        jBCidade.setText("Cidade");
        jBCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCidadeActionPerformed(evt);
            }
        });
        jBCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBCidadeKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel11.setText("Sexo");

        BGSexo.add(Feminio);
        Feminio.setText("Feminino");
        Feminio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FeminioActionPerformed(evt);
            }
        });
        Feminio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FeminioKeyPressed(evt);
            }
        });

        BGSexo.add(Masculino);
        Masculino.setText("Masculino");
        Masculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasculinoActionPerformed(evt);
            }
        });
        Masculino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MasculinoKeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel13.setText("Estado Civil");

        jCEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Solteiro(a)", "Casado(a)", "Separado(a)", "Divorciado(a)", "Viúvo(a)" }));
        jCEstadoCivil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCEstadoCivilKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout PainelFormularioLayout = new javax.swing.GroupLayout(PainelFormulario);
        PainelFormulario.setLayout(PainelFormularioLayout);
        PainelFormularioLayout.setHorizontalGroup(
            PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelFormularioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(TxtCodFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(TxtNomeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(LCPF)
                .addGap(4, 4, 4)
                .addComponent(TxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(4, 4, 4)
                .addComponent(TxtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PainelFormularioLayout.createSequentialGroup()
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(4, 4, 4)
                        .addComponent(TxtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(6, 6, 6)
                        .addComponent(Feminio)
                        .addGap(0, 0, 0)
                        .addComponent(Masculino)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(6, 6, 6)
                        .addComponent(jCEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(txtCodCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(JCCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(4, 4, 4)
                        .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jCAtivoInativo))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(4, 4, 4)
                        .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7)
                        .addGap(4, 4, 4)
                        .addComponent(TxtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8)
                        .addGap(6, 6, 6)
                        .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(4, 4, 4)
                        .addComponent(TxtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(4, 4, 4)
                        .addComponent(TxtCod_Uf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(JCUf, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jBCidade)
                        .addGap(6, 6, 6)
                        .addComponent(TxtCod_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtNomeCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jBBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(TxtCodBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(TxtNomeBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(4, 4, 4)
                        .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(4, 4, 4)
                        .addComponent(TxtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PainelFormularioLayout.setVerticalGroup(
            PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(TxtCodFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(TxtNomeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(LCPF))
                    .addComponent(TxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel5))
                    .addComponent(TxtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(TxtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel11))
                    .addComponent(Feminio)
                    .addComponent(Masculino)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel13))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jCEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(txtCodCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addComponent(TxtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8))
                    .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9))
                    .addComponent(TxtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(TxtCod_Uf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JCUf, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBCidade)
                    .addComponent(TxtCod_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBBairro)
                    .addComponent(TxtCodBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNomeBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel12))))
                .addGap(18, 18, 18)
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel18))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCAtivoInativo))
                .addGap(18, 18, 18)
                .addComponent(jPConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        PainelOpcoes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N

        jBNovo.setText("Novo ");
        jBNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNovoActionPerformed(evt);
            }
        });

        jBSalvar.setText("Salvar");
        jBSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalvarActionPerformed(evt);
            }
        });

        jBExcluir.setText("Excluir");
        jBExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExcluirActionPerformed(evt);
            }
        });

        jBAtualizar.setText("Atualizar");

        jBLimpaCampos.setText("Limpar Campos");
        jBLimpaCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimpaCamposActionPerformed(evt);
            }
        });

        jBFechar.setText("Sair");
        jBFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelOpcoesLayout = new javax.swing.GroupLayout(PainelOpcoes);
        PainelOpcoes.setLayout(PainelOpcoesLayout);
        PainelOpcoesLayout.setHorizontalGroup(
            PainelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBFechar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBLimpaCampos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PainelOpcoesLayout.setVerticalGroup(
            PainelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelOpcoesLayout.createSequentialGroup()
                .addComponent(jBNovo)
                .addGap(6, 6, 6)
                .addComponent(jBSalvar)
                .addGap(9, 9, 9)
                .addComponent(jBAtualizar)
                .addGap(6, 6, 6)
                .addComponent(jBExcluir)
                .addGap(11, 11, 11)
                .addComponent(jBLimpaCampos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBFechar))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PainelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PainelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PainelFormulario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNovoActionPerformed

    }//GEN-LAST:event_jBNovoActionPerformed

    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed
        SalvarFunc();
        SalvarMovimentacaoUsuario_Salvar();
    }//GEN-LAST:event_jBSalvarActionPerformed

    private void jBLimpaCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimpaCamposActionPerformed
        LimpaCampos();
    }//GEN-LAST:event_jBLimpaCamposActionPerformed

    private void jBFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBFecharActionPerformed

    private void jBExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcluirActionPerformed
        SalvarMovimentacaoUsuario_Atualizacao();
    }//GEN-LAST:event_jBExcluirActionPerformed

    private void jCEstadoCivilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCEstadoCivilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JCCargo.requestFocus();
        }
    }//GEN-LAST:event_jCEstadoCivilKeyPressed

    private void jBCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBCidadeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jBBairro.requestFocus();
        }
    }//GEN-LAST:event_jBCidadeKeyPressed

    private void jBCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCidadeActionPerformed
        TelaPesquisaCidade tpc = new TelaPesquisaCidade(this, true);
        tpc.setVisible(true);
        TxtCod_Cidade.setText(tpc.Cod);
        txtNomeCidade.setText(tpc.Cidade);
        jBBairro.requestFocus();
    }//GEN-LAST:event_jBCidadeActionPerformed

    private void jBBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBairroActionPerformed
        TelaPesquisaBairro tpb = new TelaPesquisaBairro(this, true);
        tpb.setVisible(true);
        TxtCodBairro.setText(tpb.Cod);
        TxtNomeBairro.setText(tpb.Bairro);
    }//GEN-LAST:event_jBBairroActionPerformed

    private void txtNomeCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeCidadeActionPerformed

    }//GEN-LAST:event_txtNomeCidadeActionPerformed

    private void JCCargoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JCCargoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtTelefone.requestFocus();
        }
    }//GEN-LAST:event_JCCargoKeyPressed

    private void JCCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCCargoActionPerformed
        Cargo c = (Cargo) JCCargo.getSelectedItem();
        txtCodCargo.setText(Integer.toString(c.getCod_cargo()).trim());
    }//GEN-LAST:event_JCCargoActionPerformed

    private void JCCargoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JCCargoMouseReleased
        Cargo c = (Cargo) JCCargo.getSelectedItem();
        txtCodCargo.setText(Integer.toString(c.getCod_cargo()).trim());
    }//GEN-LAST:event_JCCargoMouseReleased

    private void JCCargoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JCCargoMousePressed
        Cargo c = (Cargo) JCCargo.getSelectedItem();
        txtCodCargo.setText(Integer.toString(c.getCod_cargo()).trim());
    }//GEN-LAST:event_JCCargoMousePressed

    private void JCCargoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JCCargoMouseClicked
        Cargo c = (Cargo) JCCargo.getSelectedItem();
        txtCodCargo.setText(Integer.toString(c.getCod_cargo()).trim());
    }//GEN-LAST:event_JCCargoMouseClicked

    private void TxtNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNumeroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtComplemento.requestFocus();
        }
    }//GEN-LAST:event_TxtNumeroKeyPressed

    private void TxtComplementoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtComplementoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //SalvarFunc();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_TxtComplementoKeyPressed

    private void TxtComplementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtComplementoActionPerformed
        TxtEndereco.setText(String.valueOf(TxtComplemento.getText()) + " Nº "
                + String.valueOf(TxtNumero.getText()) + " "
                + String.valueOf(TxtNomeBairro.getText()) + " "
                + String.valueOf(txtNomeCidade.getText()) + " "
                + String.valueOf(JCUf.getSelectedItem()));
    }//GEN-LAST:event_TxtComplementoActionPerformed

    private void JCUfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JCUfKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TelaPesquisaCidade();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_JCUfKeyPressed

    private void JCUfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCUfActionPerformed
        Uf u = (Uf) JCUf.getSelectedItem();
        TxtCod_Uf.setText(Integer.toString(u.getCod_uf()).trim());
    }//GEN-LAST:event_JCUfActionPerformed

    private void JCUfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JCUfMouseClicked
        Uf u = (Uf) JCUf.getSelectedItem();
        TxtCod_Uf.setText(Integer.toString(u.getCod_uf()).trim());
    }//GEN-LAST:event_JCUfMouseClicked

    private void TxtCepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCepKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JCUf.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCepKeyPressed

    private void TxtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtEmailKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtCep.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_TxtEmailKeyPressed

    private void TxtCelularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCelularKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtEmail.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCelularKeyPressed

    private void TxtTelefoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTelefoneKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtCelular.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTelefoneKeyPressed

    private void TxtRgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtRgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtDataNascimento.requestFocus();
        }
    }//GEN-LAST:event_TxtRgKeyPressed

    private void TxtCPFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCPFKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtRg.requestFocus();
        }
    }//GEN-LAST:event_TxtCPFKeyPressed

    private void TxtCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCPFActionPerformed

    }//GEN-LAST:event_TxtCPFActionPerformed

    private void TxtDataNascimentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtDataNascimentoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ConverteDataNascimentoISO();
        }
    }//GEN-LAST:event_TxtDataNascimentoKeyPressed

    private void TxtDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDataNascimentoActionPerformed

    }//GEN-LAST:event_TxtDataNascimentoActionPerformed

    private void TxtNomeFuncKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNomeFuncKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtDataNascimento.requestFocus();
        }
    }//GEN-LAST:event_TxtNomeFuncKeyPressed

    private void TxtCodFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCodFuncActionPerformed
        SalvarMovimentacaoUsuario_Salvar();
    }//GEN-LAST:event_TxtCodFuncActionPerformed

    private void FeminioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FeminioActionPerformed
        ConverteDataNascimentoISO();
        JOptionPane.showMessageDialog(null, DataNascimentoConvertidaISO);
    }//GEN-LAST:event_FeminioActionPerformed

    private void MasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasculinoActionPerformed
        ConverteDataNascimentoISO();
        JOptionPane.showMessageDialog(null, DataNascimentoConvertidaISO);
    }//GEN-LAST:event_MasculinoActionPerformed

    private void FeminioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FeminioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Feminio.isSelected();
        }
    }//GEN-LAST:event_FeminioKeyPressed

    private void MasculinoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MasculinoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Masculino.isSelected();
        }
    }//GEN-LAST:event_MasculinoKeyPressed

    private void jTFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTFuncionarioMouseClicked
        FuncionarioDao fdao = new FuncionarioDao();

        fdao.MostrarFuncionario().forEach((f) -> {
            TxtCodFunc.setText(Integer.toString(f.getCod_funcionario()));
            TxtNomeFunc.setText(f.getNome_funcionario());
            TxtCPF.setText(f.getCpf_func());
            TxtRg.setText(f.getRg_func());

        });
    }//GEN-LAST:event_jTFuncionarioMouseClicked

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
            java.util.logging.Logger.getLogger(TelaCadastroFunc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroFunc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroFunc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroFunc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroFunc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BGSexo;
    private javax.swing.JRadioButton Feminio;
    private javax.swing.JComboBox<Object> JCCargo;
    private javax.swing.JComboBox<Object> JCUf;
    private javax.swing.JLabel LCPF;
    private javax.swing.JRadioButton Masculino;
    private javax.swing.JPanel PainelFormulario;
    private javax.swing.JPanel PainelOpcoes;
    private javax.swing.JFormattedTextField TxtCPF;
    private javax.swing.JFormattedTextField TxtCelular;
    private javax.swing.JFormattedTextField TxtCep;
    private javax.swing.JTextField TxtCodBairro;
    private javax.swing.JTextField TxtCodFunc;
    private javax.swing.JTextField TxtCod_Cidade;
    private javax.swing.JTextField TxtCod_Uf;
    private javax.swing.JTextField TxtComplemento;
    private javax.swing.JFormattedTextField TxtDataNascimento;
    private javax.swing.JTextField TxtEmail;
    private javax.swing.JTextField TxtEndereco;
    private javax.swing.JTextField TxtNomeBairro;
    private javax.swing.JTextField TxtNomeFunc;
    private javax.swing.JTextField TxtNumero;
    private javax.swing.JTextField TxtPesquisar;
    private javax.swing.JFormattedTextField TxtRg;
    private javax.swing.JFormattedTextField TxtTelefone;
    private javax.swing.JButton jBAtualizar;
    private javax.swing.JButton jBBairro;
    private javax.swing.JButton jBCidade;
    private javax.swing.JButton jBExcluir;
    private javax.swing.JButton jBFechar;
    private javax.swing.JButton jBLimpaCampos;
    private javax.swing.JButton jBNovo;
    private javax.swing.JButton jBPesquisar;
    private javax.swing.JButton jBSalvar;
    private javax.swing.JCheckBox jCAtivoInativo;
    private javax.swing.JComboBox<String> jCEstadoCivil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPConsulta;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTFuncionario;
    private javax.swing.JTextField txtCodCargo;
    private javax.swing.JTextField txtNomeCidade;
    // End of variables declaration//GEN-END:variables
}
