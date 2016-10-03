show databases;

create table user(

ID int primary key auto_increment,

name varchar(50),

password varchar(50),

email varchar(50),

isAdministrator boolean

);

use login;



create table file(

id int primary key auto_increment,

userId int,

name varchar(50),

type varchar(50),

size double(16,2)

);

use file

insert into user values(1,'ralf','123','2669813603@qq.com',true);