create table message
(
    mid           int auto_increment
        primary key,
    sender        int  not null,
    sender_type   int  not null,
    receiver      int  not null,
    receiver_type int  not null,
    content       text not null
);

