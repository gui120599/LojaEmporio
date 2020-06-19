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
public class CodigoRastreioProduto {
    private int     cod_rastreio_produto;     //Código gerado para cada produto cadastrado na loja
    private int     cod_produto;              //Chave Estrangeira da tabela produto
    private String  desc_produto_complementar;//Estilo do produto para melhor localização
    private double  valor_custo_produto;      //Valor que o produto teve para loja
    private double  valor_venda_produto;      //Valor que o produto será vendido na loja
    private boolean status_produto;           //Se está disponivel ou não
    private String  desc_status_produto;      //Vendido ou não Vendido
    private String  data_insercao_produto;    //Data que foi inserido no ESTOQUE
    private String  cod_usuario;              //Usuario que inseriu o porduto

    public CodigoRastreioProduto() {
    }

    public CodigoRastreioProduto(int cod_rastreio_produto, int cod_produto, String desc_produto_complementar, double valor_custo_produto, double valor_venda_produto, boolean status_produto, String desc_status_produto, String data_insercao_produto, String cod_usuario) {
        this.cod_rastreio_produto = cod_rastreio_produto;
        this.cod_produto = cod_produto;
        this.desc_produto_complementar = desc_produto_complementar;
        this.valor_custo_produto = valor_custo_produto;
        this.valor_venda_produto = valor_venda_produto;
        this.status_produto = status_produto;
        this.desc_status_produto = desc_status_produto;
        this.data_insercao_produto = data_insercao_produto;
        this.cod_usuario = cod_usuario;
    }

    public int getCod_rastreio_produto() {
        return cod_rastreio_produto;
    }

    public void setCod_rastreio_produto(int cod_rastreio_produto) {
        this.cod_rastreio_produto = cod_rastreio_produto;
    }

    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    public String getDesc_produto_complementar() {
        return desc_produto_complementar;
    }

    public void setDesc_produto_complementar(String desc_produto_complementar) {
        this.desc_produto_complementar = desc_produto_complementar;
    }

    public double getValor_custo_produto() {
        return valor_custo_produto;
    }

    public void setValor_custo_produto(double valor_custo_produto) {
        this.valor_custo_produto = valor_custo_produto;
    }

    public double getValor_venda_produto() {
        return valor_venda_produto;
    }

    public void setValor_venda_produto(double valor_venda_produto) {
        this.valor_venda_produto = valor_venda_produto;
    }

    public boolean isStatus_produto() {
        return status_produto;
    }

    public void setStatus_produto(boolean status_produto) {
        this.status_produto = status_produto;
    }

    public String getDesc_status_produto() {
        return desc_status_produto;
    }

    public void setDesc_status_produto(String desc_status_produto) {
        this.desc_status_produto = desc_status_produto;
    }

    public String getData_insercao_produto() {
        return data_insercao_produto;
    }

    public void setData_insercao_produto(String data_insercao_produto) {
        this.data_insercao_produto = data_insercao_produto;
    }

    public String getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(String cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    
}
