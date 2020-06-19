/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.UsuarioDao;
import modelo.Usuario;

/**
 *
 * @author Guilherme
 */
public class UsuarioCtrl {
    public int SalvarUsuarioCtrl(Usuario usuario){
        return new UsuarioDao().SalvarUsuario(usuario);
    }
}
