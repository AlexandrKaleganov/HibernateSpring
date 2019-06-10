create table if not exists  item(
id serial primary key,
name varchar(200),
price integer
);
insert into item(name, price) VALUES ('хлеб', 1), ('молоко', 1), ('колбаса', 1);