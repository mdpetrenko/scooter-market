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

create table addresses
(
    id            bigserial primary key,
    street        varchar(255) not null,
    office_number varchar(255) not null,
    city          varchar(255) not null,
    district      varchar(255) not null,
    postal_code   varchar(255) not null,
    country_code  varchar(255) not null,
    created_at    timestamp default current_timestamp,
    updated_at    timestamp default current_timestamp
);

create table orders
(
    id          bigserial primary key,
    price       numeric(8, 2) not null,
    status      varchar       not null default 'NEW',
    username    varchar(255)  not null,
    owner_name  varchar(255)  not null,
    owner_phone varchar(255)  not null,
    owner_email varchar(255)  not null,
    address_id  bigint        not null references addresses (id),
    created_at  timestamp              default current_timestamp,
    updated_at  timestamp              default current_timestamp
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

insert into products (title, price, category_id)
values ('Razor A6', 11700, 1),
       ('Самокат Razor A5 Lux', 6999.90, 1),
       ('Самокат Razor A5 Air', 8000, 1),
       ('Электросамокат с надувным колесом Razor E Prime Air', 39999.90, 2),
       ('Электросамокат для взрослых Razor EcoSmart SUP', 69999.90, 2),
       ('Электросамокат Razor Power Core E100', 29999.90, 2);

insert into addresses (id, street, office_number, city, district, postal_code, country_code)
values (1, 'Lenin St. 66', '205', 'Yekaterinburg', 'Sverdlovsk', '620000', 'RU');

insert into orders (price, username, owner_name, owner_phone, owner_email, address_id)
values (1000, 'user', 'Maksim Petrenko', '+79955556677', 'test@gmail.com', 1);
