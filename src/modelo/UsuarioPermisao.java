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
public class UsuarioPermisao {
    private int      cod_permissao;    //Chave Estrangeira da tabela permissoes
    private int      cod_usuario;      //Chave Estrangeira da tabela usuario
    private Boolean  permissao_ativa;  //Permissão ativa ou desativada para o usuario

    public UsuarioPermisao() {
    }

    public UsuarioPermisao(int cod_permissao, int cod_usuario, Boolean permissao_ativa) {
        this.cod_permissao = cod_permissao;
        this.cod_usuario = cod_usuario;
        this.permissao_ativa = permissao_ativa;
    }

    public int getCod_permissao() {
        return cod_permissao;
    }

    public void setCod_permissao(int cod_permissao) {
        this.cod_permissao = cod_permissao;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public Boolean getPermissao_ativa() {
        return permissao_ativa;
    }

    public void setPermissao_ativa(Boolean permissao_ativa) {
        this.permissao_ativa = permissao_ativa;
    }
    
}
