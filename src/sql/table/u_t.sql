create table se.u_t
(
    user    int        not null,
    team    int        not null,
    isAdmin tinyint(1) not null,
    constraint u_t_pk
        unique (user, team),
    constraint u_t_team_tid_fk
        foreign key (team) references se.team (tid),
    constraint u_t_user_id_fk
        foreign key (user) references se.user (id)
);

