/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Date;

/**
 *
 * @author kairi
 */
public class Pessoa {
    
    private int id;
    private String nome;
    private int telefone;
    private Date data;
    private int endereco;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, int telefone, Date data, int endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.data = data;
        this.endereco = endereco;
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

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }
    
   
    
}
