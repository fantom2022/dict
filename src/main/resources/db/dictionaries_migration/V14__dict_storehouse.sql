-- ----------------------------
-- Sequence structure for hibernate_sequence
-- ----------------------------
DROP SEQUENCE IF EXISTS "storehouse_seq";
CREATE SEQUENCE "storehouse_seq"
    MINVALUE 1
    MAXVALUE 9223372036854775807
    INCREMENT BY 50
    START WITH 1
 CACHE 1;

CREATE TABLE "dict_storehouse"
(
    "id"               int8 NOT NULL,
    "code"             varchar(255),
    "name"             varchar(255),
    "date_creation"    timestamp(6),
    "date_last_update" timestamp(6),
    "user_create"      varchar(255),
    "user_last_update" varchar(255),
    "is_archived"      boolean
)
;
ALTER TABLE "dict_storehouse"
    ADD CONSTRAINT "dict_storehouse_pkey" PRIMARY KEY ("id");
ALTER TABLE "dict_storehouse"
    ADD CONSTRAINT "dict_storehouse_code_unique" UNIQUE ("code");



