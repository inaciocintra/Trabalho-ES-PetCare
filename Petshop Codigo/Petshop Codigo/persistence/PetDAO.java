package persistence;

import pet.Pet;
import java.util.List;

public interface PetDAO {
    void create(Pet pet) throws Exception;
    Pet findByNome(String nome) throws Exception;
    List<Pet> findByTutorCpf(String cpf) throws Exception;
    void update(Pet pet) throws Exception;
    void delete(String nome) throws Exception;
}
