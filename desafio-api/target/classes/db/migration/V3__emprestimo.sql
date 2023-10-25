CREATE TABLE public.emprestimo (
	id bigserial NOT NULL,
	"data" timestamp NULL,
	devolvido_livro bool NULL,
	dias_emprestados int4 NULL,
	emprestimo_ativo bool NULL,
	id_livro int8 NULL,
	id_usuario int8 NULL,
	CONSTRAINT emprestimo_pkey PRIMARY KEY (id)
);


ALTER TABLE public.emprestimo OWNER TO postgres;
GRANT ALL ON TABLE public.emprestimo TO postgres;


ALTER TABLE public.emprestimo ADD CONSTRAINT fkddtboecot5bhm6igucr0j405u FOREIGN KEY (id_livro) REFERENCES public.livros(id);
ALTER TABLE public.emprestimo ADD CONSTRAINT fks8lirup1wisehyym648mgb5qg FOREIGN KEY (id_usuario) REFERENCES public.usuario(id);