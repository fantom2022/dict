alter table dict_unit_measure rename column classifier_code to okei_code;
comment on column dict_unit_measure.okei_code is 'Код по Общероссийскому Классификатору Единиц Измерения';