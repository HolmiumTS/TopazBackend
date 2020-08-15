create table user
(
    id        int auto_increment
        primary key,
    name      varchar(20) charset utf8  default '未命名'                                                        not null,
    password  varchar(20) charset utf8  default '123456'                                                     null,
    email     varchar(30) charset utf8                                                                       null,
    latestDoc varchar(200) charset utf8                                                                      null,
    tel       varchar(11) charset utf8                                                                       not null,
    avatar    varchar(200) charset utf8 default 'http://qexiy12gt.hd-bkt.clouddn.com/%E9%BB%84%E7%8E%89.png' null,
    collect   text                                                                                           null,
    constraint User_email_uindex
        unique (email),
    constraint User_tel_uindex
        unique (tel)
);

alter table user AUTO_INCREMENT=100000000
