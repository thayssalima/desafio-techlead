CREATE TABLE public.livros (
	id bigserial NOT NULL,
	autor varchar(100) NULL,
	data_cadastro date NULL,
	nome varchar(100) NULL,
	id_usuario int8 NULL,
	CONSTRAINT livros_pkey PRIMARY KEY (id)
);

ALTER TABLE public.livros OWNER TO postgres;
GRANT ALL ON TABLE public.livros TO postgres;

ALTER TABLE public.livros ADD CONSTRAINT fki2our1gd91mambd8lml7h5fq2 FOREIGN KEY (id_usuario) REFERENCES public.usuario(id);