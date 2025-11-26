package servico;

import java.time.LocalDate;
import pet.Pet;

public class Agendamento {
    private Pet pet;
    private Servico servico;
    private LocalDate data;
    private String profissional;

    public Agendamento(Pet pet, Servico servico, LocalDate data, String profissional) {
        this.pet = pet;
        this.servico = servico;
        this.data = data;
        this.profissional = profissional;
    }

    public Pet getPet() { return pet; }
    public Servico getServico() { return servico; }
    public LocalDate getData() { return data; }
    public String getProfissional() { return profissional; }
}
