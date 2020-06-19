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
public class Cliente {
    private int    cod_cliente;
    private String nome_cliente;
    private String data_nascimento;
    private String cpf_cliente;
    private String cnpj_cliente;
    private String rg_cliente;
    private String telefone_1;
    private String whatsApp_cliente;
    private String email_cliente;
    private String cep_cliente;
    private int    cod_uf;
    private int    cod_cidade;
    private int    cod_bairro;
    private String endereco_cliente;
    private String tipo_pessoa;
    private Boolean status_cliente;
    private String data_cadastro_cliente;
    private int    cod_usuario_cadastro;

    public Cliente() {
    }

    public Cliente(int cod_cliente, String nome_cliente, String data_nascimento, String cpf_cliente, String cnpj_cliente, String rg_cliente, String telefone_1, String whatsApp_cliente, String email_cliente, String cep, String numero_endereço, int cod_uf, int cod_cidade, int cod_bairro, String complemento, String endereco_cliente, String tipo_pessoa, Boolean status_cliente, String data_cadastro_cliente, int cod_usuario_cadastro) {
        this.cod_cliente = cod_cliente;
        this.nome_cliente = nome_cliente;
        this.data_nascimento = data_nascimento;
        this.cpf_cliente = cpf_cliente;
        this.cnpj_cliente = cnpj_cliente;
        this.rg_cliente = rg_cliente;
        this.telefone_1 = telefone_1;
        this.whatsApp_cliente = whatsApp_cliente;
        this.email_cliente = email_cliente;
        this.cep_cliente = cep;
        this.cod_uf = cod_uf;
        this.cod_cidade = cod_cidade;
        this.cod_bairro = cod_bairro;
        this.endereco_cliente = endereco_cliente;
        this.tipo_pessoa = tipo_pessoa;
        this.status_cliente = status_cliente;
        this.data_cadastro_cliente = data_cadastro_cliente;
        this.cod_usuario_cadastro = cod_usuario_cadastro;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public String getCnpj_cliente() {
        return cnpj_cliente;
    }

    public void setCnpj_cliente(String cnpj_cliente) {
        this.cnpj_cliente = cnpj_cliente;
    }

    public String getRg_cliente() {
        return rg_cliente;
    }

    public void setRg_cliente(String rg_cliente) {
        this.rg_cliente = rg_cliente;
    }

    public String getTelefone_1() {
        return telefone_1;
    }

    public void setTelefone_1(String telefone_1) {
        this.telefone_1 = telefone_1;
    }

    public String getWhatsApp_cliente() {
        return whatsApp_cliente;
    }

    public void setWhatsApp_cliente(String whatsApp_cliente) {
        this.whatsApp_cliente = whatsApp_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getCep_cliente() {
        return cep_cliente;
    }

    public void setCep_cliente(String cep_cliente) {
        this.cep_cliente = cep_cliente;
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

    public String getEndereco_cliente() {
        return endereco_cliente;
    }

    public void setEndereco_cliente(String endereco_cliente) {
        this.endereco_cliente = endereco_cliente;
    }

    public String getTipo_pessoa() {
        return tipo_pessoa;
    }

    public void setTipo_pessoa(String tipo_pessoa) {
        this.tipo_pessoa = tipo_pessoa;
    }

    public Boolean getStatus_cliente() {
        return status_cliente;
    }

    public void setStatus_cliente(Boolean status_cliente) {
        this.status_cliente = status_cliente;
    }

    public String getData_cadastro_cliente() {
        return data_cadastro_cliente;
    }

    public void setData_cadastro_cliente(String data_cadastro_cliente) {
        this.data_cadastro_cliente = data_cadastro_cliente;
    }

    public int getCod_usuario_cadastro() {
        return cod_usuario_cadastro;
    }

    public void setCod_usuario_cadastro(int cod_usuario_cadastro) {
        this.cod_usuario_cadastro = cod_usuario_cadastro;
    }

}
