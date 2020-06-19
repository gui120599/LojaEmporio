/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.BairroDao;
import modelo.Bairro;

/**
 *
 * @author Guilherme
 */
public class BairroCtrl {
    public int SalvarCtlrBairro(Bairro bairro){
        return new BairroDao().SalvarBairro(bairro);
    }
    public int ExcluirCtrlBairro(int cod_bairro){
        return new BairroDao().ExcluirBairro(cod_bairro);
    }
    public int AtualizarCtrlBairro(Bairro bairro){
        return new BairroDao().AtualizarBairro(bairro);
    }
}
