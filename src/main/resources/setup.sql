CREATE TABLE usuarios (
    id SERIAL NOT NULL,
    nome VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL,
    senha VARCHAR(250) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE perfis (
    id SERIAL NOT NULL,
    nome VARCHAR(250) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE usuario_perfil (
    id_usuario INT NOT NULL,
    id_perfil INT NOT NULL,
    PRIMARY KEY(id_usuario, id_perfil)
);

ALTER TABLE usuario_perfil ADD CONSTRAINT fk_usuario_perfil_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios (id);
ALTER TABLE usuario_perfil ADD CONSTRAINT fk_usuario_perfil_perfil FOREIGN KEY (id_perfil) REFERENCES perfis (id);

CREATE TABLE editoras (
     id SERIAL NOT NULL,
     nome VARCHAR(250) NOT NULL,
     PRIMARY KEY(id)
);

CREATE TABLE autores (
     id SERIAL NOT NULL,
     nome VARCHAR(250) NOT NULL,
     PRIMARY KEY(id)
);

CREATE TABLE livros (
    id SERIAL,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(250),
    preco NUMERIC(6, 2) NOT NULL,
    data_publicacao DATE NOT NULL,
    id_editora INT NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE livros ADD CONSTRAINT fk_livro_editora FOREIGN KEY (id_editora) REFERENCES editoras (id);

CREATE TABLE autor_livro (
    id_autor INT NOT NULL,
    id_livro INT NOT NULL,
    PRIMARY KEY(id_autor, id_livro)
);

ALTER TABLE autor_livro ADD CONSTRAINT fk_autor_livro_autor FOREIGN KEY (id_autor) REFERENCES autores (id);
ALTER TABLE autor_livro ADD CONSTRAINT fk_autor_livro_livro FOREIGN KEY (id_livro) REFERENCES livros (id);

INSERT INTO usuarios(
    nome,
    email,
    senha
) VALUES (
    'José da Silva',
    'jose@email.com',
    '$2a$10$iYX7GOoh72wcI6kRNRwI3.CV7aYmwLmdqGdvJYStNyYrckk5M/O4O' --123456
 );

INSERT INTO perfis(nome) VALUES ('ADMINISTRADOR');
INSERT INTO perfis(nome) VALUES ('CLIENTE');

INSERT INTO usuario_perfil(id_usuario, id_perfil) VALUES (1, 1);

INSERT INTO editoras(nome) VALUES ('HarperCollins');
INSERT INTO editoras(nome) VALUES ('Nova Fronteira');
INSERT INTO editoras(nome) VALUES ('Antofagica');
INSERT INTO editoras(nome) VALUES ('Alta Books');

INSERT INTO autores(nome) VALUES ('Agatha Christie');
INSERT INTO autores(nome) VALUES ('Charles Dickens');
INSERT INTO autores(nome) VALUES ('Bert Bates');
INSERT INTO autores(nome) VALUES ('Kathy Sierra');

INSERT INTO livros(
    nome,
    descricao,
    preco,
    data_publicacao,
    id_editora
) VALUES (
    'Treze à mesa',
    'Num mundo de aristocratas excêntricos e de atrizes e atores famosos se tecem os fios da complexa e inquietadora intriga desse romance que poderia levar como subtítulo Um Baile de Máscaras.',
    8.99,
    to_date('07-02-2016', 'DD-MM-YYYY'),
    1
);

INSERT INTO livros(
    nome,
    descricao,
    preco,
    data_publicacao,
    id_editora
) VALUES (
    'O misterioso caso de styles',
    'Quando mr. Hastings encontra seu velho conhecido John Cavendish casualmente e aceita seu convite para passar uma temporada na enorme e isolada casa de campo de Styles, não imagina a misteriosa trama que o espera.',
    32.5,
    to_date('12-07-2014', 'DD-MM-YYYY'),
    1
 );

INSERT INTO livros(
    nome,
    descricao,
    preco,
    data_publicacao,
    id_editora
) VALUES (
    'David Copperfield',
    'Neste romance de formação, em muito inspirado na vida de seu autor, acompanhamos o amadurecimento e a tumultuosa jornada de David, um menino nascido à meia-noite de uma sexta-feira.',
    45.99,
    to_date('22-03-2019', 'DD-MM-YYYY'),
    2
 );

INSERT INTO livros(
    nome,
    descricao,
    preco,
    data_publicacao,
    id_editora
) VALUES (
    'Um conto de natal',
    'Scrooge é um homem avarento, muquirana, miserável e mão de vaca. Para ele, até mesmo o Natal parece um enorme desperdício de tempo e de dinheiro.',
    9.50,
    to_date('01-12-2019', 'DD-MM-YYYY'),
    3
 );

INSERT INTO livros(
    nome,
    descricao,
    preco,
    data_publicacao,
    id_editora
) VALUES (
    'Use a cabeça!: Java',
    'Use a Cabeça! Java é uma experiência completa de aprendizado em programação orientada a objetos (OO) e Java.',
    141.94,
    to_date('16-10-2012', 'DD-MM-YYYY'),
    4
 );

INSERT INTO autor_livro(id_autor, id_livro) VALUES(1, 1);
INSERT INTO autor_livro(id_autor, id_livro) VALUES(1, 2);
INSERT INTO autor_livro(id_autor, id_livro) VALUES(2, 3);
INSERT INTO autor_livro(id_autor, id_livro) VALUES(2, 4);
INSERT INTO autor_livro(id_autor, id_livro) VALUES(3, 5);
INSERT INTO autor_livro(id_autor, id_livro) VALUES(4, 5);