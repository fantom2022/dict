create table dict_production_directive (
    id int8 not null,
    name varchar(255),
    short_name varchar(255),
    update_date timestamp,
    primary key (id)
);