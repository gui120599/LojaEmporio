/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import javax.swing.JOptionPane;
import modelo.Cliente;

/**
 *
 * @author Guilherme
 */
public class ClienteDao {
    public int SalvarCliente(Cliente cliente){
        Conexao c = new Conexao("MySql", "localhost", "3357", "LojaEmporio", "root", "");
        try{
            c.conectar();
            String sql = "INSERT INTO cliente"
                    + "(nome_cliente,"
                    + "data_nascimento,"
                    + "cpf_cliente,"
                    + "rg_cliente,"
                    + "cnpj_cliente,"
                    + "telefone_1,"
                    + "whatsapp_cliente,"
                    + "email_cliente,"
                    + "cep_cliente,"
                    + "endereco_cliente,"
                    + "cod_uf_cliente,"
                    + "cod_cidade_cliente,"
                    + "cod_bairro_cliente,"
                    + "tipo_pessoa,"
                    + "status_cliente,"
                    + "data_cadastro_cliente,"
                    + "cod_usuario_cadastro_cliente)"
                    + "VALUES ('"+cliente.getNome_cliente()+"',"
                    + "'"+cliente.getData_nascimento()+"',"
                    + "'"+cliente.getCpf_cliente()+"',"
                    + "'"+cliente.getRg_cliente()+"',"
                    + "'"+cliente.getCnpj_cliente()+"'"
                    + "'"+cliente.getTelefone_1()+"',"
                    + "'"+cliente.getWhatsApp_cliente()+"',"
                    + "'"+cliente.getEmail_cliente()+"',"
                    + "'"+cliente.getCep_cliente()+"',"
                    + "'"+cliente.getCod_uf()+"',"
                    + "'"+cliente.getCod_cliente()+"',"
                    + "'"+cliente.getCod_bairro()+"',"
                    + "'"+cliente.getTipo_pessoa()+","
                    + "'"+cliente.getStatus_cliente()+"',"
                    + "'"+cliente.getData_cadastro_cliente()+"',"
                    + "'"+cliente.getCod_usuario_cadastro()+"',);";
            //String sqlUltimo = "SELECT MAX (cod_cliente)FROM cliente;";
            JOptionPane.showMessageDialog(null,"Cliente salvo com sucesso!!");
            return c.queryIncluir(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao salvar!!/n"+ e);
            return 0;
        }finally{
            c.desconectar();
        }
    }
    public int ExcluirCliente(int cod_cliente){
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao("MySql", "localhost", "3357", "LojaEmporio", "root", "");
        try{
            c.conectar();
            String sql = "DELETE FROM cliente WHERE cod_cliente ='"+cod_cliente+"';";
            JOptionPane.showMessageDialog(null,"Cliente excluido com sucesso!!");
            qtdRegistrosAfetados = c.queryDelete(sql);
            return qtdRegistrosAfetados;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao excluir!!/n"+ e);
            return qtdRegistrosAfetados;
        }finally{
            c.desconectar();
        }
    }
    public int AtualizarCliente(Cliente cliente){
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao("MySql", "localhost", "3357", "LojaEmporio", "root", "");
        try{
            c.conectar();
            String sql = "UPDATE cliente"
                    + "SET nome_cliente = '"+cliente.getNome_cliente()+"',"
                    + "data_nascimento = '"+cliente.getData_nascimento()+"',"
                    + "cpf_cliente = '"+cliente.getCpf_cliente()+"',"
                    + "rg_cliente = '"+cliente.getRg_cliente()+"',"
                    + "cnpj_cliente = '"+cliente.getCnpj_cliente()+"',"
                    + "telefone_1 = '"+cliente.getTelefone_1()+"',"
                    + "whatsapp_cliente = '"+cliente.getWhatsApp_cliente()+"',"
                    + "email_cliente = '"+cliente.getEmail_cliente()+"',"
                    + "cep_cliente = '"+cliente.getCep_cliente()+"',"
                    + "cod_uf_cliente = '"+cliente.getCod_uf()+"',"
                    + "cod_cidade_cliente = '"+cliente.getCod_cidade()+"',"
                    + "cod_bairro_cliente = '"+cliente.getCod_bairro()+"',"
                    + "endereco_cliente = '"+cliente.getEndereco_cliente()+"',"
                    + "tipo_pessoa = '"+cliente.getTipo_pessoa()+"',"
                    + "status_cliente = '"+cliente.getStatus_cliente()+"',"
                    + "data_cadastro_cliente = '"+cliente.getData_cadastro_cliente()+"',"
                    + "cod_usuario_cadastro_cliente = '"+cliente.getCod_usuario_cadastro()+"'"
                    + "WHERE cod_cliente = '"+cliente.getCod_cliente()+"';";
            JOptionPane.showMessageDialog(null,"Cliente atualizado com sucesso!!");
            qtdRegistrosAfetados = c.queryUpdate(sql);
            return qtdRegistrosAfetados;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao atualizar!!"+ e);
            return qtdRegistrosAfetados;
        }finally{
            c.desconectar();
        }
    }
}
