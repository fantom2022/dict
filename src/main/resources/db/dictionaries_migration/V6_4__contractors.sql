create table dict_contractor (
    id int8 not null,
    parent_id int8,
    country_id int8,
    country_code int8,
    country_name varchar(255),
    type varchar(255),
    is_client boolean,
    is_supplier boolean,
    is_cargo_shipper boolean,
    is_cargo_receiver boolean,
    owner_type_id int8,
    owner_type_name varchar(255),
    name varchar(255),
    full_name varchar(1000),
    local_name varchar(1000),
    inn varchar(255),
    kpp varchar(255),
    legal_address varchar(3000),
    actual_address varchar(3000),
    create_date timestamp,
    archive_date timestamp,
    is_archived boolean,
    update_date timestamp,
    primary key (id)
);




