/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Guilherme
 */
public class Usuario {
    private int    cod_usuario;      //Chave primario para o usuario
    private int    cod_funcionario;  //Chave Estrangeira da tabela funcionario
    private String login_usuario;    //Login para acessar o software
    private String senha_usuario;    //Senha "      "          "

    public Usuario() {
    }

    public Usuario(int cod_usuario, int cod_funcionario, String login_usuario, String senha_usuario) {
        this.cod_usuario = cod_usuario;
        this.cod_funcionario = cod_funcionario;
        this.login_usuario = login_usuario;
        this.senha_usuario = senha_usuario;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public int getCod_funcionario() {
        return cod_funcionario;
    }

    public void setCod_funcionario(int cod_funcionario) {
        this.cod_funcionario = cod_funcionario;
    }

    public String getLogin_usuario() {
        return login_usuario;
    }

    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

}
