create table dict_code_direction
(
  id                   int8       not null,
  code        int8,
  department     int8,
  name          varchar(255),
  short_name     varchar(50),
  type_code      int8,
  primary key (id)
);

create table dict_code_composition
(
  id                   bigint       not null,
  code_id        int8,
  code_id_vh     int8,
  update_user          varchar(255),
  update_date     timestamp,
  primary key (id)
);