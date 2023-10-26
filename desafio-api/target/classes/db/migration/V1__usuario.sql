CREATE TABLE usuario (
	id bigserial NOT NULL,
	bloqueado bool NULL,
	cpf varchar(11) NULL,
	data_bloqueio date NULL,
	dias_penalidade int4 NULL,
	email varchar(100) NULL,
	nome varchar(100) NULL,
	perfil varchar(255) NULL,
	senha varchar(100) NULL,
	CONSTRAINT uk_692bsnqxa8m9fmx7m1yc6hsui UNIQUE (cpf),
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
);
