create table goods (
    id int auto_increment primary key, 
    name varchar(100) not null, 
    storage int not null, 
    goodsType int not null, 
    count int(11) default null,
    note varchar(1000) default null
)
;