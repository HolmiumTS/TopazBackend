create table se.message
(
    mid      int auto_increment
        primary key,
    sender   int default -1 not null,
    receiver int            not null,
    content  text           not null,
    status   int default 0  null,
    time     timestamp      null
);

