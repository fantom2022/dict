ALTER TABLE "dict_production_directive"
  RENAME COLUMN "update_date" to "created_date";

ALTER TABLE "dict_production_directive"
  ADD COLUMN "modified_date" TIMESTAMP;

ALTER TABLE "dict_production_directive"
  ADD COLUMN "modified_by" int8;

ALTER TABLE "dict_production_directive"
  ADD COLUMN "created_by" int8;


