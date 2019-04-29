create table if not exists  items(
id serial primary key,
descr varchar(200),
user_id integer references users(id),
comment_id integer references comments(id)
);