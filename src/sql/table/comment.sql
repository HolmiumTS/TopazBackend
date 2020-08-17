create table se.comment
(
    cid     int auto_increment
        primary key,
    did     int  not null,
    content text not null,
    uid     int  null,
    constraint comment_doc_did_fk
        foreign key (did) references se.doc (did),
    constraint comment_user_id_fk
        foreign key (uid) references se.user (id)
);

