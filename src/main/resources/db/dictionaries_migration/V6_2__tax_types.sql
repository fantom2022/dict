create table dict_tax_type (
    id int8 not null,
    name varchar(255),
    factor numeric,
    is_archived boolean,
    update_date timestamp,
    primary key (id)
);




