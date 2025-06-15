- -----------------------------------------------------
-- Table stazhi.supervisor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS supervisor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    idade INTEGER NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE
    senha VARCHAR(45) NOT NULL,
);

-- Criar índices únicos
CREATE UNIQUE INDEX IF NOT EXISTS supervisor_email_unique ON supervisor (email);

-- -----------------------------------------------------
-- Table stazhi.empresas
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS empresas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    cnpj VARCHAR(45) NOT NULL UNIQUE,
    email VARCHAR(45) NOT NULL UNIQUE,
    setor VARCHAR(180) NOT NULL,
    senha VARCHAR(45) NOT NULL
);

-- Criar índices únicos
CREATE UNIQUE INDEX IF NOT EXISTS empresas_cnpj_unique ON empresas (cnpj);
CREATE UNIQUE INDEX IF NOT EXISTS empresas_email_unique ON empresas (email);

-- -----------------------------------------------------
-- Table stazhi.alunos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS alunos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    formacao VARCHAR(45) NOT NULL,
    idade INTEGER NOT NULL,
    supervisor_id INTEGER,
    empresas_id INTEGER,
    senha VARCHAR(45) NOT NULL
    CONSTRAINT fk_alunos_supervisor 
        FOREIGN KEY (supervisor_id) 
        REFERENCES supervisor (id) 
        ON DELETE SET NULL 
        ON UPDATE CASCADE,
    CONSTRAINT fk_alunos_empresas 
        FOREIGN KEY (empresas_id) 
        REFERENCES empresas (id) 
        ON DELETE SET NULL 
        ON UPDATE CASCADE
);

-- Criar índices
CREATE UNIQUE INDEX IF NOT EXISTS alunos_email_unique ON alunos (email);
CREATE INDEX IF NOT EXISTS idx_alunos_supervisor ON alunos (supervisor_id);
CREATE INDEX IF NOT EXISTS idx_alunos_empresas ON alunos (empresas_id);

-- -----------------------------------------------------
-- Table stazhi.vagas
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS vagas (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(180) NOT NULL,
    quantidade_de_candidaturas INTEGER NOT NULL,
    empresas_id INTEGER NOT NULL,
    data_limite TIMESTAMP NOT NULL,
    modalidade VARCHAR(20) NOT NULL CHECK (modalidade IN ('presencial', 'remoto', 'hibrido')),
    CONSTRAINT fk_vagas_empresas 
        FOREIGN KEY (empresas_id) 
        REFERENCES empresas (id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);

-- Criar índice
CREATE INDEX IF NOT EXISTS idx_vagas_empresas ON vagas (empresas_id);
CREATE INDEX IF NOT EXISTS idx_vagas_data_limite ON vagas (data_limite);
CREATE INDEX IF NOT EXISTS idx_vagas_modalidade ON vagas (modalidade);

-- -----------------------------------------------------
-- Table stazhi.candidaturas (tabela de relacionamento many-to-many)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS candidaturas (
    alunos_id INTEGER NOT NULL,
    vagas_id INTEGER NOT NULL,
    data_candidatura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (alunos_id, vagas_id),
    CONSTRAINT fk_candidaturas_alunos 
        FOREIGN KEY (alunos_id) 
        REFERENCES alunos (id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    CONSTRAINT fk_candidaturas_vagas 
        FOREIGN KEY (vagas_id) 
        REFERENCES vagas (id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);

-- Criar índices para otimizar consultas
CREATE INDEX IF NOT EXISTS idx_candidaturas_alunos ON candidaturas (alunos_id);
CREATE INDEX IF NOT EXISTS idx_candidaturas_vagas ON candidaturas (vagas_id);

-- Comentários nas tabelas
COMMENT ON TABLE supervisor IS 'Tabela de supervisores responsáveis pelos alunos';
COMMENT ON TABLE empresas IS 'Tabela de empresas que oferecem vagas de estágio';
COMMENT ON TABLE alunos IS 'Tabela de alunos cadastrados no sistema';
COMMENT ON TABLE vagas IS 'Tabela de vagas de estágio disponíveis';
COMMENT ON TABLE candidaturas IS 'Tabela de relacionamento entre alunos e vagas (candidaturas)';

-- Comentários em colunas específicas
COMMENT ON COLUMN vagas.quantidade_de_candidaturas IS 'Número de candidaturas para esta vaga';
COMMENT ON COLUMN candidaturas.data_candidatura IS 'Data e hora em que o aluno se candidatou à vaga';