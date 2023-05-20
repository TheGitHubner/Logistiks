create table delivery(
	id serial primary key,
	client_id bigint not null,
	tax decimal(10,2) not null,
	status varchar(20) not null,
	order_date timestamp not null,
	finish_order_date timestamp,
	receiver_name varchar(60) not null,
	receiver_public_area varchar(255) not null,
	receiver_number varchar(30) not null,
	receiver_complement varchar(60) not null,
	receiver_neighbourhood varchar(30) not null
);

alter table delivery add constraint fk_delivery_client
foreign key (client_id) references client (id);