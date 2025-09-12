-- Jogos
INSERT INTO jogo (titulo, descricao, preco, dataLancamento) 
VALUES ('The Witcher 3', 'Um RPG de mundo aberto com o bruxo Geralt de Rívia.', 99.90, '2015-05-19');
INSERT INTO jogo (titulo, descricao, preco, dataLancamento) 
VALUES ('Elden Ring', 'Um soulslike de fantasia criado por Hidetaka Miyazaki e George R.R. Martin.', 249.90, '2022-02-25');
INSERT INTO jogo (titulo, descricao, preco, dataLancamento) 
VALUES ('Minecraft', 'Um jogo sandbox de blocos infinitos.', 79.90, '2011-11-18');

-- Usuários
INSERT INTO usuario (nome, email, senha, tipo)
VALUES ('Kauan Batista Silveira', 'kauansenior@gmail.com', '123456', 'ADMIN');
INSERT INTO usuario (nome, email, senha, tipo)
VALUES ('Maria Therezza de Oliveira', 'mariatherezza15@gmail.com', 'hellokitty', 'CLIENTE');
INSERT INTO usuario (nome, email, senha, tipo)
VALUES ('Edson Arantes do Nascimento', 'josoareslenda@gmail.com', 'pelerei', 'CLIENTE');