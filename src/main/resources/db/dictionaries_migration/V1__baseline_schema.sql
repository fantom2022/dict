create table dict_appointment (
   id int8 not null,
    begin_date timestamp,
    department_id int8,
    end_date timestamp,
    personal_id int8,
    place_id int8,
    position_id int8,
    update_date timestamp,
    primary key (id)
);

create table dict_department (
   id int8 not null,
    close_date timestamp,
    code varchar(255),
    full_name varchar(255),
    name varchar(255),
    parent_id int8,
    place_id int8,
    type_id int8,
    update_date timestamp,
    primary key (id)
);

create table dict_person (
   id int8 not null,
    first_name varchar(255),
    last_name varchar(255),
    middle_name varchar(255),
    primary key (id)
);

create table dict_personal (
   id int8 not null,
    begin_date timestamp,
    dismissed_date timestamp,
    person_id int8,
    personal_number varchar(255),
    work_type int8,
    primary key (id)
);

create table dict_place (
   id int8 not null,
    full_name varchar(255),
    name varchar(255),
    primary key (id)
);

create table dict_position (
   id int8 not null,
    code varchar(255),
    end_date timestamp,
    name varchar(255),
    primary key (id)
);
