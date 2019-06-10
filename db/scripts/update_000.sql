create table if not exists  toodet(
id serial primary key,
name varchar(200),
price integer
);
insert into toodet(name, price) values ('хлеб',2),