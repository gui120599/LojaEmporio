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
public class Permissoes {
    private int    cod_permissao;      //Chave primária da tabela
    private String desc_permissao;     //Nome para a permissão
    private String desc_modulo_sistema;//Nome do modulo que essa permissão irá estar

    public Permissoes() {
    }

    public Permissoes(int cod_permissao, String desc_permissao, String desc_modulo_sistema) {
        this.cod_permissao = cod_permissao;
        this.desc_permissao = desc_permissao;
        this.desc_modulo_sistema = desc_modulo_sistema;
    }

    public int getCod_permissao() {
        return cod_permissao;
    }

    public void setCod_permissao(int cod_permissao) {
        this.cod_permissao = cod_permissao;
    }

    public String getDesc_permissao() {
        return desc_permissao;
    }

    public void setDesc_permissao(String desc_permissao) {
        this.desc_permissao = desc_permissao;
    }

    public String getDesc_modulo_sistema() {
        return desc_modulo_sistema;
    }

    public void setDesc_modulo_sistema(String desc_modulo_sistema) {
        this.desc_modulo_sistema = desc_modulo_sistema;
    }

    
}
