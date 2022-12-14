create table dict_contract (
    id int8 not null,
    parent_id int8,
    registration_number varchar(255),
    name varchar(2000),
    number varchar(255),
    date timestamp,
    direction_id int8,
    direction varchar(255),
    contractor_id int8,
    work_kind_id int8,
    work_kind varchar(255),
    tax_type_id int8,
    custom_order_id int8,
    is_archived boolean,
    update_date timestamp,
    primary key (id)
);




