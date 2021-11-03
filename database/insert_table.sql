use newservlet;

insert into role(code,name) values('ADMIN','ADMIN');
insert into role(code,name) values('USER','USER');
insert into role(code,name) values('ADMIN_TONG','ADMIN_T');

insert into user(username,password,fullname,status, roleid) values('admin','123456','admin',1,1);
insert into user(username,password,fullname,status, roleid) values('nguyenvana','123456','nguyen van a',1,2);
insert into user(username,password,fullname,status, roleid) values('nguyenvanb','123456','nguyen van b',0,2);
insert into user(username,password,fullname,status, roleid) values('vungochai','123456','Vũ Ngọc Hải',1,3);
