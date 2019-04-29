create table if not exists  comments(
id serial primary key,
comment varchar(200),
user_id integer references users(id)
);