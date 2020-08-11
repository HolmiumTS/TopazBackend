create table U_T
(
    user int not null,
    team int not null,
    isAdmin bool not null,
    constraint U_T_Team_tid_fk
        foreign key (team) references Team (tid),
    constraint U_T_User_id_fk
        foreign key (user) references User (id)
);

