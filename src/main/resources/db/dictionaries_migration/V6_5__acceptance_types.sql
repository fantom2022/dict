create table dict_acceptance_types (
    id int8 not null,
    litera varchar(255),
    name varchar(255),
    importance int8,
    is_archived boolean,
    update_date timestamp,
    primary key (id)
);




