/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.CidadeDao;
import modelo.Cidade;

/**
 *
 * @author Guilherme
 */
public class CidadeCtrl {
    public int SalvarCtrlCidade(Cidade cidade){
        return new CidadeDao().SalvarCidade(cidade);
    }
    public int ExcluirCtrlCidade(int cod_cidade){
        return new CidadeDao().ExcluirCidade(cod_cidade);
    }
    public int AtualizarCtrlCidade(Cidade cidade){
        return new CidadeDao().AtualizarCidade(cidade);
    }
}
