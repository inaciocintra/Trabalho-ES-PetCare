src/persistence/

ConnectionFactory.java — Singleton que fornece conexões JDBC para SQLite.

ClienteDAO.java, ProdutoDAO.java, PetDAO.java, VendaDAO.java — interfaces DAO (contratos).

src/persistence/impl/

SQLiteClienteDAO.java — implementação JDBC/SQLite do DAO de Cliente (CRUD).

SQLiteProdutoDAO.java — implementação JDBC/SQLite do DAO de Produto (CRUD).

SQLitePetDAO.java — implementação JDBC/SQLite do DAO de Pet (CRUD).

SQLiteVendaDAO.java — implementação simplificada do DAO de Venda (insere venda + itens em transação).

sql/ 

create_tables.sql — scripts DDL para criar o esquema (tabelas: cliente, produto, pet, servico, agendamento, venda, venda_item).

insert_data.sql — scripts DML com dados iniciais.