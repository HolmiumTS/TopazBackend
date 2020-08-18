create table se.deldoc
(
    did      int       not null,
    time     timestamp null,
    executor int       not null,
    constraint DelDoc_Doc_did_fk
        foreign key (did) references se.doc (did),
    constraint DelDoc_User_id_fk
        foreign key (executor) references se.user (id)
);

