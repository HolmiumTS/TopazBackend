create table se.u_d
(
    uid   int            not null,
    did   int            not null,
    dteam int default -1 null,
    constraint u_d_pk
        unique (uid, did),
    constraint u_d_doc_did_fk
        foreign key (did) references se.doc (did),
    constraint u_d_user_id_fk
        foreign key (uid) references se.user (id)
);

