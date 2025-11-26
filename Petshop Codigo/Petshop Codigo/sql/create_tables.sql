-- create_tables.sql
PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS cliente (
    cpf TEXT PRIMARY KEY,
    nome TEXT NOT NULL,
    telefone TEXT,
    endereco TEXT
);

CREATE TABLE IF NOT EXISTS produto (
    nome TEXT PRIMARY KEY,
    categoria TEXT,
    preco REAL,
    estoque INTEGER
);

CREATE TABLE IF NOT EXISTS pet (
    nome TEXT PRIMARY KEY,
    raca TEXT,
    idade INTEGER,
    peso REAL,
    historico_medico TEXT,
    tutor_cpf TEXT,
    FOREIGN KEY (tutor_cpf) REFERENCES cliente(cpf) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS servico (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo TEXT,
    preco REAL
);

CREATE TABLE IF NOT EXISTS agendamento (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    pet_nome TEXT,
    servico_id INTEGER,
    data TEXT,
    profissional TEXT,
    FOREIGN KEY (pet_nome) REFERENCES pet(nome) ON DELETE CASCADE,
    FOREIGN KEY (servico_id) REFERENCES servico(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS venda (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    cliente_cpf TEXT,
    desconto REAL,
    total REAL,
    data TEXT,
    FOREIGN KEY (cliente_cpf) REFERENCES cliente(cpf) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS venda_item (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    venda_id INTEGER,
    produto_nome TEXT,
    quantidade INTEGER,
    preco_unitario REAL,
    FOREIGN KEY (venda_id) REFERENCES venda(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_nome) REFERENCES produto(nome) ON DELETE SET NULL
);
