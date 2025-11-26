package persistence.impl;

import persistence.ProdutoDAO;
import persistence.ConnectionFactory;
import produto.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteProdutoDAO implements ProdutoDAO {

    @Override
    public void create(Produto produto) throws Exception {
        String sql = "INSERT INTO produto(nome, categoria, preco, estoque) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getCategoria());
            ps.setDouble(3, produto.getPreco());
            ps.setInt(4, produto.getEstoque());
            ps.executeUpdate();
        }
    }

    @Override
    public Produto findByName(String nome) throws Exception {
        String sql = "SELECT nome, categoria, preco, estoque FROM produto WHERE nome = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Produto p = new Produto(rs.getString("nome"), rs.getString("categoria"),
                            rs.getDouble("preco"), rs.getInt("estoque"));
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public List<Produto> findAll() throws Exception {
        String sql = "SELECT nome, categoria, preco, estoque FROM produto";
        List<Produto> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Produto p = new Produto(rs.getString("nome"), rs.getString("categoria"),
                        rs.getDouble("preco"), rs.getInt("estoque"));
                lista.add(p);
            }
        }
        return lista;
    }

    @Override
    public void update(Produto produto) throws Exception {
        String sql = "UPDATE produto SET categoria = ?, preco = ?, estoque = ? WHERE nome = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, produto.getCategoria());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getEstoque());
            ps.setString(4, produto.getNome());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String nome) throws Exception {
        String sql = "DELETE FROM produto WHERE nome = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.executeUpdate();
        }
    }
}
