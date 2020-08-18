create table se.apply
(
    aid    int auto_increment
        primary key,
    time   timestamp not null,
    id     int       not null,
    tid    int       not null,
    status int       not null,
    constraint Apply_Team_tid_fk
        foreign key (tid) references se.team (tid),
    constraint Apply_User_id_fk
        foreign key (id) references se.user (id)
);

