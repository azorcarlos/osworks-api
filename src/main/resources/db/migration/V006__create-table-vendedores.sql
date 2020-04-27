create table vendedores (
id bigint unsigned primary key not null auto_increment,
nome varchar(20) not null,
departamento_id bigint unsigned not null
);
alter table vendedores add constraint fk_vendedores_departamento foreign key (departamento_id) references departamento(id);