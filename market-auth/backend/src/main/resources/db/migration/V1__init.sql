create table users
(
    id         bigserial primary key,
    username   varchar(255) unique not null,
    password   varchar(255)        not null,
    name       varchar(255)        not null,
    email      varchar(255)        not null unique,
    phone      varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    title      varchar(255) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

create table billing_addresses
(
    id            bigserial primary key,
    user_id       bigint       not null references users (id),
    address_line1 varchar(255) not null,
    address_line2 varchar(255) not null,
    admin_area1   varchar(255) not null,
    admin_area2   varchar(255) not null,
    postal_code   varchar(255) not null,
    country_code  varchar(255) not null
);

insert into users (username, password, name, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Maksim', 'user@example.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Superadmin', 'admin@example.com');

insert into billing_addresses(user_id, address_line1, address_line2, admin_area1, admin_area2, postal_code,
                              country_code)
values (1, 'Lenina 55', '124', 'Sverdlovsk', 'Yekaterinburg', '620000', 'RU'),
       (1, 'Lenina 66', '15', 'Chelyabinsk', 'Chelyabinsk', '350000', 'RU'),
       (1, 'Lenina 20', '15', 'Sverdlovsk', 'Yekabe', '350000', 'RU');

insert into roles (title)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);