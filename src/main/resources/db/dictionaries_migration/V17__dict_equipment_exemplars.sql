create table dict_equipment_exemplar
(
  id                   int8       not null,
  designation        varchar(255),
  name      varchar(255),
  place_name          varchar(255),
  inventory_number     varchar(50),
  department_id         int8,
  department_code     varchar(50),
  building_number     varchar(50),
  room_number     varchar(50),
  notes         varchar(50),
  id_area         int8,
  name_area     varchar(50),
  update_date     timestamp,
  primary key (id)
);
