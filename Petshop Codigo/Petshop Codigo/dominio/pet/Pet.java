package pet;

import cliente.Cliente;

public class Pet {
    private String nome;
    private String raca;
    private int idade;
    private double peso;
    private String historicoMedico;
    private Cliente tutor;

    public Pet(String nome, String raca, int idade, double peso, String historicoMedico, Cliente tutor) {
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.peso = peso;
        this.historicoMedico = historicoMedico;
        this.tutor = tutor;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public String getHistoricoMedico() { return historicoMedico; }
    public void setHistoricoMedico(String historicoMedico) { this.historicoMedico = historicoMedico; }
    public Cliente getTutor() { return tutor; }
    public void setTutor(Cliente tutor) { this.tutor = tutor; }
}
