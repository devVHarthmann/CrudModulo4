/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.DAO;

import br.ulbra.model.Fornecedor;
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
public class FornecedorDAO extends AbstractDAO implements CrudRepository<Fornecedor> {

    @Override
    public void salvar(Fornecedor f) throws SQLException {
        String sql = "INSERT INTO tbfornecedor (nomeFantasia, nomeRazao, telefone, email) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, f.getNomeFantasia());
            ps.setString(2, f.getNomeRazao());
            ps.setString(3, f.getTelefone());
            ps.setString(4, f.getEmail());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Falha ao inserir fornecedor.");
            } else {

            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    f.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Fornecedor buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nomeFantasia, nomeRazao, telefone, email FROM tbfornecedor WHERE id = ?";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Fornecedor(
                            rs.getInt("id"),
                            rs.getString("nomeFantasia"),
                            rs.getString("nomeRazao"),
                            rs.getString("telefone"),
                            rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Fornecedor> listar() throws SQLException {
        String sql = "SELECT id, nomeFantasia, nomeRazao, telefone, email FROM tbfornecedor ORDER by id";
        List<Fornecedor> lista = new ArrayList<>();
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Fornecedor f = new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nomeFantasia"),
                        rs.getString("nomeRazao"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );
                lista.add(f);

            }
        }
        return lista;
    }

    @Override
    public void atualizar(Fornecedor f) throws SQLException {
        String sql = "UPDATE tbfornecedor set nomeFantasia = ?, nomeRazao = ?, telefone = ?, email = ? WHERE id = ?";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getNomeFantasia());
            ps.setString(2, f.getNomeRazao());
            ps.setString(3, f.getTelefone());
            ps.setString(4, f.getEmail());
            ps.setInt(4, f.getId());
            ps.executeUpdate();

        }
    }

    @Override
    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM tbFornecedor WHERE id= ?";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    }


