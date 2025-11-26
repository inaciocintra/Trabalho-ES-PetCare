package persistence;

import cliente.Cliente;
import java.util.List;

public interface ClienteDAO {
    void create(Cliente cliente) throws Exception;
    Cliente findByCpf(String cpf) throws Exception;
    List<Cliente> findAll() throws Exception;
    void update(Cliente cliente) throws Exception;
    void delete(String cpf) throws Exception;
}
