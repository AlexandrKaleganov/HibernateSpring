create table if not exists  item(
id serial primary key,
name varchar(200),
price integer
);
insert into item(name, price) VALUES ('хлеб', 5), ('молоко', 2), ('колбаса', 2);