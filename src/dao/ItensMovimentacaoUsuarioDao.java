/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import modelo.ItensMovimentacaoUsuario;

/**
 *
 * @author Suporte T.I
 */
public class ItensMovimentacaoUsuarioDao {

    public int SalavarItensMovimentacao(ItensMovimentacaoUsuario m) {
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try {
            c.conectar();

            String sql = "INSERT INTO itens_movimentacao_usuario("
                    + "Valor_antigo,"
                    + "Valor_novo,"
                    + "Cod_movimentacao,"
                    + "Nome_coluna)"
                    + "Values('" + m.getValor_antigo() + "',"
                    + "'" + m.getValor_novo() + "',"
                    + " " + m.getCod_movimentacao() + ","
                    + "'" + m.getNome_coluna() + "');";

            JOptionPane.showMessageDialog(null, "Itens Movimentação salvo com sucesso");
            return c.queryIncluir(sql);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar/n" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }
}
