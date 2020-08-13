create table team
(
    tid   int auto_increment
        primary key,
    name  varchar(20) charset utf8  not null,
    owner int                       null,
    info  varchar(200) charset utf8 null,
    constraint team_user_id_fk
        foreign key (owner) references user (id)
);

