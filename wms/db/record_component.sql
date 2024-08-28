-- 子查询从记录的用户id获取用户名
-- 联表查询获取货物类型名和仓库名
select r.*, g.name as goodsname, s.name as storagename, gt.name as goodstypename, 
(select u.name from user u where u.id=r.userId) as username, 
(select u.name from user u where u.id=r.adminId) as adminname
from record r, goods g, storage s, goodsType gt
where r.goods=g.id
and g.storage=s.id
and g.goodsType=gt.id
;
