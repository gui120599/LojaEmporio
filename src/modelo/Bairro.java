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
public class Bairro {
    private int cod_bairro;
    private String desc_bairro;
    private int cod_cidade;
    private String nome_cidade;
    public Bairro() {
    }

    public Bairro(int cod_bairro, String desc_bairro, int cod_cidade, String nome_cidade) {
        this.cod_bairro = cod_bairro;
        this.desc_bairro = desc_bairro;
        this.cod_cidade = cod_cidade;
        this.nome_cidade = nome_cidade;
    }

    public int getCod_bairro() {
        return cod_bairro;
    }

    public void setCod_bairro(int cod_bairro) {
        this.cod_bairro = cod_bairro;
    }

    public String getDesc_bairro() {
        return desc_bairro;
    }

    public void setDesc_bairro(String desc_bairro) {
        this.desc_bairro = desc_bairro;
    }

    public int getCod_cidade() {
        return cod_cidade;
    }

    public void setCod_cidade(int cod_cidade) {
        this.cod_cidade = cod_cidade;
    }

    public String getNome_cidade() {
        return nome_cidade;
    }

    public void setNome_cidade(String nome_cidade) {
        this.nome_cidade = nome_cidade;
    }

    
}
