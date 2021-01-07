DROP TABLE IF EXISTS BITCOIN;

CREATE SEQUENCE SEQ_BITCOIN MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 NOCACHE;

create table BITCOIN
(
    id          long        not null primary key,
    lprice      double      not null,
    curr1       varchar(50) not null,
    curr2       varchar(50) not null,
    create_date TIMESTAMP not null
);
