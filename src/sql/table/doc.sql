create table doc
(
    did      int auto_increment
        primary key,
    name     varchar(50) charset utf8 default '未命名' null,
    owner    int                                    not null,
    team     int                                    not null,
    view     tinyint(1)                             not null,
    edit     tinyint(1)                             not null,
    `create` timestamp                              not null,
    `update` timestamp                              null,
    count    int                      default 0     null,
    content  longtext                               null,
    isDel    tinyint(1)                             not null,
    constraint doc_team_tid_fk
        foreign key (team) references team (tid),
    constraint doc_user_id_fk
        foreign key (owner) references user (id)
);

