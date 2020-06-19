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
public class Venda {
    private int    cod_venda;                //Codigo gerado para cada venda efetuada
    private int    cod_caixa;                //Chave Estrangeira da tabela caixa
    private int    cod_cliente;              //Chave Estrangeira da Tabela Cliente
    private int    cod_rastreio_produto;     //Chave Estrangeira da Tabela CodigoRatreioProduto        
    private String data_venda;               //Data que a venda foi efetuada
    private String hora_venda;               //Hora que a venda foi efetuada
    private int    quantidade;               //Quantidade de determinado produto para determinada venda
    private int    cod_pagamento;            //Codigo de pagamento
    private int    cod_condicao_pagamento;   //Codigo da condição de pagamento
    private Double desconto_venda;           //Caso aja desconto na venda, inserir o valor
    private Double valor_total_bruto_venda;  //Valor total bruto sem descontos
    private Double valor_total_liquido_venda;//Valor total liquido com descontos
    private Double juros_venda;              //Se caso aja juros na venda, inserir o valor de juros
    private double valor_entregue_venda;     //Caso o cliente pague a vista, inserir o valor para o calculo de troco
    private double troco_venda;              //Resultado do calculo de troco 
    private double valor_entrada;            //Caso a venda seja parcelada e o cliente der entrada de pagamento
    private int    cod_vendedor;             //Vendedor que efetuou a venda
    private int    cod_usuario;              //Usuario que efetuou a venda

    public Venda() {
    }

    public Venda(int cod_venda, int cod_caixa, int cod_cliente, int cod_rastreio_produto, String data_venda, String hora_venda, int quantidade, int cod_pagamento, int cod_condicao_pagamento, Double desconto_venda, Double valor_total_bruto_venda, Double valor_total_liquido_venda, Double juros_venda, double valor_entregue_venda, double troco_venda, double valor_entrada, int cod_vendedor, int cod_usuario) {
        this.cod_venda = cod_venda;
        this.cod_caixa = cod_caixa;
        this.cod_cliente = cod_cliente;
        this.cod_rastreio_produto = cod_rastreio_produto;
        this.data_venda = data_venda;
        this.hora_venda = hora_venda;
        this.quantidade = quantidade;
        this.cod_pagamento = cod_pagamento;
        this.cod_condicao_pagamento = cod_condicao_pagamento;
        this.desconto_venda = desconto_venda;
        this.valor_total_bruto_venda = valor_total_bruto_venda;
        this.valor_total_liquido_venda = valor_total_liquido_venda;
        this.juros_venda = juros_venda;
        this.valor_entregue_venda = valor_entregue_venda;
        this.troco_venda = troco_venda;
        this.valor_entrada = valor_entrada;
        this.cod_vendedor = cod_vendedor;
        this.cod_usuario = cod_usuario;
    }

    public int getCod_venda() {
        return cod_venda;
    }

    public void setCod_venda(int cod_venda) {
        this.cod_venda = cod_venda;
    }

    public int getCod_caixa() {
        return cod_caixa;
    }

    public void setCod_caixa(int cod_caixa) {
        this.cod_caixa = cod_caixa;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public int getCod_rastreio_produto() {
        return cod_rastreio_produto;
    }

    public void setCod_rastreio_produto(int cod_rastreio_produto) {
        this.cod_rastreio_produto = cod_rastreio_produto;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public String getHora_venda() {
        return hora_venda;
    }

    public void setHora_venda(String hora_venda) {
        this.hora_venda = hora_venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCod_pagamento() {
        return cod_pagamento;
    }

    public void setCod_pagamento(int cod_pagamento) {
        this.cod_pagamento = cod_pagamento;
    }

    public int getCod_condicao_pagamento() {
        return cod_condicao_pagamento;
    }

    public void setCod_condicao_pagamento(int cod_condicao_pagamento) {
        this.cod_condicao_pagamento = cod_condicao_pagamento;
    }

    public Double getDesconto_venda() {
        return desconto_venda;
    }

    public void setDesconto_venda(Double desconto_venda) {
        this.desconto_venda = desconto_venda;
    }

    public Double getValor_total_bruto_venda() {
        return valor_total_bruto_venda;
    }

    public void setValor_total_bruto_venda(Double valor_total_bruto_venda) {
        this.valor_total_bruto_venda = valor_total_bruto_venda;
    }

    public Double getValor_total_liquido_venda() {
        return valor_total_liquido_venda;
    }

    public void setValor_total_liquido_venda(Double valor_total_liquido_venda) {
        this.valor_total_liquido_venda = valor_total_liquido_venda;
    }

    public Double getJuros_venda() {
        return juros_venda;
    }

    public void setJuros_venda(Double juros_venda) {
        this.juros_venda = juros_venda;
    }

    public double getValor_entregue_venda() {
        return valor_entregue_venda;
    }

    public void setValor_entregue_venda(double valor_entregue_venda) {
        this.valor_entregue_venda = valor_entregue_venda;
    }

    public double getTroco_venda() {
        return troco_venda;
    }

    public void setTroco_venda(double troco_venda) {
        this.troco_venda = troco_venda;
    }

    public double getValor_entrada() {
        return valor_entrada;
    }

    public void setValor_entrada(double valor_entrada) {
        this.valor_entrada = valor_entrada;
    }

    public int getCod_vendedor() {
        return cod_vendedor;
    }

    public void setCod_vendedor(int cod_vendedor) {
        this.cod_vendedor = cod_vendedor;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }
    
    
    
}
