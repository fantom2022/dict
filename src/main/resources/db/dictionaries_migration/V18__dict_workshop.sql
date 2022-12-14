create table dict_workshop
(
  id int8 not null,
  code varchar(255),
  department_id int8,
  is_archived boolean,
  primary key (id)
);

create table dict_workshop_department
(
  workshop_id int8 not null,
  department_id int8 not null,
  primary key (workshop_id, department_id)
);
