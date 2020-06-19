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
public class Produto {
   private int       cod_produto;           // Gerado para cada produto
   private String    desc_produto;          // Marca e modelo
   private int       quantidade_produto;    //Quantidade não será inserida pelo o usuário, e sim pelo o proprio software a partir da tabela CodigoRastreioProduto
   private int       cod_categoria;         //Chave Estrangeira da tabela categoria 
   private boolean   status_produto;        //Ativo ou inativo
   private String    data_cadastro_produto; //Data que o usuario efetuou o cadastro o produto
   private int       cod_usuario_cadastro;  //Usuario que inseriu

    public Produto() {
    }

    public Produto(int cod_produto, String desc_produto, int quantidade_produto, int cod_categoria, boolean status_produto, String data_cadastro_produto, int cod_usuario_cadastro) {
        this.cod_produto = cod_produto;
        this.desc_produto = desc_produto;
        this.quantidade_produto = quantidade_produto;
        this.cod_categoria = cod_categoria;
        this.status_produto = status_produto;
        this.data_cadastro_produto = data_cadastro_produto;
        this.cod_usuario_cadastro = cod_usuario_cadastro;
    }

    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    public String getDesc_produto() {
        return desc_produto;
    }

    public void setDesc_produto(String desc_produto) {
        this.desc_produto = desc_produto;
    }

    public int getQuantidade_produto() {
        return quantidade_produto;
    }

    public void setQuantidade_produto(int quantidade_produto) {
        this.quantidade_produto = quantidade_produto;
    }

    public int getCod_categoria() {
        return cod_categoria;
    }

    public void setCod_categoria(int cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public boolean isStatus_produto() {
        return status_produto;
    }

    public void setStatus_produto(boolean status_produto) {
        this.status_produto = status_produto;
    }

    public String getData_cadastro_produto() {
        return data_cadastro_produto;
    }

    public void setData_cadastro_produto(String data_cadastro_produto) {
        this.data_cadastro_produto = data_cadastro_produto;
    }

    public int getCod_usuario_cadastro() {
        return cod_usuario_cadastro;
    }

    public void setCod_usuario_cadastro(int cod_usuario_cadastro) {
        this.cod_usuario_cadastro = cod_usuario_cadastro;
    }

}
