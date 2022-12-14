create table dict_material_resource (
   id int8 not null,
    id_inner int8 not null,
    id_ref int8 not null,
    designation varchar(4000),
    name varchar(4000),
    update_date timestamp,
    primary key (id)
);