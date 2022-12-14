drop sequence if exists storehouse_seq;
alter table dict_storehouse drop date_creation;
alter table dict_storehouse drop date_last_update;
alter table dict_storehouse drop user_create;
alter table dict_storehouse drop user_last_update;
alter table dict_storehouse add storehouse_type_id int8;
