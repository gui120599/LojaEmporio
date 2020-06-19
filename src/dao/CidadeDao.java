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
import modelo.Cidade;

/**
 *
 * @author Guilherme
 */
public class CidadeDao {

    public int SalvarCidade(Cidade cidade) {
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try {
            c.conectar();
            String sql = "INSERT INTO cidade(nome_cidade,cod_uf) "
                    + "VALUES ('" + cidade.getNome_cidade() + "',"
                    + "'" + cidade.getCod_uf() + "');";
            //String slqUltimo = "SELECT MAX(cod_cidade) FROM cidade;";
            JOptionPane.showMessageDialog(null, "Cidade salva com sucesso!!");
            return c.queryIncluir(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }

    public int ExcluirCidade(int cod_cidade) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try {
            c.conectar();
            String sql = "DELETE FROM cidade WHERE cod_cidade = '" + cod_cidade + "';";
            JOptionPane.showMessageDialog(null, "Cidade excluida com sucesso!!");
            qtdRegistrosAfetados = c.queryDelete(sql);
            return qtdRegistrosAfetados;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!!/n" + e);
            return qtdRegistrosAfetados;
        } finally {
            c.desconectar();
        }
    }

    public int AtualizarCidade(Cidade cidade) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try {
            c.conectar();
            String sql = "UPDATE cidade "
                    + "SET nome_cidade = '" + cidade.getNome_cidade() + "',"
                    + "cod_uf = " + cidade.getCod_uf() + " "
                    + "WHERE cod_cidade = " + cidade.getCod_cidade() + ";";
            JOptionPane.showMessageDialog(null, "Cidade atualizada com sucesso!!");
            qtdRegistrosAfetados = c.queryUpdate(sql);
            return qtdRegistrosAfetados;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!!/n" + e);
            return qtdRegistrosAfetados;
        } finally {
            c.desconectar();
        }
    }

    public Collection<Cidade> MostrarCidade() {
        Collection<Cidade> cidades = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();

            String sql = "SELECT * "
                    + "FROM cidade,uf "
                    + "WHERE cidade.cod_uf = uf.cod_uf ORDER BY 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Cidade ci = new Cidade();
                ci.setCod_cidade(c.getResultSet().getInt("cod_cidade"));
                ci.setNome_cidade(c.getResultSet().getString("nome_cidade"));
                ci.setCod_uf(c.getResultSet().getInt("cod_uf"));
                ci.setDesc_uf(c.getResultSet().getString("desc_uf"));
                cidades.add(ci);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return cidades;
        } finally {
            c.desconectar();
        }
        return cidades;
    }

    public Collection<Cidade> MostrarUltimaCidade() {
        Collection<Cidade> cidades = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();

            String sql = "SELECT cod_cidade FROM cidade ORDER BY cod_cidade DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Cidade ci = new Cidade();
                ci.setCod_cidade(c.getResultSet().getInt("cod_cidade"));
                cidades.add(ci);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return cidades;
        } finally {
            c.desconectar();
        }
        return cidades;
    }

    public Collection<Cidade> BuscaCidadeNome(String nome_cidade) {
        Collection<Cidade> cidades = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();

            String sql = "SELECT*FROM cidade,uf WHERE nome_cidade LIKE '%" + nome_cidade + "%' AND cidade.cod_uf = uf.cod_uf ORDER BY 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Cidade ci = new Cidade();
                ci.setCod_cidade(c.getResultSet().getInt("cod_cidade"));
                ci.setNome_cidade(c.getResultSet().getString("nome_cidade"));
                ci.setCod_uf(c.getResultSet().getInt("cod_uf"));
                ci.setDesc_uf(c.getResultSet().getString("desc_uf"));
                cidades.add(ci);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return cidades;
        } finally {
            c.desconectar();
        }
        return cidades;
    }

    public Collection<Cidade> BuscaCidadeCodigo(int cod_cidade) {
        Collection<Cidade> cidades = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();

            String sql = "SELECT*FROM cidade,uf WHERE cod_cidade = '" + cod_cidade + "' AND cidade.cod_uf = uf.cod_uf ORDER BY 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Cidade ci = new Cidade();
                ci.setCod_cidade(c.getResultSet().getInt("cod_cidade"));
                ci.setNome_cidade(c.getResultSet().getString("nome_cidade"));
                ci.setCod_uf(c.getResultSet().getInt("cod_uf"));
                ci.setDesc_uf(c.getResultSet().getString("desc_uf"));
                cidades.add(ci);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return cidades;
        } finally {
            c.desconectar();
        }
        return cidades;
    }

    public Collection<Cidade> BuscaCidadeCodigoUf(int cod_uf) {
        Collection<Cidade> cidades = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();

            String sql = "SELECT*FROM cidade WHERE cod_uf = '" + cod_uf + "' ORDER BY 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Cidade ci = new Cidade();
                ci.setCod_cidade(c.getResultSet().getInt("cod_cidade"));
                ci.setNome_cidade(c.getResultSet().getString("nome_cidade"));
                ci.setCod_uf(c.getResultSet().getInt("cod_uf"));
                cidades.add(ci);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return cidades;
        } finally {
            c.desconectar();
        }
        return cidades;
    }
}
