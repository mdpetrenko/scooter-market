-- noinspection SqlNoDataSourceInspectionForFile

create table categories
(
    id         bigserial primary key,
    title      varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories (title)
values ('Классический самокат'),
       ('Электросамокат');

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       numeric(8, 2) not null,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into products (title, price, category_id)
values ('Razor A6', 11700, 1),
       ('Самокат Razor A5 Lux', 6999.90, 1),
       ('Самокат Razor A5 Air', 8000, 1),
       ('Электросамокат с надувным колесом Razor E Prime Air', 39999.90, 2),
       ('Электросамокат для взрослых Razor EcoSmart SUP', 69999.90, 2),
       ('Электросамокат Razor Power Core E100', 29999.90, 2);

create table orders
(
    id               bigserial primary key,
    price            numeric(8, 2) not null,
    status           varchar       not null default 'NEW',
    username         varchar(255),
    owner_name       varchar(255)  not null,
    delivery_address varchar(255)  not null,
    owner_phone      varchar(255),
    owner_email      varchar(255),
    created_at       timestamp              default current_timestamp,
    updated_at       timestamp              default current_timestamp
);

create table order_items
(
    id             bigserial primary key,
    order_id       bigint        not null references orders (id),
    product_id     varchar(255)  not null references products (id),
    quantity       integer       not null,
    price_per_item numeric(8, 2) not null,
    price          numeric(8, 2) not null,
    created_at     timestamp default current_timestamp,
    updated_at     timestamp default current_timestamp
);

insert into orders (price, username, owner_name, delivery_address, owner_phone, owner_email)
values (1000, 'user', 'max', 'Europe', '77777777777', 'test@gmail.com')
