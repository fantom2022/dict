CREATE TABLE "dict_waste" (
    "id" int8 NOT NULL,
    "product_id" int8,
    "waste" int8,
    "witness_count" int8,
    "destroy_control_count" int8,
    "set_up_equipment_count" int8,
    "optimal_production_batch" int8,
    "update_date" timestamp
)
;

ALTER TABLE "dict_waste" ADD CONSTRAINT dict_waste_pk PRIMARY KEY (id);