/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kairi
 */
public class PessoaDao {

    public void insert(Pessoa pessoa) throws SQLException {
        Connection conexao = ConnectionFactory.getConexao();
        PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO pessoa(id, nome, telefone, data, endereco) VALUES (?, ?, ?, ?, ?);");
        pstmt.setInt(1, pessoa.getId());
        pstmt.setString(2, pessoa.getNome());
        pstmt.setInt(3, pessoa.getTelefone());
        pstmt.setDate(4, pessoa.getData());
        pstmt.setInt(5, pessoa.getEndereco());
        int resultado = pstmt.executeUpdate();
        pstmt.close();
    }

    public void update(Pessoa pessoa) throws SQLException {
        Connection conexao = ConnectionFactory.getConexao();
        PreparedStatement pstmt = conexao.prepareStatement("UPDATE pessoa SET nome = ?, telefone = ?, data = ?, endereco = ? WHERE id = ?;");
        pstmt.setString(1, pessoa.getNome());
        pstmt.setInt(2, pessoa.getTelefone());
        pstmt.setDate(3, pessoa.getData());
        pstmt.setInt(4, pessoa.getEndereco());
        pstmt.setInt(5, pessoa.getId());
        int resultado = pstmt.executeUpdate();
        pstmt.close();
    }

    public void delete(int id) throws SQLException {
        Connection conexao = ConnectionFactory.getConexao();
        PreparedStatement pstmt = conexao.prepareStatement("DELETE FROM pessoa WHERE id = ?;");
        pstmt.setInt(1, id);
        int resultado = pstmt.executeUpdate();
        pstmt.close();
    }

    public List<Pessoa> select() throws SQLException {
        Connection conexao = ConnectionFactory.getConexao();
        List<Pessoa> lista = new ArrayList();
        PreparedStatement pstmt = conexao.prepareStatement("SELECT * FROM pessoa;");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(rs.getInt("id"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setTelefone(rs.getInt("telefone"));
            pessoa.setData(rs.getDate("data"));
            pessoa.setEndereco(rs.getInt("endereco"));
            lista.add(pessoa);
        }
        return lista;
    }

    public Pessoa selectOne(int id) throws SQLException {
        Connection conexao = ConnectionFactory.getConexao();
        List<Pessoa> lista = new ArrayList();
        PreparedStatement pstmt = conexao.prepareStatement("SELECT * FROM pessoa WHERE id = ?;");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(rs.getInt("id"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setTelefone(rs.getInt("telefone"));
            pessoa.setData(rs.getDate("data"));
            pessoa.setEndereco(rs.getInt("endereco"));
            lista.add(pessoa);
        }
        return lista.get(0);
    }

}
