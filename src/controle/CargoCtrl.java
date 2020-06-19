/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.CargoDao;
import modelo.Cargo;

/**
 *
 * @author Guilherme
 */
public class CargoCtrl {
    public int SalvarCargoCtrl(Cargo cargo){
        return new CargoDao().SalvarCargo(cargo);
    }
    public int ExcluirCargoCtrl(int cod_cargo){
        return new CargoDao().ExcluirCargo(cod_cargo);
    }
    public int AtualizarCargoCtrl(Cargo cargo){
        return new CargoDao().AtualizarCargo(cargo);
    }
}
