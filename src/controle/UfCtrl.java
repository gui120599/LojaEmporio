/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.UfDao;
import modelo.Uf;

/**
 *
 * @author Guilherme
 */
public class UfCtrl {
    public int SalvarUfCtrl(Uf uf) {
        return new UfDao().SalvarUf(uf);
    }
    public int ExcluirUfCtrl(int cod_uf){
        return new UfDao().ExcluirUf(cod_uf);
    }
    public int AtualizarUfCtrl(Uf uf){
        return new UfDao().AtualizarUf(uf);
    }
}
