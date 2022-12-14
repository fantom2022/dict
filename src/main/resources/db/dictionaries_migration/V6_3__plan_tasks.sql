create table dict_plan_task (
    id int8 not null,
    category varchar(255),
    parent_id int8,
    quantity int8,
    launch_date timestamp,
    release_date timestamp,
    note varchar(1000),
    closing_date timestamp,
    is_closed boolean,
    customer varchar(255),
    priority_id int8,
    priority int8,
    plantask_type_id int8,
    plantask_type varchar(255),
    position_number int8,
    parent_position_number int8,
    plantask_kind_id int8,
    plantask_kind varchar(255),
    plantask_status_id int8,
    plantask_status varchar(255),
    custom_order_id int8,
    custom_order_number int8,
    delivery_department_id int8,
    delivery_department varchar(255),
    designation varchar(255),
    litera varchar(255),
    designation_analog varchar(255),
    update_date timestamp,
    primary key (id)
);




