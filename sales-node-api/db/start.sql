create table customer(
	id serial primary key,
	cpf varchar(15) not null,
  nome varchar(100) not null,
	email varchar(100) not null
);

insert into customer(nome, cpf, email) values
('João', '111.111.111-11', 'joao@gmail.com'),
('Maria', '222.222.222-22', 'maria@gmail.com'),
('José', '333.333.333-33', 'jose@gmail.com');