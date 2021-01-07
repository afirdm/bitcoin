DROP TABLE IF EXISTS BITCOIN;

create table BITCOIN
(
    id          long        not null primary key,
    lprice      double      not null,
    curr1       varchar(50) not null,
    curr2       varchar(50) not null,
    create_date TIMESTAMP not null
);
