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
public class Uf {
    private int cod_uf;
    private String desc_uf;

    public Uf() {
    }

    public Uf(int cod_uf, String desc_uf) {
        this.cod_uf = cod_uf;
        this.desc_uf = desc_uf;
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

    @Override
    public String toString() {
        return getDesc_uf(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
