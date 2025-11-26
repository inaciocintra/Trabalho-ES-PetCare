package persistence.impl;

import persistence.VendaDAO;
import persistence.ConnectionFactory;
import venda.Venda;
import produto.Produto;
import cliente.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteVendaDAO implements VendaDAO {

    @Override
    public void create(Venda venda) throws Exception {
        String sqlVenda = "INSERT INTO venda(cliente_cpf, desconto, total, data) VALUES (?, ?, ?, datetime('now'))";
        String sqlItem = "INSERT INTO venda_item(venda_id, produto_nome, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement psVenda = null;
        PreparedStatement psItem = null;
        ResultSet rsKeys = null;
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            conn.setAutoCommit(false);

            psVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
            psVenda.setString(1, venda.getCliente().getCpf());
            psVenda.setDouble(2, 0.0);
            psVenda.setDouble(3, venda.calcularTotal());
            psVenda.executeUpdate();

            rsKeys = psVenda.getGeneratedKeys();
            int vendaId = -1;
            if (rsKeys.next()) vendaId = rsKeys.getInt(1);

            psItem = conn.prepareStatement(sqlItem);
            for (Produto p : venda.getProdutos()) {
                psItem.setInt(1, vendaId);
                psItem.setString(2, p.getNome());
                psItem.setInt(3, 1);
                psItem.setDouble(4, p.getPreco());
                psItem.addBatch();
            }
            psItem.executeBatch();
            conn.commit();
        } catch (Exception ex) {
            if (conn != null) conn.rollback();
            throw ex;
        } finally {
            if (rsKeys != null) rsKeys.close();
            if (psItem != null) psItem.close();
            if (psVenda != null) psVenda.close();
            if (conn != null) conn.setAutoCommit(true);
            if (conn != null) conn.close();
        }
    }

    @Override
    public Venda findById(int id) throws Exception {
        String sql = "SELECT id, cliente_cpf, desconto, total, data FROM venda WHERE id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente(rs.getString("cliente_cpf"), rs.getString("cliente_cpf"), "", "");
                    Venda v = new Venda(c);
                    return v;
                }
            }
        }
        return null;
    }

    @Override
    public List<Venda> findAll() throws Exception {
        String sql = "SELECT id, cliente_cpf, desconto, total, data FROM venda";
        List<Venda> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(rs.getString("cliente_cpf"), rs.getString("cliente_cpf"), "", "");
                Venda v = new Venda(c);
                lista.add(v);
            }
        }
        return lista;
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM venda WHERE id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
