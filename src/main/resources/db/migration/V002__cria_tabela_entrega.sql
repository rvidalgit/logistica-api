CREATE TABLE entrega
(
    id                       bigserial      not null,
    id_cliente               uuid           not null,
    taxa                     decimal(10, 2) not null,
    status                   varchar(20)    not null,
    data_pedido              timestamp with time zone      not null,
    data_finalizacao         timestamp with time zone,

    destinatario_nome        varchar(60)    not null,
    destinatario_logradouro  varchar(255)   not null,
    destinatario_numero      varchar(30)    not null,
    destinatario_complemento varchar(60)    not null,
    destinatario_bairro      varchar(30)    not null,

    primary key (id)
);

alter table entrega
    add constraint fk_entrega__cliente foreign key (id_cliente) references cliente (id);