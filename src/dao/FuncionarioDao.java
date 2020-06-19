/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import modelo.Funcionario;

/**
 *
 * @author Suporte T.I
 */
public class FuncionarioDao {

    public int SalvarFuncionario(Funcionario func) {
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try {
            c.conectar();
            String sql = "INSERT INTO funcionario"
                    + "(nome_funcionario,"
                    + "cpf_func,"
                    + "rg_func,"
                    + "data_nascimento_func,"
                    + "sexo_func,"
                    + "estado_civil_func,"
                    + "cod_cargo,"
                    + "telefone_func,"
                    + "whatsapp_func,"
                    + "email_func,"
                    + "cep_func,"
                    + "cod_uf,"
                    + "cod_cidade,"
                    + "cod_bairro,"
                    + "endereco_func,"
                    + "status_func,"
                    + "data_cadastro_func,"
                    + "cod_usuario_cadastro)"
                    + "VALUES('" + func.getNome_funcionario() + "',"
                    + "'" + func.getCpf_func() + "',"
                    + "'" + func.getRg_func() + "',"
                    + "'" + func.getData_nascimento_func() + "',"
                    + "'" + func.getSexo() + "',"
                    + "'" + func.getEstado_civil_func() + "',"
                    + "" + func.getCod_cargo() + ","
                    + "'" + func.getTelefone() + "',"
                    + "'" + func.getWhatsApp() + "',"
                    + "'" + func.getEmail() + "',"
                    + "'" + func.getCep() + "',"
                    + "" + func.getCod_uf() + ","
                    + "" + func.getCod_cidade() + ","
                    + "" + func.getCod_bairro() + ","
                    +"'"+func.getEndereco()+"',"
                    +""+func.getStatus_func()+","
                    +"'"+func.getData_cadastro_func()+"',"
                    +""+func.getCod_usuario_cadastro()+");";
            JOptionPane.showMessageDialog(null, "Funcionário salvo com sucesso!!");
            return c.queryIncluir(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!!\n" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }
    public int AtualizarFuncionario(Funcionario func) {
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try {
            c.conectar();
            String sql = "UPDATE funcionario"
                    + "SET nome_funcionario = "+func.getNome_funcionario()+","
                    + "cpf_func = "+ func.getCpf_func() + "',"
                    + "rg_func = "+ func.getRg_func() + "',"
                    + "data_nascimento_func = "+ func.getData_nascimento_func() + "',"
                    + "sexo_func = "+ func.getRg_func() + "',"
                    + "estado_civil_func = "+ func.getEstado_civil_func() + "',"
                    + "cod_cargo = "+ func.getCod_cargo() + ","
                    + "telefone_func = "+ func.getTelefone() + "',"
                    + "whatsapp_func = "+ func.getWhatsApp() + "',"
                    + "email_func = "+ func.getEmail() + "',"
                    + "cep_func = "+ func.getCep() + "',"
                    + "cod_uf = "+ func.getCod_uf() + ","
                    + "cod_cidade = "+ func.getCod_cidade() + ","
                    + "cod_bairro = "+ func.getCod_bairro() + ","
                    + "endereco_func = "+func.getEndereco()+"',"
                    + "status_func = "+func.getStatus_func()+","
                    + "data_cadastro_func = "+func.getData_cadastro_func()+"',"
                    + "cod_usuario_cadastro = "+func.getCod_usuario_cadastro()+" "
                    + "WHERE cod_funcionario = "+func.getCod_funcionario()+";";
            JOptionPane.showMessageDialog(null, "Funcionário Atualizado com sucesso!!");
            return c.queryIncluir(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!!\n" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }
    public Collection<Funcionario> MostrarFuncionario() {
        Collection<Funcionario> funcionarios = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();
            
            String sql = "SELECT * FROM funcionario,cargo WHERE funcionario.cod_cargo = cargo.cod_cargo ORDER BY 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Funcionario f = new Funcionario();
                f.setCod_funcionario(c.getResultSet().getInt("cod_funcionario"));
                f.setNome_funcionario(c.getResultSet().getString("nome_funcionario"));
                f.setCod_cargo(c.getResultSet().getInt("cod_cargo"));
                f.setDesc_cargo(c.getResultSet().getString("desc_cargo"));
                funcionarios.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return funcionarios;
        } finally {
            c.desconectar();
        }
        return funcionarios;
    }
    public Collection<Funcionario> MostrarUltimoCodFuncionario() {
        Collection<Funcionario> funcionarios = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();
            
            String sql = "SELECT cod_funcionario FROM funcionario ORDER BY cod_funcionario DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Funcionario f = new Funcionario();
                f.setCod_funcionario(c.getResultSet().getInt("cod_funcionario"));
                funcionarios.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return funcionarios;
        } finally {
            c.desconectar();
        }
        return funcionarios;
    }
    public Collection<Funcionario> MostrarUlCodFuncionario() {
        Collection<Funcionario> funcionarios = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();
            
            String sql = "SELECT cod_funcionario,nome_funcionario FROM funcionario ORDER BY cod_funcionario DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Funcionario f = new Funcionario();
                f.setCod_funcionario(c.getResultSet().getInt("cod_funcionario"));
                f.setNome_funcionario(c.getResultSet().getString(("nome_funcionario")));
                funcionarios.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return funcionarios;
        } finally {
            c.desconectar();
        }
        return funcionarios;
    }
    public Collection<Funcionario> MostrarDataUlCodFuncionario() {
        Collection<Funcionario> funcionarios = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();
            
            String sql = "SELECT data_nascimento_func FROM funcionario ORDER BY cod_funcionario DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Funcionario f = new Funcionario();
                f.setData_nascimento_func(c.getResultSet().getString("data_nascimento_func"));
                funcionarios.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return funcionarios;
        } finally {
            c.desconectar();
        }
        return funcionarios;
    }
    
}
