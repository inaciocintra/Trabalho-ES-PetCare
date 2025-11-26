# Sistema de Gestão de Pet Shop - Camada de Domínio

Este projeto implementa a camada de domínio do Sistema de Gestão de Pet Shop
## Estrutura de Pacotes

- **cliente/Cliente.java**
  - Representa os clientes do Pet Shop (tutores).
  - Armazena informações como nome, CPF, telefone e endereço.
  - Mantém a lista de pets vinculados ao cliente.
  - Métodos principais:
    - `adicionarPet(Pet pet)` : associa um pet ao cliente.
    - `removerPet(Pet pet)` : remove um pet do cliente.

- **pet/Pet.java**
  - Representa os animais de estimação dos clientes.
  - Atributos: nome, raça, idade, peso, histórico médico e o tutor (Cliente).
  - Mantém a associação entre o pet e seu tutor.

- **produto/Produto.java**
  - Representa os produtos vendidos pelo Pet Shop (ração, brinquedos, medicamentos etc.).
  - Atributos: nome, categoria, preço e quantidade em estoque.
  - Métodos principais:
    - `checarDisponibilidade(int quantidade)` : verifica se há estoque suficiente.
    - `atualizarEstoque(int quantidadeVendida)` : reduz o estoque após venda.

- **servico/Servico.java**
  - Representa os serviços oferecidos pelo Pet Shop (banho, tosa, consulta veterinária).
  - Atributos: tipo do serviço e preço.

- **servico/Agendamento.java**
  - Representa o agendamento de um serviço para um pet.
  - Atributos: pet, serviço, data e profissional responsável.
  - Permite organizar a agenda de serviços.

- **venda/Venda.java**
  - Representa uma venda realizada no Pet Shop.
  - Atributos: cliente, lista de produtos comprados e desconto aplicado.
  - Métodos principais:
    - `adicionarProduto(Produto produto, int quantidade)` : adiciona produtos à venda, controlando estoque.
    - `calcularTotal()` : calcula o valor final da venda com desconto.
    - `aplicarDesconto(double valor)` : aplica desconto ao total da venda.

