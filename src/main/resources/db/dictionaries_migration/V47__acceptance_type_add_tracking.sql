alter table "dict_acceptance_type"
  rename column "update_date" to "created_date";

alter table "dict_acceptance_type"
  add column "modified_date" timestamp;

alter table "dict_acceptance_type"
  add column "modified_by" int8;

alter table "dict_acceptance_type"
  add column "created_by" int8;