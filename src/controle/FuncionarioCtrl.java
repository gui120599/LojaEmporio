/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.FuncionarioDao;
import modelo.Funcionario;

/**
 *
 * @author Suporte T.I
 */
public class FuncionarioCtrl {
    public int SalvarFuncionarioCtrl(Funcionario Funcionario){
        return new FuncionarioDao().SalvarFuncionario(Funcionario);
    }
    /*public int ExcluirFuncionarioCtrl(int cod_Funcionario){
        return new FuncionarioDao().ExcluirFuncionario(cod_Funcionario);
    }
    public int AtualizarFuncionarioCtrl(Funcionario Funcionario){
        return new FuncionarioDao().AtualizarFuncionario(Funcionario);
    }*/
}
