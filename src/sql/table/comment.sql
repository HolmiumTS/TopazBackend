create table comment
(
    cid     int auto_increment
        primary key,
    did     int       not null,
    content text      not null,
    uid     int       null,
    time    timestamp null
);

