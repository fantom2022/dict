create table dict_unit_measure (
   id int8 not null,
    full_name varchar(255),
    name varchar(255),
    name_lower varchar(255),
    update_date timestamp,
    primary key (id)
);

create table dict_custom_order (
   id int8 not null,
    order_number varchar(12),
    open_date timestamp,
    close_date timestamp,
    stop_date timestamp,
    departments varchar(4000),
    update_date timestamp,
    primary key (id)
);

