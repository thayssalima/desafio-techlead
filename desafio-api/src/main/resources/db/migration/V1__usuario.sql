CREATE TABLE public.usuario (
	id bigserial NOT NULL,
	cpf varchar(11) NULL,
	email varchar(100) NULL,
	nome varchar(100) NULL,
	perfil varchar(255) NULL,
	senha varchar(100) NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

ALTER TABLE public.usuario OWNER TO postgres;
GRANT ALL ON TABLE public.usuario TO postgres;