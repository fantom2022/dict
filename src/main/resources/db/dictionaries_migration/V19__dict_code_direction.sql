drop table dict_code_direction;
drop table dict_code_composition;

create table dict_code_direction
(
  id         bigint not null,
  code       bigint,
  name       varchar(255),
  short_name varchar(50),
  type_code  bigint,
  primary key (id)
);

create table dict_code_composition
(
  parent_id         bigint not null,
  children_id       bigint
);