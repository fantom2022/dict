create table dict_enlarged_composition (
    id int8 not null,
    designation varchar(255),
    vshi varchar(255),
    name varchar(255),
    note varchar(255),
    update_date timestamp,
    update_user varchar(255),
    primary key (id)
);