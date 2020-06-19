/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.LoginSessaoDao;
import modelo.LoginSessao;

/**
 *
 * @author Guilherme
 */
public class LoginSessaoCtrl {
    public int AbrirSessao(LoginSessao l){
        return new LoginSessaoDao().AbrirSessao(l);
    }
}
