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
public class ComissaoVendedor {
    private int cod_comissao;  //Chave estreangeira tabela comissao
    private int cod_vendedor;  //Chave Estrangeira tabela vendedor
    private int cod_venda;     //Chave estrangeira tabela venda

    public ComissaoVendedor() {
    }

    public ComissaoVendedor(int cod_comissao, int cod_vendedor, int cod_venda) {
        this.cod_comissao = cod_comissao;
        this.cod_vendedor = cod_vendedor;
        this.cod_venda = cod_venda;
    }

    public int getCod_comissao() {
        return cod_comissao;
    }

    public void setCod_comissao(int cod_comissao) {
        this.cod_comissao = cod_comissao;
    }

    public int getCod_vendedor() {
        return cod_vendedor;
    }

    public void setCod_vendedor(int cod_vendedor) {
        this.cod_vendedor = cod_vendedor;
    }

    public int getCod_venda() {
        return cod_venda;
    }

    public void setCod_venda(int cod_venda) {
        this.cod_venda = cod_venda;
    }
    
    
    
}
