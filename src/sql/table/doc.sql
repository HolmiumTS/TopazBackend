create table se.doc
(
    did      int auto_increment
        primary key,
    name     varchar(50) charset utf8 default '未命名' null,
    owner    int                                    not null,
    team     int                                    not null,
    view     tinyint(1)                             not null,
    edit     int                                    not null,
    `create` timestamp                              not null,
    `update` timestamp                              null,
    count    int                      default 0     null,
    content  longtext                               null,
    isdel    tinyint(1)                             not null,
    islocked tinyint(1)               default 0     not null,
    constraint Doc_User_id_fk
        foreign key (owner) references se.user (id)
);

create index Doc_Team_tid_fk
    on se.doc (team);

