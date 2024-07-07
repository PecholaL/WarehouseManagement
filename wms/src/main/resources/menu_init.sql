create table menu
(
    id int(11) not null primary key,
    menuCode varchar(8) default null,
    menuName varchar(16) default null,
    menuLevel varchar(2) default null,
    menuParentCode varchar(8) default null,
    menuClick varchar(16) default null,
    menuRight varchar(16) default null,
    menuComponent varchar(200) default null,
    menuIcon varchar(100) default null
)
;