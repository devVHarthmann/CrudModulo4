/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.DAO;

import br.ulbra.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno.saolucas
 */
public class ProdutoDAO extends AbstractDAO implements CrudRepository<Produto>{

    @Override
    public void salvar(Produto p) throws SQLException {
        String sql = "INSERT INTO tbproduto (nomeProduto, idFor, quantidade, valorUnitario) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNomeProduto());
            ps.setInt(2, p.getIdFor());
            ps.setInt(3, p.getQuantidade());
            ps.setDouble(4, p.getValorUnitario());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Falha ao inserir fornecedor.");
            } else {

            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Produto buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nomeProduto, idFor, quantidade, valorUnitario FROM tbproduto WHERE id = ?";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Produto(
                            rs.getInt("id"),
                            rs.getString("nomeProduto"),
                            rs.getInt("idFor"),
                            rs.getInt("quantidade"),
                            rs.getDouble("valorUnitario")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Produto> listar() throws SQLException {
 String sql = "SELECT id, nomeProduto, idFor, quantidade, valorUnitario FROM tbproduto ORDER by id";
        List<Produto> lista = new ArrayList<>();
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nomeProduto"),
                        rs.getInt("iFor"),
                        rs.getInt("quantidade"),
                        rs.getDouble("valorUnitario"));
                lista.add(p);

            }
        }
        return lista;   
    }

    @Override
    public void atualizar(Produto p) throws SQLException {
        String sql = "UPDATE tbproduto set nomeProduto = ?, idFor = ?, quantidade = ?, valorUnitario = ? WHERE id = ?";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNomeProduto());
            ps.setInt(2, p.getIdFor());
            ps.setInt(3, p.getQuantidade());
            ps.setDouble(4, p.getValorUnitario());
            ps.setInt(5, p.getId());
            ps.executeUpdate();

        }
    }

    @Override
    public void remover(int id) throws SQLException {
                String sql = "DELETE FROM tbProduto WHERE id= ?";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    
}
