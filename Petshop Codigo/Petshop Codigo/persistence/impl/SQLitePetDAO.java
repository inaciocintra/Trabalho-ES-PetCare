package persistence.impl;

import persistence.PetDAO;
import persistence.ConnectionFactory;
import pet.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLitePetDAO implements PetDAO {

    @Override
    public void create(Pet pet) throws Exception {
        String sql = "INSERT INTO pet(nome, raca, idade, peso, historico_medico, tutor_cpf) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pet.getNome());
            ps.setString(2, pet.getRaca());
            ps.setInt(3, pet.getIdade());
            ps.setDouble(4, pet.getPeso());
            ps.setString(5, pet.getHistoricoMedico());
            ps.setString(6, pet.getTutor().getCpf());
            ps.executeUpdate();
        }
    }

    @Override
    public Pet findByNome(String nome) throws Exception {
        String sql = "SELECT nome, raca, idade, peso, historico_medico, tutor_cpf FROM pet WHERE nome = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // tutor must be fetched separately if needed
                    Pet p = new Pet(rs.getString("nome"), rs.getString("raca"),
                            rs.getInt("idade"), rs.getDouble("peso"),
                            rs.getString("historico_medico"), null);
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public List<Pet> findByTutorCpf(String cpf) throws Exception {
        String sql = "SELECT nome, raca, idade, peso, historico_medico, tutor_cpf FROM pet WHERE tutor_cpf = ?";
        List<Pet> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pet p = new Pet(rs.getString("nome"), rs.getString("raca"),
                            rs.getInt("idade"), rs.getDouble("peso"),
                            rs.getString("historico_medico"), null);
                    lista.add(p);
                }
            }
        }
        return lista;
    }

    @Override
    public void update(Pet pet) throws Exception {
        String sql = "UPDATE pet SET raca = ?, idade = ?, peso = ?, historico_medico = ?, tutor_cpf = ? WHERE nome = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pet.getRaca());
            ps.setInt(2, pet.getIdade());
            ps.setDouble(3, pet.getPeso());
            ps.setString(4, pet.getHistoricoMedico());
            ps.setString(5, pet.getTutor().getCpf());
            ps.setString(6, pet.getNome());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String nome) throws Exception {
        String sql = "DELETE FROM pet WHERE nome = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.executeUpdate();
        }
    }
}
