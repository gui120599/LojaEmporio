/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Guilherme
 */
public class Funcionario {

    private int cod_funcionario;
    private int cod_cargo;
    private String nome_funcionario;
    private String data_nascimento_func;
    private String cpf_func;
    private String rg_func;
    private String sexo;
    private String estado_civil_func;
    private String telefone;
    private String whatsApp;
    private String email;
    private String cep;
    private int cod_uf;
    private int cod_cidade;
    private int cod_bairro;
    private String endereco;
    private Boolean status_func;
    private String data_cadastro_func;
    private int cod_usuario_cadastro;
    private String desc_cargo;

    public Funcionario() {
    }

    public Funcionario(int cod_funcionario, int cod_cargo, String nome_funcionario, String data_nascimento_func, String cpf_func, String rg_func, String sexo, String estado_civil_func, String telefone, String whatsApp, String email, String cep, int cod_uf, int cod_cidade, int cod_bairro, String endereco, Boolean status_func, String data_cadastro_func, int cod_usuario_cadastro, String desc_cargo) {
        this.cod_funcionario = cod_funcionario;
        this.cod_cargo = cod_cargo;
        this.nome_funcionario = nome_funcionario;
        this.data_nascimento_func = data_nascimento_func;
        this.cpf_func = cpf_func;
        this.rg_func = rg_func;
        this.sexo = sexo;
        this.estado_civil_func = estado_civil_func;
        this.telefone = telefone;
        this.whatsApp = whatsApp;
        this.email = email;
        this.cep = cep;
        this.cod_uf = cod_uf;
        this.cod_cidade = cod_cidade;
        this.cod_bairro = cod_bairro;
        this.endereco = endereco;
        this.status_func = status_func;
        this.data_cadastro_func = data_cadastro_func;
        this.cod_usuario_cadastro = cod_usuario_cadastro;
        this.desc_cargo = desc_cargo;
    }

    public int getCod_funcionario() {
        return cod_funcionario;
    }

    public void setCod_funcionario(int cod_funcionario) {
        this.cod_funcionario = cod_funcionario;
    }

    public int getCod_cargo() {
        return cod_cargo;
    }

    public void setCod_cargo(int cod_cargo) {
        this.cod_cargo = cod_cargo;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getData_nascimento_func() {
        return data_nascimento_func;
    }

    public void setData_nascimento_func(String data_nascimento_func) {
        this.data_nascimento_func = data_nascimento_func;
    }

    public String getCpf_func() {
        return cpf_func;
    }

    public void setCpf_func(String cpf_func) {
        this.cpf_func = cpf_func;
    }

    public String getRg_func() {
        return rg_func;
    }

    public void setRg_func(String rg_func) {
        this.rg_func = rg_func;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstado_civil_func() {
        return estado_civil_func;
    }

    public void setEstado_civil_func(String estado_civil_func) {
        this.estado_civil_func = estado_civil_func;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getCod_uf() {
        return cod_uf;
    }

    public void setCod_uf(int cod_uf) {
        this.cod_uf = cod_uf;
    }

    public int getCod_cidade() {
        return cod_cidade;
    }

    public void setCod_cidade(int cod_cidade) {
        this.cod_cidade = cod_cidade;
    }

    public int getCod_bairro() {
        return cod_bairro;
    }

    public void setCod_bairro(int cod_bairro) {
        this.cod_bairro = cod_bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getStatus_func() {
        return status_func;
    }

    public void setStatus_func(Boolean status_func) {
        this.status_func = status_func;
    }

    public String getData_cadastro_func() {
        return data_cadastro_func;
    }

    public void setData_cadastro_func(String data_cadastro_func) {
        this.data_cadastro_func = data_cadastro_func;
    }

    public int getCod_usuario_cadastro() {
        return cod_usuario_cadastro;
    }

    public void setCod_usuario_cadastro(int cod_usuario_cadastro) {
        this.cod_usuario_cadastro = cod_usuario_cadastro;
    }

    public String getDesc_cargo() {
        return desc_cargo;
    }

    public void setDesc_cargo(String desc_cargo) {
        this.desc_cargo = desc_cargo;
    }

}
