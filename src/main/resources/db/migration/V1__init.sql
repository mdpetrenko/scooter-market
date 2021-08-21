create table categories
(
    id    bigserial primary key,
    title varchar(255)
);

insert into categories (title)
values ('Классический самокат'),
       ('Электросамокат');

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    category_id bigint references categories (id)
);

insert into products (title, price, category_id)
values ('Razor A6', 11700, 1),
       ('Самокат Razor A5 Lux', 6990, 1),
       ('Самокат Razor A5 Air', 8000, 1),
       ('Электросамокат с надувным колесом Razor E Prime Air', 39900, 2),
       ('Электросамокат для взрослых Razor EcoSmart SUP', 69900, 2),
       ('Электросамокат Razor Power Core E100', 29900, 2);