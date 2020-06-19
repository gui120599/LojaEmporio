/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ClienteDao;
import modelo.Cliente;

/**
 *
 * @author Guilherme
 */
public class ClienteCtrl {
    public int SalvarClienteCtrl(Cliente cliente){
        return new ClienteDao().SalvarCliente(cliente);
    }
    public int ExcluirClienteCtrl(int cod_cliente){
        return new ClienteDao().ExcluirCliente(cod_cliente);
    }
    public int AtualizarClienteCtrl(Cliente cliente){
        return new ClienteDao().AtualizarCliente(cliente);
    }
}    
    
