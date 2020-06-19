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
import modelo.Uf;

/**
 *
 * @author Guilherme
 */
public class UfDao {

    public int SalvarUf(Uf uf) {
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();

            String sql = "INSERT INTO uf(desc_uf)VALUES('" + uf.getDesc_uf() + "');";

            JOptionPane.showMessageDialog(null, "Cargo salvo com sucesso!!");

            return c.queryIncluir(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar ! \n" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }

    public int ExcluirUf(int cod_uf) {

        int qtdRegistrosAfetados = 0;

        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");

        try {

            c.conectar();

            String sql = "DELETE FROM uf WHERE cod_uf = '" + cod_uf + "';";

            qtdRegistrosAfetados = c.queryDelete(sql);
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            qtdRegistrosAfetados = c.queryDelete(sql);
            return qtdRegistrosAfetados;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!! \n" + e);
            return qtdRegistrosAfetados;

        } finally {
            c.desconectar();

        }

    }

    public int AtualizarUf(Uf uf) {

        int qtdRegistrosAfetados = 0;

        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");

        try {

            c.conectar();

            String sql = "UPDATE uf SET "
                    + "desc_uf = '" + uf.getDesc_uf() + "' "
                    + "WHERE cod_uf = " + uf.getCod_uf() + ";";

            qtdRegistrosAfetados = c.queryUpdate(sql);
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!!");
            qtdRegistrosAfetados = c.queryUpdate(sql);
            return qtdRegistrosAfetados;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!! /n" + e);
            return qtdRegistrosAfetados;

        } finally {
            c.desconectar();

        }

    }

    public Collection<Uf> Mostrar() {
        Collection<Uf> ufs = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try {
            c.conectar();

            String sql = "SELECT * FROM uf;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Uf uf = new Uf();
                uf.setCod_uf(c.getResultSet().getInt("cod_uf"));
                uf.setDesc_uf(c.getResultSet().getString("desc_uf"));
                ufs.add(uf);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ufs;
        } finally {
            c.desconectar();
        }
        return ufs;
    }
    public Collection<Uf> MostrarUltmoCodigo() {
        Collection<Uf> ufs = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try {
            c.conectar();

            String sql = "SELECT cod_uf FROM uf ORDER BY cod_uf DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Uf uf = new Uf();
                uf.setCod_uf(c.getResultSet().getInt("cod_uf"));
                ufs.add(uf);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ufs;
        } finally {
            c.desconectar();
        }
        return ufs;
    }

}
