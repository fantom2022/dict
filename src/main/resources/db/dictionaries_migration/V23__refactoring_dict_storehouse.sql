alter table dict_storehouse
    rename column storehouse_type to type;

alter table dict_storehouse
    drop column storehouse_type_id;
