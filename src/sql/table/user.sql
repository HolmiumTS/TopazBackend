create table user
(
	id int auto_increment,
	name nvarchar(20) default '未命名' not null,
	password nvarchar(20) default '123456' null,
	email nvarchar(30) null,
	latestDoc nvarchar(200) null,
	tel nvarchar(11) not null,
	avatar nvarchar(200) null,
	constraint user_pk
		primary key (id)
);

create unique index user_tel_uindex
	on user (tel);
create unique index user_email_uindex
    on user (email);
alter table user AUTO_INCREMENT=100000000
