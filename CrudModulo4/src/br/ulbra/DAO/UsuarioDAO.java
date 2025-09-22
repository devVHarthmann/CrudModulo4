/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.DAO;

import br.ulbra.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno.saolucas
 */
public class UsuarioDAO extends AbstractDAO implements CrudRepository<Usuario>{
    
    @Override
    public void salvar(Usuario u) throws SQLException {
        String sql = "INSERT INTO tbcliente (nome, email, telefone) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getLogin());
            ps.setString(3, u.getSenha());
            ps.setBoolean(4, u.getAtivo());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Falha ao inserir cliente.");
            }else{
                
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    u.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nome, login, senha, ativo FROM tbusuario WHERE id = ?";
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getBoolean("ativo")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        String sql = "SELECT id, nome, login, ativo FROM tbcliente ORDER by id";
        List<Usuario> lista = new ArrayList<>();
        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("login"),
                        rs.getBoolean("ativo"));
                lista.add(u);

            }
        }
        return lista;
    }

    @Override
    public void atualizar(Usuario obj) throws SQLException {
        
    }

    @Override
    public void remover(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
