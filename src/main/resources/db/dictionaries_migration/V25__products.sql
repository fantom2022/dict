DROP TABLE IF EXISTS "dict_product";
CREATE TABLE "dict_product" (
    "id" int8 NOT NULL,
    "designation" varchar(255),
    "name" varchar(255),
    "type_id" int8,
    "product_id_main_variant" int8,
    "inventory_number_techdocs" varchar(255)
)
;