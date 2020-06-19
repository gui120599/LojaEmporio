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
public class CondicaoPagamento {
    private int    cod_condicao_pagamento;      //Chave primária
    private String desc_condicao_pagamento;     //Nome para a condição de pagamento
    private int    quantidade_parcelas;         //Quantidade de vezes que essa condição terá
    private int    dias_prazo_primeira_parcela; //Quando a parcela irá vencer
    private int    dias_prazo_entre_parcelas;   //Quando a segunda e as demais parcelas irá vencer

    public CondicaoPagamento() {
    }

    public CondicaoPagamento(int cod_condicao_pagamento, String desc_condicao_pagamento, int quantidade_parcelas, int dias_prazo_primeira_parcela, int dias_prazo_entre_parcelas) {
        this.cod_condicao_pagamento = cod_condicao_pagamento;
        this.desc_condicao_pagamento = desc_condicao_pagamento;
        this.quantidade_parcelas = quantidade_parcelas;
        this.dias_prazo_primeira_parcela = dias_prazo_primeira_parcela;
        this.dias_prazo_entre_parcelas = dias_prazo_entre_parcelas;
    }

    public int getCod_condicao_pagamento() {
        return cod_condicao_pagamento;
    }

    public void setCod_condicao_pagamento(int cod_condicao_pagamento) {
        this.cod_condicao_pagamento = cod_condicao_pagamento;
    }

    public String getDesc_condicao_pagamento() {
        return desc_condicao_pagamento;
    }

    public void setDesc_condicao_pagamento(String desc_condicao_pagamento) {
        this.desc_condicao_pagamento = desc_condicao_pagamento;
    }

    public int getQuantidade_parcelas() {
        return quantidade_parcelas;
    }

    public void setQuantidade_parcelas(int quantidade_parcelas) {
        this.quantidade_parcelas = quantidade_parcelas;
    }

    public int getDias_prazo_primeira_parcela() {
        return dias_prazo_primeira_parcela;
    }

    public void setDias_prazo_primeira_parcela(int dias_prazo_primeira_parcela) {
        this.dias_prazo_primeira_parcela = dias_prazo_primeira_parcela;
    }

    public int getDias_prazo_entre_parcelas() {
        return dias_prazo_entre_parcelas;
    }

    public void setDias_prazo_entre_parcelas(int dias_prazo_entre_parcelas) {
        this.dias_prazo_entre_parcelas = dias_prazo_entre_parcelas;
    }
    
}
