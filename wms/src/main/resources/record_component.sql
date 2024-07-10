select r.*,
(select u.name from user u where u.id=r.userId) as username,
(select u.name from user u where u.id=r.adminId) as adminname
from record r
;