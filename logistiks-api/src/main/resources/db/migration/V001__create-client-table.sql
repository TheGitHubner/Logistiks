create table client(
	id serial primary key,
	name varchar(60) not null,
	email varchar(255) not null,
	phone varchar(255) not null
);