create table deldoc
(
    did      int       not null,
    time     timestamp null,
    executor int       not null,
    constraint deldoc_doc_did_fk
        foreign key (did) references doc (did),
    constraint deldoc_user_id_fk
        foreign key (executor) references user (id)
);

