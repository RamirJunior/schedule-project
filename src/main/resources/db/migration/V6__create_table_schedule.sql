create table schedule (
	id uuid PRIMARY KEY,
	description varchar(255),
	date_time timestamp,
	creation_date_time timestamp,
	user_id uuid,
	CONSTRAINT fk_schedule_user FOREIGN KEY(user_id) REFERENCES users(id)
);