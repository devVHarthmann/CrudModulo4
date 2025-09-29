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
public class Produto {
    private int id;
    private String nomeProduto;
    private int idFor;
    private int quantidade;
    private double valorUnitario;
    
    public Produto(){
        
    }

    public Produto(int id, String nomeProduto, int idFor, int quantidade, double valorUnitario) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.idFor = idFor;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getIdFor() {
        return idFor;
    }

    public void setIdFor(int idFor) {
        this.idFor = idFor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    
    @Override
    public String toString() {
        return "Cód: " + id + "Nome Produto: " + nomeProduto + "ID Fornecedor:" + idFor + "Quantidade: " + quantidade + "Valor unitário: " + valorUnitario;
    }
}
