/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import modelo.Bairro;

/**
 *
 * @author Guilherme
 */
public class BairroDao {
    public int SalvarBairro(Bairro bairro){
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try{
            c.conectar();
            String sql="INSERT INTO bairro(nome_bairro,cod_cidade)VALUES('"+bairro.getDesc_bairro()+"','"+bairro.getCod_cidade()+"');";
            JOptionPane.showMessageDialog(null,"Bairro salvo com sucesso!!");
            //String sqlUltimo="SELECT MAX (desc_bairro)FROM bairro;";
            return c.queryIncluir(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao salvar/n"+ e);
            return 0;
        }finally{
            c.desconectar();
        }
    }
    
    public int ExcluirBairro(int cod_bairro){
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try{
            c.conectar();
            String sql="DELETE FROM bairro WHERE cod_bairro='"+cod_bairro+"';";
            JOptionPane.showMessageDialog(null,"Bairro excluido com sucesso!!");
            qtdRegistrosAfetados = c.queryDelete(sql);
            return qtdRegistrosAfetados;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao excluir!!/n"+ e);
            return qtdRegistrosAfetados;
        }finally{
            c.desconectar();
        }
    }
    
    public int AtualizarBairro(Bairro bairro){
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao("MySql", "localhost", "3306", "LojaEmporio", "root", "");
        try{
            c.conectar();
            String sql = "UPDATE bairro "
                    + "SET nome_bairro = '"+bairro.getDesc_bairro()+"',"
                    + "cod_cidade = '"+bairro.getCod_cidade()+"'"
                    + "WHERE cod_bairro = '"+bairro.getCod_bairro()+"';";
            JOptionPane.showMessageDialog(null,"Bairro atualizado com sucesso!!");
            qtdRegistrosAfetados = c.queryUpdate(sql);
            return qtdRegistrosAfetados;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao atualizar!!/n"+ e);
            return qtdRegistrosAfetados;
        }finally{
            c.desconectar();
        }
    }
    public Collection<Bairro> MostrarBairro() {
        Collection<Bairro> bairros = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();
            
            String sql = "SELECT * FROM bairro,cidade WHERE bairro.cod_cidade = cidade.cod_cidade ORDER BY 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Bairro b = new Bairro();
                b.setCod_bairro(c.getResultSet().getInt("cod_bairro"));
                b.setDesc_bairro(c.getResultSet().getString("nome_bairro"));
                b.setCod_cidade(c.getResultSet().getInt("cod_cidade"));
                b.setNome_cidade(c.getResultSet().getString("nome_cidade"));
                bairros.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return bairros;
        } finally {
            c.desconectar();
        }
        return bairros;
    }
    public Collection<Bairro> MostrarBairroCodigo(int codigo) {
        Collection<Bairro> bairros = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();
            
            String sql = "SELECT * FROM bairro,cidade WHERE cod_bairro = '"+codigo+"' AND bairro.cod_cidade = cidade.cod_cidade ORDER BY 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Bairro b = new Bairro();
                b.setCod_bairro(c.getResultSet().getInt("cod_bairro"));
                b.setDesc_bairro(c.getResultSet().getString("nome_bairro"));
                b.setCod_cidade(c.getResultSet().getInt("cod_cidade"));
                b.setNome_cidade(c.getResultSet().getString("nome_cidade"));
                bairros.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return bairros;
        } finally {
            c.desconectar();
        }
        return bairros;
    }
    public Collection<Bairro> MostrarBairroNome(String nome) {
        Collection<Bairro> bairros = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();
            
            String sql = "SELECT * FROM bairro,cidade WHERE nome_bairro LIKE '%"+nome+"%' AND bairro.cod_cidade = cidade.cod_cidade ORDER BY 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Bairro b = new Bairro();
                b.setCod_bairro(c.getResultSet().getInt("cod_bairro"));
                b.setDesc_bairro(c.getResultSet().getString("nome_bairro"));
                b.setCod_cidade(c.getResultSet().getInt("cod_cidade"));
                b.setNome_cidade(c.getResultSet().getString("nome_cidade"));
                bairros.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return bairros;
        } finally {
            c.desconectar();
        }
        return bairros;
    }
}
