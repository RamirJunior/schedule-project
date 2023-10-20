create table users (
	id uuid DEFAULT uuid_generate_v4 (),
	name varchar(50),
	lastname varchar(100),
	email varchar(50),
	phone varchar(15),
	PRIMARY KEY (id)
);