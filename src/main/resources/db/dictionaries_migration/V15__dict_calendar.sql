create table dict_calendar (
    id int8 not null,
    update_date timestamp,
    date timestamp,
    type int2 not null,
    primary key (id)
);