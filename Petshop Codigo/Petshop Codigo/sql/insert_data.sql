-- insert_data.sql
INSERT INTO cliente(cpf, nome, telefone, endereco) VALUES ('11111111111', 'João Silva', '31999990000', 'Rua A, 123');
INSERT INTO cliente(cpf, nome, telefone, endereco) VALUES ('22222222222', 'Maria Souza', '31988880000', 'Av B, 456');

INSERT INTO produto(nome, categoria, preco, estoque) VALUES ('Ração Premium', 'Ração', 120.0, 10);
INSERT INTO produto(nome, categoria, preco, estoque) VALUES ('Coleira', 'Acessório', 25.0, 20);

INSERT INTO servico(tipo, preco) VALUES ('Banho', 40.0);
INSERT INTO servico(tipo, preco) VALUES ('Tosa', 60.0);
