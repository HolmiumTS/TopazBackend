create table se.template
(
    temid   int auto_increment
        primary key,
    owner   int      not null,
    content longtext null,
    name    text     null,
    constraint template_user_id_fk
        foreign key (owner) references se.user (id)
);

