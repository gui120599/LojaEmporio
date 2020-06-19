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
public class Cargo {
    private int    cod_cargo;   //Chave Primária do cargo
    private String desc_cargo;  //Nome do cargo que o funcionário exerce

    public Cargo() {
    }

    public Cargo(int cod_cargo, String desc_cargo) {
        this.cod_cargo = cod_cargo;
        this.desc_cargo = desc_cargo;
    }

    public int getCod_cargo() {
        return cod_cargo;
    }

    public void setCod_cargo(int cod_cargo) {
        this.cod_cargo = cod_cargo;
    }

    public String getDesc_cargo() {
        return desc_cargo;
    }

    public void setDesc_cargo(String desc_cargo) {
        this.desc_cargo = desc_cargo;
    }

    @Override
    public String toString() {
        return getDesc_cargo(); //To change body of generated methods, choose Tools | Templates.
    }

    
}
