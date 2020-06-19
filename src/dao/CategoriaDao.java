/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import javax.swing.JOptionPane;
import modelo.Categoria;

/**
 *
 * @author Guilherme
 */
public class CategoriaDao {
    public int SalvarCategoria(Categoria categoria){
        Conexao c = new Conexao("MySql", "localhost", "3357", "LojaEmporio", "root", "");
        try{
            c.conectar();
            String sql = "INSERT INTO categoria(desc_categoria)VALUES('"+categoria.getDesc_categoria()+"');";
            //String sqlUltimo ="SELECT MAX (cod_categoria)FROM categoria;";
            JOptionPane.showMessageDialog(null,"Categoria salva com sucesso!!");
            return c.queryIncluir(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao salvar!!/n"+ e);
            return 0;
        }finally{
            c.desconectar();
        }
    }
    public int ExcluirCategoria(int cod_categoria){
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao("MySql", "localhost", "3357", "LojaEmporio", "root", "");
        try{
            c.conectar();
            String sql = "DELETE FROM categoria WHERE cod_categoria = '"+cod_categoria+"';";
            JOptionPane.showMessageDialog(null,"Caategoria excluida com sucesso!!");
            qtdRegistrosAfetados = c.queryDelete(sql);
            return qtdRegistrosAfetados;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao excluir!!/n"+ e);
            return qtdRegistrosAfetados;
        }finally{
            c.desconectar();
        }
    }
    public int AtualizarCategoria(Categoria categoria){
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao("MySql", "localhost", "3357", "LojaEmporio", "root", "");
        try{
            c.conectar();
            String sql="UPDATE categoria"
                    + "SET desc_categoria = '"+categoria.getDesc_categoria()+"'"
                    + "WHERE desc_categoria = '"+categoria.getCod_categoria()+"';";
            JOptionPane.showMessageDialog(null,"Categoria atualizado com sucesso!!");
            qtdRegistrosAfetados = c.queryUpdate(sql);
            return qtdRegistrosAfetados;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao atualizar!!"+ e);
            return qtdRegistrosAfetados;
        }finally{
            c.desconectar();
        }
    }
}
