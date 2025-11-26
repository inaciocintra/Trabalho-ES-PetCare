package venda;

import cliente.Cliente;
import produto.Produto;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private Cliente cliente;
    private List<Produto> produtos = new ArrayList<>();
    private double desconto;

    public Venda(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        if (produto.checarDisponibilidade(quantidade)) {
            produto.atualizarEstoque(quantidade);
            produtos.add(produto);
        } else {
            throw new IllegalArgumentException("Produto indisponível no estoque.");
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto p : produtos) {
            total += p.getPreco();
        }
        return total - desconto;
    }

    public void aplicarDesconto(double valor) {
        this.desconto = valor;
    }

    public Cliente getCliente() { return cliente; }
    public List<Produto> getProdutos() { return produtos; }
}
