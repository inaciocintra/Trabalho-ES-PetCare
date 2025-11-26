package persistence;

import produto.Produto;
import java.util.List;

public interface ProdutoDAO {
    void create(Produto produto) throws Exception;
    Produto findByName(String nome) throws Exception;
    List<Produto> findAll() throws Exception;
    void update(Produto produto) throws Exception;
    void delete(String nome) throws Exception;
}
