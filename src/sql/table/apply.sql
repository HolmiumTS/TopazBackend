create table apply
(
    aid    int auto_increment
        primary key,
    time   timestamp not null,
    id     int       not null,
    tid    int       not null,
    status int       not null,
    constraint apply_team_tid_fk
        foreign key (tid) references team (tid),
    constraint apply_user_id_fk
        foreign key (id) references user (id)
);

