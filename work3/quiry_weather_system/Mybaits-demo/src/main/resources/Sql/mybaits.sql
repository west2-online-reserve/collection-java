create table cities
(
    name     varchar(255) null,
    id       int          not null
        primary key,
    lat      double       null,
    lon      double       null,
    adm2     varchar(255) null,
    adm1     varchar(255) null,
    country  varchar(255) null,
    citytype varchar(255) null,
    dengji   int          null
)
    engine = InnoDB;

create table weather
(
    id      int          not null,
    fxDate  varchar(255) null,
    tempMax int          null,
    tempMin int          null,
    textDay varchar(255) null
)
    engine = InnoDB;
