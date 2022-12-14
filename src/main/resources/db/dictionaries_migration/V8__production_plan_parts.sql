create table dict_production_plan_part (
    id int8 not null,
    part int8,
    part_name varchar(255),
    short_part_name varchar(255),
    update_date timestamp,
    primary key (id)
);