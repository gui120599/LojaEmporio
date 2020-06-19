/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import modelo.LoginSessao;

/**
 *
 * @author Guilherme
 */
public class LoginSessaoDao {

    public int AbrirSessao(LoginSessao l) {
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try {
            c.conectar();
            String sql = "INSERT INTO login_sessao("
                    + "Cod_usuario_sessao,"
                    + "Data_Hora,"
                    + "Ip_da_maquina)"
                    + "VALUES("
                    + "'" + l.getCod_usuario() + "',"
                    + "'" + l.getData_hora() + "',"
                    + "'" + l.getIp_da_maquina() + "');";
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }

    public Collection<LoginSessao> BuscarSessao(String ip) {
        Collection<LoginSessao> logis = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();

            String sql = "SELECT cod_usuario_sessao FROM login_sessao WHERE Ip_da_maquina = '"+ip+"' ORDER BY Cod_sessao DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                LoginSessao l = new LoginSessao();
                l.setCod_usuario(c.getResultSet().getInt("Cod_usuario_sessao"));
                logis.add(l);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro= " + e);
            return logis;
        } finally {
            c.desconectar();
        }
        return logis;
    }
}
