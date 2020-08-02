create table comentario (
id bigint unsigned not null primary key auto_increment,
ordem_servico_id bigint unsigned not null,
descricao text not null,
date_envio datetime not null
);
alter table comentario add  constraint fk_comentario_ordem foreign key (ordem_servico_id) references ordem_servico (id);