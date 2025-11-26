package persistence;

import venda.Venda;
import java.util.List;

public interface VendaDAO {
    void create(Venda venda) throws Exception;
    Venda findById(int id) throws Exception;
    List<Venda> findAll() throws Exception;
    void delete(int id) throws Exception;
}
