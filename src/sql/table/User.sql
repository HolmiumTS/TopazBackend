create table User
(
	id int auto_increment,
	name nvarchar(20) default '未命名' not null,
	password nvarchar(20) default '123456' null,
	email nvarchar(30) null,
	latestDoc nvarchar(200) null,
	tel nvarchar(11) not null,
	avatar nvarchar(200) null,
	constraint User_pk
		primary key (id)
);

create unique index User_tel_uindex
	on User (tel);
create unique index User_email_uindex
    on User (email);
alter table User AUTO_INCREMENT=100000000
