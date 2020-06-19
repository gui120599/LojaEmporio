/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import modelo.Usuario;

/**
 *
 * @author Guilherme
 */
public class UsuarioDao {

    public int SalvarUsuario(Usuario usuario) {
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try {
            c.conectar();
            String sql = "INSERT INTO usuario("
                    + "cod_funcionario,"
                    + "login_usuario,"
                    + "senha_usuario)"
                    + "VALUES("
                    + ""+usuario.getCod_funcionario()+","
                    + "'"+usuario.getLogin_usuario()+"',"
                    + "'"+usuario.getSenha_usuario()+"');";
            
            JOptionPane.showMessageDialog(null, "Usuario salvo com sucesso!!");
            return c.queryIncluir(sql);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }

    public Collection<Usuario> ChecarLogin(String Login, String Senha) {
        Collection<Usuario> usuarios = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();

            String sql = "SELECT * FROM usuario WHERE login_usuario = '" + Login + "' AND senha_usuario = '" + Senha + "';";

            c.query(sql);
            while (c.getResultSet().next()) {
                Usuario u = new Usuario();
                u.setCod_usuario(c.getResultSet().getInt("cod_usuario"));
                u.setLogin_usuario(c.getResultSet().getString("login_usuario"));
                usuarios.add(u);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro= " + e);
            return usuarios;
        } finally {
            c.desconectar();
        }
        return usuarios;
    }
    public Collection<Usuario> MostrarUsuario() {
        Collection<Usuario> usuarios = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();

            String sql = "SELECT * FROM usuario ;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Usuario u = new Usuario();
                u.setCod_usuario(c.getResultSet().getInt("cod_usuario"));
                u.setLogin_usuario(c.getResultSet().getString("login_usuario"));
                usuarios.add(u);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro= " + e);
            return usuarios;
        } finally {
            c.desconectar();
        }
        return usuarios;
    }

    public boolean Checar(String Login, String Senha) {
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        boolean check = false;
        try {
            c.conectar();

            String sql = "SELECT cod_usuario FROM usuario WHERE login_usuario = '" + Login + "' AND senha_usuario = '" + Senha + "';";

            c.query(sql);
            if (c.getResultSet().next()) {

                check = true;
            }
        } catch (SQLException e) {

        } finally {
            c.desconectar();
        }
        return check;
    }
}
