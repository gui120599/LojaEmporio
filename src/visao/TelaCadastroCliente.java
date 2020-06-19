/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.ClienteCtrl;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import modelo.Cliente;

/**
 *
 * @author Guilherme
 */
public class TelaCadastroCliente extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroCliente
     */
    public TelaCadastroCliente() {
        initComponents();
        DesabilitaCamposGeral();
        
    }

    public void DesabilitaCamposGeral() {
        TxtNomeCliente.setEnabled(false);
        TxtDataNascimento.setEnabled(false);
        TxtCPF.setEnabled(false);
        TxtRg.setEnabled(false);
        TxtCNPJ.setEnabled(false);
        TxtTelefone.setEnabled(false);
        TxtCelular.setEnabled(false);
        TxtEmail.setEnabled(false);
        TxtCep.setEnabled(false);
        TxtCod_Uf.setEnabled(false);
        JCUf.setEnabled(false);
        jBCidade.setEnabled(false);
        TxtNumero.setEnabled(false);
        TxtComplemento.setEnabled(false);
        jCAtivoInativo.setEnabled(false);
        jBSalvar.setEnabled(false);
        jBAtualizar.setEnabled(false);
        jBExcluir.setEnabled(false);
    }

    public void HabilitaCamposJuridica() {
        TxtNomeCliente.setEnabled(true);
        TxtDataNascimento.setEnabled(false);
        TxtCPF.setEnabled(false);
        TxtRg.setEnabled(false);
        TxtCNPJ.setEnabled(true);
        TxtTelefone.setEnabled(true);
        TxtCelular.setEnabled(true);
        TxtEmail.setEnabled(true);
        TxtCep.setEnabled(true);
        TxtCod_Uf.setEnabled(true);
        JCUf.setEnabled(true);
        jBCidade.setEnabled(true);
        jBBairro.setEnabled(true);
        TxtNumero.setEnabled(true);
        TxtComplemento.setEnabled(true);
        jCAtivoInativo.setEnabled(true);
        jBSalvar.setEnabled(true);
        jBAtualizar.setEnabled(true);
        jBExcluir.setEnabled(true);
    }

    public void HabilitaCamposFisica() {
        TxtNomeCliente.setEnabled(true);
        TxtDataNascimento.setEnabled(true);
        TxtCPF.setEnabled(true);
        TxtRg.setEnabled(true);
        TxtCNPJ.setEnabled(false);
        TxtTelefone.setEnabled(true);
        TxtCelular.setEnabled(true);
        TxtEmail.setEnabled(true);
        TxtCep.setEnabled(true);
        TxtCod_Uf.setEnabled(true);
        JCUf.setEnabled(true);
        jBCidade.setEnabled(true);
        jBBairro.setEnabled(true);
        TxtNumero.setEnabled(true);
        TxtComplemento.setEnabled(true);
        jCAtivoInativo.setEnabled(true);
        jBSalvar.setEnabled(true);
        jBAtualizar.setEnabled(true);
        jBExcluir.setEnabled(true);
    }

    public void LimpaCampos() {
        TxtNomeCliente.setText("");
        JrBPessoaFisica.setSelected(false);
        jRBPessoaJuridica.setSelected(false);
        TxtDataNascimento.setText("");
        TxtCPF.setText("");
        TxtRg.setText("");
        TxtCNPJ.setText("");
        TxtTelefone.setText("");
        TxtCelular.setText("");
        TxtEmail.setText("");
        TxtCep.setText("");
        TxtCod_Uf.setText("");
        TxtCod_Cidade.setText("");
        txtNomeCidade.setText("");
        TxtCodBairro.setText("");
        TxtNomeBairro.setText("");
        TxtNumero.setText("");
        TxtComplemento.setText("");
        jCAtivoInativo.setSelected(false);
        TxtEndereco.setText("");
    }

    public void SalavarCliente() {
        Cliente c = new Cliente();
        ClienteCtrl ctrl = new ClienteCtrl();

//Verifica se os Campos obrigatórios estão preenchidos corretamente
        if (JrBPessoaFisica.isSelected()) {
            if (TxtNomeCliente.getText() == null || TxtNomeCliente.getText().trim().equals("")) {//Verifica se tem informação no campo
                JOptionPane.showMessageDialog(null, "Campo Nome Cliente é Obrigatório!");//Retorna a Mensagem caso não tenha
                TxtNomeCliente.requestFocus();//Coloca o curso no campo sem informação
            } else if (TxtDataNascimento.getText() == null || TxtDataNascimento.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Campo Data Nascimento é Obrigatório!");
                TxtDataNascimento.requestFocus();
            } else if (TxtCPF.getText() == null || TxtCPF.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Campo CPF é Obrigatório!");
                TxtCPF.requestFocus();
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
                jBCidade.requestFocus();
            } else if (TxtCodBairro.getText() == null || TxtCodBairro.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Campo Bairro é Obrigatório!");
                jBBairro.requestFocus();
            } else {

                c.setNome_cliente(TxtNomeCliente.getText());
                c.setTipo_pessoa("Física");
                c.setData_nascimento(TxtDataNascimento.getText());
                c.setCpf_cliente(TxtCPF.getText());
                c.setCep_cliente(TxtCep.getText());
                c.setCod_uf(Integer.parseInt(TxtCod_Uf.getText()));
                c.setCod_cidade(Integer.parseInt(TxtCod_Cidade.getText()));
                c.setCod_bairro(Integer.parseInt(TxtCodBairro.getText()));
                c.setEndereco_cliente(TxtEndereco.getText());
                c.setStatus_cliente(true);
                if (TxtRg.getText() == null || TxtRg.getText().trim().equals("")) {
                    c.setRg_cliente("S/N");
                } else {
                    c.setRg_cliente(TxtRg.getText());
                }
                if (TxtTelefone.getText() == null || TxtTelefone.getText().trim().equals("")) {
                    c.setTelefone_1("S/N");
                } else {
                    c.setTelefone_1(TxtTelefone.getText());
                }
                if (TxtEmail.getText() == null || TxtEmail.getText().trim().equals("")) {
                    c.setEmail_cliente("Não informado");
                } else {
                    c.setEmail_cliente(TxtEmail.getText());
                }
                ctrl.SalvarClienteCtrl(c);
            }
        } else if (jRBPessoaJuridica.isSelected()) {
            if (TxtNomeCliente.getText() == null || TxtNomeCliente.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Campo Nome Cliente é Obrigatório!");
                TxtNomeCliente.requestFocus();
            } else if (TxtDataNascimento.getText() == null || TxtDataNascimento.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Campo Data Nascimento é Obrigatório!");
                TxtDataNascimento.requestFocus();
            } else if (TxtCNPJ.getText() == null || TxtCNPJ.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Campo CNPJ é Obrigatório!");
                TxtCPF.requestFocus();
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
                jBCidade.requestFocus();
            } else if (TxtCodBairro.getText() == null || TxtCodBairro.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Campo Bairro é Obrigatório!");
                jBBairro.requestFocus();
            } else {
                c.setNome_cliente(TxtNomeCliente.getText());
                c.setTipo_pessoa("Jurídica");
                c.setCnpj_cliente(TxtCNPJ.getText());
                c.setEndereco_cliente(TxtEndereco.getText());
                c.setStatus_cliente(true);
                if (TxtRg.getText() == null || TxtRg.getText().trim().equals("")) {
                    c.setRg_cliente("Não informado");
                } else {
                    c.setRg_cliente(TxtRg.getText());
                }
                if (TxtTelefone.getText() == null || TxtTelefone.getText().trim().equals("")) {
                    c.setTelefone_1("S/N");
                } else {
                    c.setTelefone_1(TxtTelefone.getText());
                }
                if (TxtEmail.getText() == null || TxtEmail.getText().trim().equals("")) {
                    c.setEmail_cliente("Não informado");
                } else {
                    c.setEmail_cliente(TxtEmail.getText());
                }
                ctrl.SalvarClienteCtrl(c);
            }

        }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroupPesquisar = new javax.swing.ButtonGroup();
        PainelFormulario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TxtCodCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtNomeCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtDataNascimento = new javax.swing.JFormattedTextField();
        LCPF = new javax.swing.JLabel();
        TxtCPF = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        TxtCNPJ = new javax.swing.JFormattedTextField();
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
        jLabel14 = new javax.swing.JLabel();
        TxtDataCadastro = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        TxtCodUsuario = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        TxtNumero = new javax.swing.JTextField();
        jPConsulta = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        TxtPesquisar = new javax.swing.JTextField();
        jBPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPTipoPessoa = new javax.swing.JPanel();
        JrBPessoaFisica = new javax.swing.JRadioButton();
        jRBPessoaJuridica = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        TxtEndereco = new javax.swing.JTextField();
        jBCidade = new javax.swing.JButton();
        jBBairro = new javax.swing.JButton();
        TxtNomeBairro = new javax.swing.JTextField();
        txtNomeCidade = new javax.swing.JTextField();
        PainelOpcoes = new javax.swing.JPanel();
        jBNovo = new javax.swing.JButton();
        jBSalvar = new javax.swing.JButton();
        jBExcluir = new javax.swing.JButton();
        jBAtualizar = new javax.swing.JButton();
        jBLimpaCampos = new javax.swing.JButton();
        jBFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        setResizable(false);

        PainelFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel1.setText("Código");

        TxtCodCliente.setEditable(false);
        TxtCodCliente.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel2.setText("Nome Cliente");

        TxtNomeCliente.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        TxtNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNomeClienteKeyPressed(evt);
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
        TxtCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCPFKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel4.setText("CNPJ");

        try {
            TxtCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtCNPJ.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

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
            TxtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ####-####")));
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
            TxtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("## ###-###")));
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

        jLabel14.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel14.setText("Data do Cadastro");

        TxtDataCadastro.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        TxtDataCadastro.setForeground(new java.awt.Color(51, 102, 255));
        TxtDataCadastro.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel15.setText("Código usuário que cadastrou");

        TxtCodUsuario.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        TxtCodUsuario.setForeground(new java.awt.Color(51, 153, 255));
        TxtCodUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel17.setText("Número");

        TxtNumero.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        TxtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNumeroKeyPressed(evt);
            }
        });

        jPConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes Cadastrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 12))); // NOI18N

        buttonGroupPesquisar.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jRadioButton1.setText("Código Cliente");

        buttonGroupPesquisar.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jRadioButton2.setText("Nome Cliente");

        jBPesquisar.setText("Pesquisar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Tipo de Pessoa", "Celular e WhatsApp"
            }
        ));
        jTable1.setName(""); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPConsultaLayout = new javax.swing.GroupLayout(jPConsulta);
        jPConsulta.setLayout(jPConsultaLayout);
        jPConsultaLayout.setHorizontalGroup(
            jPConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPConsultaLayout.createSequentialGroup()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtPesquisar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBPesquisar))
            .addComponent(jScrollPane1)
        );
        jPConsultaLayout.setVerticalGroup(
            jPConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPConsultaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(TxtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBPesquisar)))
        );

        jPTipoPessoa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de Pessoa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 2, 10))); // NOI18N

        buttonGroup1.add(JrBPessoaFisica);
        JrBPessoaFisica.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        JrBPessoaFisica.setText("Pessoa Física");
        JrBPessoaFisica.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JrBPessoaFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JrBPessoaFisicaActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRBPessoaJuridica);
        jRBPessoaJuridica.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jRBPessoaJuridica.setText("Pessoa Jurídica");
        jRBPessoaJuridica.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jRBPessoaJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBPessoaJuridicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPTipoPessoaLayout = new javax.swing.GroupLayout(jPTipoPessoa);
        jPTipoPessoa.setLayout(jPTipoPessoaLayout);
        jPTipoPessoaLayout.setHorizontalGroup(
            jPTipoPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPTipoPessoaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JrBPessoaFisica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRBPessoaJuridica))
        );
        jPTipoPessoaLayout.setVerticalGroup(
            jPTipoPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPTipoPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(JrBPessoaFisica)
                .addComponent(jRBPessoaJuridica))
        );

        jLabel18.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel18.setText("Endereço");

        TxtEndereco.setEditable(false);
        TxtEndereco.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        jBCidade.setText("Cidade");
        jBCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCidadeActionPerformed(evt);
            }
        });

        jBBairro.setText("Bairro");
        jBBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBairroActionPerformed(evt);
            }
        });

        TxtNomeBairro.setEditable(false);
        TxtNomeBairro.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        txtNomeCidade.setEditable(false);
        txtNomeCidade.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        javax.swing.GroupLayout PainelFormularioLayout = new javax.swing.GroupLayout(PainelFormulario);
        PainelFormulario.setLayout(PainelFormularioLayout);
        PainelFormularioLayout.setHorizontalGroup(
            PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PainelFormularioLayout.createSequentialGroup()
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtNomeCliente))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelFormularioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtCodUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelFormularioLayout.createSequentialGroup()
                        .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PainelFormularioLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtEndereco))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PainelFormularioLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtComplemento)))
                        .addGap(18, 18, 18)
                        .addComponent(jCAtivoInativo))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelFormularioLayout.createSequentialGroup()
                                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(LCPF)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8))
                            .addGroup(PainelFormularioLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtCod_Uf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JCUf, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBCidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtCod_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtEmail)
                            .addGroup(PainelFormularioLayout.createSequentialGroup()
                                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(59, 59, 59)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelFormularioLayout.createSequentialGroup()
                                        .addComponent(txtNomeCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBBairro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtCodBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(100, 100, 100)))
                                .addGap(0, 60, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelFormularioLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(TxtNomeBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        PainelFormularioLayout.setVerticalGroup(
            PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelFormularioLayout.createSequentialGroup()
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TxtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(jLabel2)))
                    .addGroup(PainelFormularioLayout.createSequentialGroup()
                        .addComponent(jPTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LCPF)
                    .addComponent(TxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(TxtCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(TxtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(TxtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(TxtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(TxtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(TxtCod_Uf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCUf, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCod_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCodBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBCidade)
                    .addComponent(jBBairro)
                    .addComponent(TxtNomeBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(TxtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCAtivoInativo)
                    .addComponent(jLabel17)
                    .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PainelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(TxtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15)
                    .addComponent(TxtCodUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jBExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBLimpaCampos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                .addContainerGap())
        );
        PainelOpcoesLayout.setVerticalGroup(
            PainelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelOpcoesLayout.createSequentialGroup()
                .addComponent(jBNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBSalvar)
                .addGap(9, 9, 9)
                .addComponent(jBAtualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBLimpaCampos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 496, Short.MAX_VALUE)
                .addComponent(jBFechar))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PainelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PainelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PainelOpcoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNovoActionPerformed

    }//GEN-LAST:event_jBNovoActionPerformed

    private void jBLimpaCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimpaCamposActionPerformed
        LimpaCampos();
    }//GEN-LAST:event_jBLimpaCamposActionPerformed

    private void TxtComplementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtComplementoActionPerformed
        TxtEndereco.setText(String.valueOf(TxtComplemento.getText()) + " Nº "
                + String.valueOf(TxtNumero.getText()) + " "
                + String.valueOf(TxtNomeBairro.getText()) + " "
                + String.valueOf(txtNomeCidade.getText()) + " "
                + String.valueOf(JCUf.getSelectedItem()));
    }//GEN-LAST:event_TxtComplementoActionPerformed

    private void JrBPessoaFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JrBPessoaFisicaActionPerformed
        HabilitaCamposFisica();
        TxtNomeCliente.requestFocus();
    }//GEN-LAST:event_JrBPessoaFisicaActionPerformed

    private void jRBPessoaJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBPessoaJuridicaActionPerformed
        HabilitaCamposJuridica();
        TxtNomeCliente.requestFocus();
    }//GEN-LAST:event_jRBPessoaJuridicaActionPerformed

    private void TxtNomeClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNomeClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (JrBPessoaFisica.isSelected()) {
                TxtDataNascimento.requestFocus();
            } else {
                TxtCNPJ.requestFocus();
            }
        }
    }//GEN-LAST:event_TxtNomeClienteKeyPressed

    private void TxtDataNascimentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtDataNascimentoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtCPF.requestFocus();
        }
    }//GEN-LAST:event_TxtDataNascimentoKeyPressed

    private void TxtCPFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCPFKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtRg.requestFocus();
        }        
    }//GEN-LAST:event_TxtCPFKeyPressed

    private void TxtRgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtRgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtTelefone.requestFocus();
        }        
    }//GEN-LAST:event_TxtRgKeyPressed

    private void TxtTelefoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTelefoneKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtCelular.requestFocus();
        }        
    }//GEN-LAST:event_TxtTelefoneKeyPressed

    private void TxtCelularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCelularKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtEmail.requestFocus();
        }        
    }//GEN-LAST:event_TxtCelularKeyPressed

    private void TxtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtEmailKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtCep.requestFocus();
        }        
    }//GEN-LAST:event_TxtEmailKeyPressed

    private void TxtCepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCepKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JCUf.requestFocus();
        }        
    }//GEN-LAST:event_TxtCepKeyPressed

    private void JCUfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JCUfKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jBCidade.requestFocus();
        }       
    }//GEN-LAST:event_JCUfKeyPressed

    private void TxtNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNumeroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtComplemento.requestFocus();
        }        
    }//GEN-LAST:event_TxtNumeroKeyPressed

    private void TxtComplementoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtComplementoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SalavarCliente();
        }        
    }//GEN-LAST:event_TxtComplementoKeyPressed

    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed
        SalavarCliente();
    }//GEN-LAST:event_jBSalvarActionPerformed

    private void jBCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCidadeActionPerformed
        TelaPesquisaCidade();        
    }//GEN-LAST:event_jBCidadeActionPerformed

    private void jBBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBairroActionPerformed
        TelaPesquisaBairro();        
    }//GEN-LAST:event_jBBairroActionPerformed

    private void jBFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFecharActionPerformed
        this.dispose();        
    }//GEN-LAST:event_jBFecharActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JCUf;
    private javax.swing.JRadioButton JrBPessoaFisica;
    private javax.swing.JLabel LCPF;
    private javax.swing.JPanel PainelFormulario;
    private javax.swing.JPanel PainelOpcoes;
    private javax.swing.JFormattedTextField TxtCNPJ;
    private javax.swing.JFormattedTextField TxtCPF;
    private javax.swing.JFormattedTextField TxtCelular;
    private javax.swing.JFormattedTextField TxtCep;
    private javax.swing.JTextField TxtCodBairro;
    private javax.swing.JTextField TxtCodCliente;
    private javax.swing.JLabel TxtCodUsuario;
    private javax.swing.JTextField TxtCod_Cidade;
    private javax.swing.JTextField TxtCod_Uf;
    private javax.swing.JTextField TxtComplemento;
    private javax.swing.JLabel TxtDataCadastro;
    private javax.swing.JFormattedTextField TxtDataNascimento;
    private javax.swing.JTextField TxtEmail;
    private javax.swing.JTextField TxtEndereco;
    private javax.swing.JTextField TxtNomeBairro;
    private javax.swing.JTextField TxtNomeCliente;
    private javax.swing.JTextField TxtNumero;
    private javax.swing.JTextField TxtPesquisar;
    private javax.swing.JFormattedTextField TxtRg;
    private javax.swing.JFormattedTextField TxtTelefone;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroupPesquisar;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPTipoPessoa;
    private javax.swing.JRadioButton jRBPessoaJuridica;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtNomeCidade;
    // End of variables declaration//GEN-END:variables
}
