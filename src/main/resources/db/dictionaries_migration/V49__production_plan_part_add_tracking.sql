alter table "dict_production_plan_part"
  rename column "update_date" to "created_date";

alter table "dict_production_plan_part"
  add column "modified_date" timestamp;

alter table "dict_production_plan_part"
  add column "modified_by" int8;

  alter table "dict_production_plan_part"
  rename column "person_id" to "created_by"
