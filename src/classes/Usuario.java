
package classes;

import java.util.Date;

public class Usuario {
    private int id;
    private String nome;
    private String rua;
    private int numero;
    private String bairro;
    private String cep;
    private String telefone_res;
    private String telefone_cel;
    private String cpf;
    private String rg;
    private String data_nasc;
    private String usuario;
    private String senha;
    private String email;
    private String data_inc;
    private int status;
    private String cidade;
    private String estado;
    private String data_alt;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone_res() {
        return telefone_res;
    }

    public void setTelefone_res(String telefone_res) {
        this.telefone_res = telefone_res;
    }

    public String getTelefone_cel() {
        return telefone_cel;
    }

    public void setTelefone_cel(String telefone_cel) {
        this.telefone_cel = telefone_cel;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getData_inc() {
        return data_inc;
    }

    public void setData_inc(String data_inc) {
        this.data_inc = data_inc;
    }

    public String getData_alt() {
        return data_alt;
    }

    public void setData_alt(String data_alt) {
        this.data_alt = data_alt;
    }
    
}
