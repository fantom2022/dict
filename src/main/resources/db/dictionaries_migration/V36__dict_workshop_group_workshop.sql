create table dict_workshop_group
(
    id    bigint not null
            primary key,
    title varchar(255),
    archive   boolean default false
);

create table dict_workshop_group_workshop
(
    id                bigint not null
            primary key,
    workshop_id       bigint not null,
    workshop_group_id bigint not null,
    archive   boolean default false
);