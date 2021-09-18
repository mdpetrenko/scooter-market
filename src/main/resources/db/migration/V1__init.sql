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

create table users
(
    id         bigserial primary key,
    username   varchar(255) not null,
    password   varchar(255) not null,
    email      varchar(255) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(255) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into products (title, price, category_id)
values ('Razor A6', 11700, 1),
       ('Самокат Razor A5 Lux', 6990, 1),
       ('Самокат Razor A5 Air', 8000, 1),
       ('Электросамокат с надувным колесом Razor E Prime Air', 39900, 2),
       ('Электросамокат для взрослых Razor EcoSmart SUP', 69900, 2),
       ('Электросамокат Razor Power Core E100', 29900, 2);

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@example.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@example.com');

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);
