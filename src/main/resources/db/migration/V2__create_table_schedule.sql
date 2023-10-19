create table schedule (
	id uuid PRIMARY KEY,
	description varchar(255),
	date_time timestamp,
	creation_date_time timestamp,
	person_id uuid,
	CONSTRAINT fk_schedule_person FOREIGN KEY(person_id) REFERENCES person(id)
);