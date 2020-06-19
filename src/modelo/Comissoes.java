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
public class Comissoes {
    private int      cod_comissao;         //Chave primária da tabela comissão
    private String   desc_comissao;        //Usuario irá dar um nome para a comissão
    private double   valor_comisssao;      //Valor da comissão
    private int      cod_categoria;        //Chave Estrangeira da tabela categoria serve para separar tipos de produtos que irão valer para esta comissão
    private String   data_inicio_comissão; //Data que irá iniciar a valer a comissão
    private String   data_final_comissão;  //Apos está data a comissão irá inativar automaticamente ao abrir o softeware
    private Boolean  status_comissao;      //Ativa ou inativa
    private int      cod_usuario;          //Usuario que inseriu a comissao
    

    public Comissoes() {
    }

    public Comissoes(int cod_comissao, String desc_comissao, double valor_comisssao, int cod_categoria, String data_inicio_comissão, String data_final_comissão, Boolean status_comissao, int cod_usuario) {
        this.cod_comissao = cod_comissao;
        this.desc_comissao = desc_comissao;
        this.valor_comisssao = valor_comisssao;
        this.cod_categoria = cod_categoria;
        this.data_inicio_comissão = data_inicio_comissão;
        this.data_final_comissão = data_final_comissão;
        this.status_comissao = status_comissao;
        this.cod_usuario = cod_usuario;
    }

    public int getCod_comissao() {
        return cod_comissao;
    }

    public void setCod_comissao(int cod_comissao) {
        this.cod_comissao = cod_comissao;
    }

    public String getDesc_comissao() {
        return desc_comissao;
    }

    public void setDesc_comissao(String desc_comissao) {
        this.desc_comissao = desc_comissao;
    }

    public double getValor_comisssao() {
        return valor_comisssao;
    }

    public void setValor_comisssao(double valor_comisssao) {
        this.valor_comisssao = valor_comisssao;
    }

    public int getCod_categoria() {
        return cod_categoria;
    }

    public void setCod_categoria(int cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public String getData_inicio_comissão() {
        return data_inicio_comissão;
    }

    public void setData_inicio_comissão(String data_inicio_comissão) {
        this.data_inicio_comissão = data_inicio_comissão;
    }

    public String getData_final_comissão() {
        return data_final_comissão;
    }

    public void setData_final_comissão(String data_final_comissão) {
        this.data_final_comissão = data_final_comissão;
    }

    public Boolean getStatus_comissao() {
        return status_comissao;
    }

    public void setStatus_comissao(Boolean status_comissao) {
        this.status_comissao = status_comissao;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

}
