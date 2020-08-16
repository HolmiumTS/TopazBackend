create table u_d
(
    uid int not null,
    did int not null,
    constraint u_d_pk
        unique (uid, did),
    constraint u_d_doc_did_fk
        foreign key (did) references doc (did),
    constraint u_d_user_id_fk
        foreign key (uid) references user (id)
);