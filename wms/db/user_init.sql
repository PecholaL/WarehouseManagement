create table user
(
    id int auto_increment comment "primary key" primary key,
    no varchar(20) null comment "user id",
    name varchar(100) not null comment "name",
    password varchar(20) not null comment "password",
    age int null comment "age",
    sex int null comment "sex, 0-female, 1-male",
    phone varchar(20) null comment "phone number",
    role_id int null comment "role, 0-owner, 1-administrator, 2-user"
)
;