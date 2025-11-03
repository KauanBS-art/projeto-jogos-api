-- Jogos
INSERT INTO jogo (titulo, descricao, preco, dataLancamento, id_empresa)
VALUES ('The Witcher 3', 'Um RPG de mundo aberto com o bruxo Geralt de Rívia.', 99.90, '2015-05-19', 1);
INSERT INTO jogo (titulo, descricao, preco, dataLancamento, id_empresa)
VALUES ('Elden Ring', 'Um soulslike de fantasia criado por Hidetaka Miyazaki e George R.R. Martin.', 249.90, '2022-02-25', 1);
INSERT INTO jogo (titulo, descricao, preco, dataLancamento, id_empresa)
VALUES ('Minecraft', 'Um jogo sandbox de blocos infinitos.', 79.90, '2011-11-18', 1);


-- Categorias
INSERT INTO jogo_categoria (id_jogo, id_categoria)
VALUES (1, 1);
INSERT INTO jogo_categoria (id_jogo, id_categoria)
VALUES (2, 2);
INSERT INTO jogo_categoria (id_jogo, id_categoria)
VALUES (3, 3);

-- Plataformas
INSERT INTO jogo_plataforma (id_jogo, id_plataforma)
VALUES (1, 1);
INSERT INTO jogo_plataforma (id_jogo, id_plataforma)
VALUES (2, 2);
INSERT INTO jogo_plataforma (id_jogo, id_plataforma)
VALUES (3, 3);
