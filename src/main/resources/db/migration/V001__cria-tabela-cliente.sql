CREATE TABLE public.cliente
(
    id uuid NOT NULL,
    email character varying(255) not null,
    nome character varying(255) not null,
    telefone character varying(255) not null,
    CONSTRAINT cliente_pkey PRIMARY KEY (id),
    CONSTRAINT email_key UNIQUE (email)
);

ALTER TABLE public.cliente
    OWNER to admin;