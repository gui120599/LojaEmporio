/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import modelo.MovimentacaoUsuario;

/**
 *
 * @author Guilherme
 */
public class MovimentacaoUsuarioDao {

    public int AbrirMovimentacao(MovimentacaoUsuario m) {
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try {
            c.conectar();

            String sql = "INSERT INTO movimentacao_usuario(Cod_usuario)Values(" + m.getCod_usuario() + ");";

            //JOptionPane.showMessageDialog(null, "Movimentação Aberta com sucesso");
            return c.queryIncluir(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar/n" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }

    public Collection<MovimentacaoUsuario> BuscarUltimaMovimentacao() {
        Collection<MovimentacaoUsuario> ms = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try {
            c.conectar();

            String sql = "SELECT Cod_movimentacao FROM movimentacao_usuario ORDER BY Cod_movimentacao DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                MovimentacaoUsuario m = new MovimentacaoUsuario();
                m.setCod_movimentacao(c.getResultSet().getInt("Cod_movimentacao"));
                ms.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ms;
        } finally {
            c.desconectar();
        }
        return ms;
    }

    public int SalvaMovimentacao(MovimentacaoUsuario m) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try {
            c.conectar();
            String sql = "UPDATE movimentacao_usuario "
                    + "SET Tipo_movimentacao = '" + m.getTipo_movimentacao() + "',"
                    + "Tabela_alterada = '" + m.getTabela_alterada() + "',"
                    + "Cod_registro_alterado ='" + m.getCod_registro_alterado() + "',"
                    + "Data_Hora_movimentacao = '" + m.getData_Hora_movimentacao() + "',"
                    + "Cod_usuario = '" + m.getCod_usuario() + "'"
                    + "WHERE Cod_movimentacao = '" + m.getCod_movimentacao() + "' ;";

            //JOptionPane.showMessageDialog(null, "Movimentação Salva!!");

            qtdRegistrosAfetados = c.queryUpdate(sql);
            return qtdRegistrosAfetados;
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!!/n" + e);
            return qtdRegistrosAfetados;
        } finally {
            c.desconectar();
        }
    }
}
