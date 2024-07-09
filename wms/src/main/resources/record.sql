create table record (
    id int not null primary key,
    goods int not null,
    userId int default null,
    adminId int default null,
    count int default null,
    createTime datetime default null,
    note varchar(1000) default null
)
;