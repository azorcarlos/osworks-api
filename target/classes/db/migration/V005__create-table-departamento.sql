create table departamento (
id bigint unsigned not null primary key auto_increment,
descricao varchar(90) not null,
data_cadastro datetime default current_timestamp not null,
status varchar(20)
);