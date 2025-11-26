package persistence.impl;

import persistence.ClienteDAO;
import persistence.ConnectionFactory;
import cliente.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteClienteDAO implements ClienteDAO {

    @Override
    public void create(Cliente cliente) throws Exception {
        String sql = "INSERT INTO cliente(cpf, nome, telefone, endereco) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEndereco());
            ps.executeUpdate();
        }
    }

    @Override
    public Cliente findByCpf(String cpf) throws Exception {
        String sql = "SELECT cpf, nome, telefone, endereco FROM cliente WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente(rs.getString("nome"), rs.getString("cpf"),
                            rs.getString("telefone"), rs.getString("endereco"));
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public List<Cliente> findAll() throws Exception {
        String sql = "SELECT cpf, nome, telefone, endereco FROM cliente";
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(rs.getString("nome"), rs.getString("cpf"),
                        rs.getString("telefone"), rs.getString("endereco"));
                lista.add(c);
            }
        }
        return lista;
    }

    @Override
    public void update(Cliente cliente) throws Exception {
        String sql = "UPDATE cliente SET nome = ?, telefone = ?, endereco = ? WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getCpf());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String cpf) throws Exception {
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cpf);
            ps.executeUpdate();
        }
    }
}
