create table if not exists  item(
id serial primary key,
descr varchar(200),
created TIMESTAMP,
done boolean
);
