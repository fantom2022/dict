alter table dict_reference
   add short_name varchar(255);

alter table dict_reference
   rename column name to full_name ;
