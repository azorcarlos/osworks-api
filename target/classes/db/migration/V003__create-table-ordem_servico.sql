create table ordem_servico (
	id bigint unsigned not null primary key auto_increment,
	cliente_id bigint unsigned not null,
	descricao text not null,
	preco decimal(20,2) not null,
	status varchar(20) not null,
	data_abertura datetime not null,
	data_finalizacao datetime
);
alter table ordem_servico add constraint fk_ordem_servico_cliente 
 foreign key (cliente_id) references cliente (id); 
 
