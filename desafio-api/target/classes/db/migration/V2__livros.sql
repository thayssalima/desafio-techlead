CREATE TABLE livros (
	id bigserial NOT NULL,
	autor varchar(100) NULL,
	data_cadastro date NULL,
	livro_disponivel bool NULL,
	nome varchar(100) NULL,
	quantidade_estoque int4 NULL,
	id_usuario int8 NULL,
	CONSTRAINT livros_pkey PRIMARY KEY (id)
);



ALTER TABLE livros ADD CONSTRAINT fki2our1gd91mambd8lml7h5fq2 FOREIGN KEY (id_usuario) REFERENCES usuario(id);