create table u_t
(
    user int not null,
    team int not null,
    isAdmin bool not null,
    constraint u_t_pk
        unique (user, team),
    constraint u_t_team_tid_fk
        foreign key (team) references team (tid),
    constraint u_t_user_id_fk
        foreign key (user) references user (id)
);

