package produto;

public class Produto {
    private String nome;
    private String categoria;
    private double preco;
    private int estoque;

    public Produto(String nome, String categoria, double preco, int estoque) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
    }

    public boolean checarDisponibilidade(int quantidade) {
        return estoque >= quantidade;
    }

    public void atualizarEstoque(int quantidadeVendida) {
        if (quantidadeVendida <= estoque) {
            estoque -= quantidadeVendida;
        } else {
            throw new IllegalArgumentException("Estoque insuficiente!");
        }
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }
}
