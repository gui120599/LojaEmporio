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
public class Fornecedor {
    private int    cod_fornecedor;
    private String nome_fornecedor;
    private String razão_social;
    private String cnpj;
    private String telefone_1;
    private String whatsApp;
    private String email;
    private String cep;
    private String endereço;
    private int    cod_uf;
    private int    cod_cidade;
    private int    cod_bairro;
    private String complemento;
    private String inscricao_estadual;
    private String inscricao_municipal;
    private Boolean status_fornecedor;       //ativo ou inativo
    private String data_cadastro_fornecedor;
    private int    cod_usuario_cadastro;

    public Fornecedor() {
    }

    public Fornecedor(int cod_fornecedor, String nome_fornecedor, String razão_social, String cnpj, String telefone_1, String whatsApp, String email, String cep, String endereço, int cod_uf, int cod_cidade, int cod_bairro, String complemento, String inscricao_estadual, String inscricao_municipal, Boolean status_fornecedor, String data_cadastro_fornecedor, int cod_usuario_cadastro) {
        this.cod_fornecedor = cod_fornecedor;
        this.nome_fornecedor = nome_fornecedor;
        this.razão_social = razão_social;
        this.cnpj = cnpj;
        this.telefone_1 = telefone_1;
        this.whatsApp = whatsApp;
        this.email = email;
        this.cep = cep;
        this.endereço = endereço;
        this.cod_uf = cod_uf;
        this.cod_cidade = cod_cidade;
        this.cod_bairro = cod_bairro;
        this.complemento = complemento;
        this.inscricao_estadual = inscricao_estadual;
        this.inscricao_municipal = inscricao_municipal;
        this.status_fornecedor = status_fornecedor;
        this.data_cadastro_fornecedor = data_cadastro_fornecedor;
        this.cod_usuario_cadastro = cod_usuario_cadastro;
    }

    public int getCod_fornecedor() {
        return cod_fornecedor;
    }

    public void setCod_fornecedor(int cod_fornecedor) {
        this.cod_fornecedor = cod_fornecedor;
    }

    public String getNome_fornecedor() {
        return nome_fornecedor;
    }

    public void setNome_fornecedor(String nome_fornecedor) {
        this.nome_fornecedor = nome_fornecedor;
    }

    public String getRazão_social() {
        return razão_social;
    }

    public void setRazão_social(String razão_social) {
        this.razão_social = razão_social;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone_1() {
        return telefone_1;
    }

    public void setTelefone_1(String telefone_1) {
        this.telefone_1 = telefone_1;
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

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

    public String getInscricao_municipal() {
        return inscricao_municipal;
    }

    public void setInscricao_municipal(String inscricao_municipal) {
        this.inscricao_municipal = inscricao_municipal;
    }

    public Boolean getStatus_fornecedor() {
        return status_fornecedor;
    }

    public void setStatus_fornecedor(Boolean status_fornecedor) {
        this.status_fornecedor = status_fornecedor;
    }

    public String getData_cadastro_fornecedor() {
        return data_cadastro_fornecedor;
    }

    public void setData_cadastro_fornecedor(String data_cadastro_fornecedor) {
        this.data_cadastro_fornecedor = data_cadastro_fornecedor;
    }

    public int getCod_usuario_cadastro() {
        return cod_usuario_cadastro;
    }

    public void setCod_usuario_cadastro(int cod_usuario_cadastro) {
        this.cod_usuario_cadastro = cod_usuario_cadastro;
    }
    
}
