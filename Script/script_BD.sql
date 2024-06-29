-- ---------------------------
/* POPULANDO O BANCO DE DADOS PARA TESTES DE SELECT */

-- Inserindo dados na tabela de Usuários

-- Inserindo dados na tabela de Usuários
INSERT INTO usuarios (nome_usuario, senha, email) VALUES
('a', '123', 'a'),
('mariaribeiro', 'senha456', 'maria@example.com'),
('carloslima', 'senha789', 'carlos@example.com'),
('anapaula', 'senhaabc', 'ana@example.com'),
('lucasrodrigues', 'senhadef', 'lucas@example.com'),
('fernandopereira', 'senhaghi', 'fernando@example.com'),
('julianadasilva', 'senhajkl', 'juliana@example.com'),
('rodrigomartins', 'senhamno', 'rodrigo@example.com'),
('beatrizsantos', 'senhapqr', 'beatriz@example.com'),
('andreiacosta', 'senhastu', 'andreia@example.com');

-- Inserindo dados na tabela de Categorias
INSERT INTO categorias (nome_categoria) VALUES
('Alimentação'),
('Transporte'),
('Saúde'),
('Educação'),
('Lazer'),
('Roupas'),
('Tecnologia'),
('Moradia'),
('Viagem'),
('Outros');

INSERT INTO `transacoes` (`categoria_id`, `valor`, `data_dia`, `dataMesAno`, `descricao`)
VALUES
  (1, 120.00, '06', '12/2024', 'Jantar em restaurante'),
  (2, 80.00, '07', '12/2024', 'Taxi'),
  (3, 300.00, '08', '12/2024', 'Material escolar'),
  (4, 50.00, '09', '12/2024', 'Aluguel de filmes'),
  (5, 100.00, '10', '08/2024', 'Remédios'),
  (1, -200.00, '11', '08/2024', 'Compras de mês'),
  (2, -60.00, '12', '08/2024', 'Combustível'),
  (3, -150.00, '13', '08/2024', 'Livros'),
  (4, 80.00, '14', '08/2024', 'Parque de diversões'),
  (5, 90.00, '15', '08/2024', 'Exames médicos'),
  (1, 180.00, '16', '08/2024', 'Padaria'),
  (2, 70.00, '17', '08/2024', 'Bilhete único'),
  (3, 250.00, '18', '10/2024', 'Aulas de música'),
  (4, 120.00, '19', '10/2024', 'Teatro'),
  (5, 130.00, '20', '10/2024', 'Consultas dentárias'),
  (1, 90.00, '21', '10/2024', 'Lanchonete'),
  (2, -100.00, '22', '10/2024', 'Transporte por aplicativo'),
  (3, -400.00, '23', '10/2024', 'Mensalidade escolar'),
  (4, 60.00, '24', '10/2024', 'Locação de bicicleta'),
  (5, 110.00, '25', '10/2024', 'Fisioterapia'),
  (1, 130.00, '26', '01/2024', 'Jantar com amigos'),
  (2, 40.00, '27', '01/2024', 'Estacionamento'),
  (3, 220.00, '28', '2023-06', 'Material didático'),
  (4, 70.00, '29', '01/2024', 'Sessão de cinema'),
  (5, 140.00, '30', '01/2024', 'Medicamentos'),
  (1, 160.00, '01', '01/2024', 'Compras no mercado'),
  (2, 90.00, '02', '01/2024', 'Táxi'),
  (3, 310.00, '03', '01/2024', 'Curso de idiomas'),
  (4, 110.00, '04', '01/2024', 'Passeio no parque'),
  (5, 80.00, '05', '01/2024', 'Consulta médica');

-- --------------------------------------------
/*POSIVEIS SELECTS QUE EU POSSA USAR NO TRABALHO*/

SELECT * FROM categorias;
SELECT * FROM transacoes;
SELECT * FROM usuarios;

/*LISTAR PARA VER AS COMPRAS*/
SELECT 
    transacoes.categoria_id,
    transacoes.data_dia,
    transacoes.dataMesAno,
    transacoes.descricao,
    transacoes.valor,
    categorias.nome_categoria
FROM
    transacoes
INNER JOIN
    categorias
ON
    transacoes.categoria_id = categorias.categoria_id;

    
/*SOMA FILTRANDO A DATA*/
SELECT 
    SUM(valor) AS SOMA_GERAL,
    COUNT(*) AS QTDE
FROM
    transacoes
WHERE
    data_transacao >= '2024-05-01' AND data_transacao <= '2024-05-30';


/*MÉDIA DAS TRANSAÇÕES*/
SELECT 
    AVG(valor) AS Media_Transacoes
FROM
    transacoes;
    
/*MAIOR E MENOR TRANSIÇÃO*/
SELECT 
    MAX(valor) AS Maior_Transacao,
    MIN(valor) AS Menor_Transacao
FROM
    transacoes;
    
/*SOMA DOS GASTOS / SOMA DOQUE ENTRA*/
SELECT 
    SUM(CASE WHEN valor >= 0 THEN valor ELSE 0 END) AS Receitas,
    SUM(CASE WHEN valor < 0 THEN valor ELSE 0 END) AS Despesas,
    SUM(valor) AS Saldo_Total
FROM
    transacoes;
    
