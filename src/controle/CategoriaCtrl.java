/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.CategoriaDao;
import modelo.Categoria;

/**
 *
 * @author Guilherme
 */
public class CategoriaCtrl {
    public int SalvarCategoriaCtrl(Categoria categoria){
        return new CategoriaDao().SalvarCategoria(categoria);
    }
    public int ExcluirCategoriaCtrl(int cod_categoria){
        return new CategoriaDao().ExcluirCategoria(cod_categoria);
    }
    public int AtualizarCatergoriaCtrl(Categoria categoria){
        return new CategoriaDao().AtualizarCategoria(categoria);
    }
}
