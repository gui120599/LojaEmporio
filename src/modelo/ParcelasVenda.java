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
public class ParcelasVenda {
    private int     cod_parcela;              //Chave primária da tabela Parcelas Venda
    private int     cod_venda;                //Chave Estrangeira da tabela venda
    private double  valor_parcela;            //Valor que será divido pelo o software
    private String  data_vencimento_parcela;  //Datas de vencimento tentar gerar pelo o software
    private boolean status_pagamento;         //Se está pago ou não pago
    private String  desc_status_pagamento;    //Nome do status de pagamento pago ou não pago
    private String  data_pagamento;           //Data que efetuaram o pagamento

    public ParcelasVenda() {
    }

    public ParcelasVenda(int cod_parcela, int cod_venda, double valor_parcela, String data_vencimento_parcela, boolean status_pagamento, String desc_status_pagamento, String data_pagamento) {
        this.cod_parcela = cod_parcela;
        this.cod_venda = cod_venda;
        this.valor_parcela = valor_parcela;
        this.data_vencimento_parcela = data_vencimento_parcela;
        this.status_pagamento = status_pagamento;
        this.desc_status_pagamento = desc_status_pagamento;
        this.data_pagamento = data_pagamento;
    }

    public int getCod_parcela() {
        return cod_parcela;
    }

    public void setCod_parcela(int cod_parcela) {
        this.cod_parcela = cod_parcela;
    }

    public int getCod_venda() {
        return cod_venda;
    }

    public void setCod_venda(int cod_venda) {
        this.cod_venda = cod_venda;
    }

    public double getValor_parcela() {
        return valor_parcela;
    }

    public void setValor_parcela(double valor_parcela) {
        this.valor_parcela = valor_parcela;
    }

    public String getData_vencimento_parcela() {
        return data_vencimento_parcela;
    }

    public void setData_vencimento_parcela(String data_vencimento_parcela) {
        this.data_vencimento_parcela = data_vencimento_parcela;
    }

    public boolean isStatus_pagamento() {
        return status_pagamento;
    }

    public void setStatus_pagamento(boolean status_pagamento) {
        this.status_pagamento = status_pagamento;
    }

    public String getDesc_status_pagamento() {
        return desc_status_pagamento;
    }

    public void setDesc_status_pagamento(String desc_status_pagamento) {
        this.desc_status_pagamento = desc_status_pagamento;
    }

    public String getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }
    
    
}
