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
public class Cidade {
    private int cod_cidade;
    private String nome_cidade;
    private int cod_uf;
    private String desc_uf;

    public Cidade() {
    }

    public Cidade(int cod_cidade, String nome_cidade, int cod_uf, String desc_uf) {
        this.cod_cidade = cod_cidade;
        this.nome_cidade = nome_cidade;
        this.cod_uf = cod_uf;
        this.desc_uf = desc_uf;
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

    public int getCod_uf() {
        return cod_uf;
    }

    public void setCod_uf(int cod_uf) {
        this.cod_uf = cod_uf;
    }

    public String getDesc_uf() {
        return desc_uf;
    }

    public void setDesc_uf(String desc_uf) {
        this.desc_uf = desc_uf;
    }

    public void getNome_cidade(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
@Override
    public String toString() {
        return getNome_cidade(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
