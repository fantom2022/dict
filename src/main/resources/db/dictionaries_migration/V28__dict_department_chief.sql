create table DICT_DEPARTMENT_CHIEF
(
    DEPARTMENT_ID int8  not null,
    PERSONAL_ID   int8  not null,
    CHIEF         boolean,
    primary key (DEPARTMENT_ID, PERSONAL_ID, CHIEF)
);

