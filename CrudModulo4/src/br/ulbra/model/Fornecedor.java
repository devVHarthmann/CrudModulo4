/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.model;

/**
 *
 * @author aluno.saolucas
 */
public class Fornecedor {

    private int id;
    private String nomeFantasia;
    private String nomeRazao;
    private String telefone;
    private String email;

    public Fornecedor() {

    }

    public Fornecedor(int id, String nomeFantasia, String nomeRazao, String telefone, String email) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.nomeRazao = nomeRazao;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getNomeRazao() {
        return nomeRazao;
    }

    public void setNomeRazao(String nomeRazao) {
        this.nomeRazao = nomeRazao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cód: " + id + "Nome Fantasia: " + nomeFantasia + "Nome Razão:" + nomeRazao + "Telefone: " + telefone + "Email: " + email;
    }

}
