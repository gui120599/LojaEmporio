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
import modelo.Cargo;

/**
 *
 * @author Guilherme
 */
public class CargoDao {
    public int SalvarCargo(Cargo cargo){
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try{
            c.conectar();
            
            String sql ="INSERT INTO cargo(desc_cargo)VALUES('"+cargo.getDesc_cargo()+"');";
            
            JOptionPane.showMessageDialog(null,"Cargo salvo com sucesso!!");
            
            return c.queryIncluir(sql);
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Erro ao salvar!! \n"+ e);
            return 0;
        }finally{
            c.desconectar();
        }
    }
    public int ExcluirCargo(int cod_cargo){
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try{
            c.conectar();
            String sql = "DELETE FROM cargo WHERE cod_cargo = '"+cod_cargo+"';";
            JOptionPane.showMessageDialog(null,"Cargo excluido com sucesso!!");
            qtdRegistrosAfetados = c.queryDelete(sql);
            return qtdRegistrosAfetados;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao excluir!!\n"+ e);
            return qtdRegistrosAfetados;
        }finally{
            c.desconectar();
        }
    }
    public int AtualizarCargo(Cargo cargo){
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try{
            c.conectar();
            String sql = "UPDATE cargo  SET"
                    + " desc_cargo = '"+cargo.getDesc_cargo()+"' "
                    + "WHERE cod_cargo = "+cargo.getCod_cargo()+" ;";
            qtdRegistrosAfetados = c.queryUpdate(sql);
            JOptionPane.showMessageDialog(null,"Cargo Atualizado com sucesso!!");
            return qtdRegistrosAfetados;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao atualizar!! /n"+ e);
            return qtdRegistrosAfetados;
        }finally{
            c.desconectar();
        }
    }
    public Collection<Cargo> MostrarCargo() {
        Collection<Cargo> cargos = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();
            
            String sql = "SELECT * FROM cargo;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Cargo cargo = new Cargo();
                cargo.setCod_cargo(c.getResultSet().getInt("cod_cargo"));
                cargo.setDesc_cargo(c.getResultSet().getString("desc_cargo"));
                cargos.add(cargo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return cargos;
        } finally {
            c.desconectar();
        }
        return cargos;
    }
    public Collection<Cargo> BuscarCargoCodigo(int cod_cargo) {
        Collection<Cargo> cargos = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();
            
            String sql = "SELECT*FROM cargo WHERE cod_cargo = "+cod_cargo+";";

            c.query(sql);
            while (c.getResultSet().next()) {
                Cargo cargo = new Cargo();
                cargo.setCod_cargo(c.getResultSet().getInt("cod_cargo"));
                cargo.setDesc_cargo(c.getResultSet().getString("desc_cargo"));
                cargos.add(cargo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return cargos;
        } finally {
            c.desconectar();
        }
        return cargos;
    }
    public Collection<Cargo> BuscarCargoNome(String desc_cargo) {
        Collection<Cargo> cargos = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();
            
            String sql = "SELECT*FROM cargo WHERE desc_cargo LIKE '%"+desc_cargo+"%';";

            c.query(sql);
            while (c.getResultSet().next()) {
                Cargo cargo = new Cargo();
                cargo.setCod_cargo(c.getResultSet().getInt("cod_cargo"));
                cargo.setDesc_cargo(c.getResultSet().getString("desc_cargo"));
                cargos.add(cargo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return cargos;
        } finally {
            c.desconectar();
        }
        return cargos;
    }
    public Collection<Cargo> MostrarUltimoCargo() {
        Collection<Cargo> cargos = new ArrayList<>();
        Conexao c = new Conexao("MySql", "localhost", "3306", "lojaemporio", "root", "");
        try {
            c.conectar();
            
            String sql = "SELECT cod_cargo FROM cargo ORDER BY cod_cargo DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Cargo cargo = new Cargo();
                cargo.setCod_cargo(c.getResultSet().getInt("cod_cargo"));
                cargos.add(cargo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return cargos;
        } finally {
            c.desconectar();
        }
        return cargos;
    }
}
