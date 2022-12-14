alter table "dict_workshop_group"
  add column "created_date" timestamp;

alter table "dict_workshop_group"
  add column "modified_date" timestamp;

alter table "dict_workshop_group"
  add column "modified_by" int8;

alter table "dict_workshop_group"
  add column "created_by" int8;