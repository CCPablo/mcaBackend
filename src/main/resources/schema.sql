create table VIDEOGAME
(
    ID    int          not null AUTO_INCREMENT,
    TITLE varchar(100) not null,
    PRIMARY KEY (ID)
);

create table PROMOTION
(
    ID           int not null AUTO_INCREMENT,
    VALID_FROM   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRICE        numeric(5, 2),
    VIDEOGAME_ID int not null,
    PRIMARY KEY (ID),
    CONSTRAINT fk_VIDEOGAME_ID_PROMOTION
        FOREIGN KEY (VIDEOGAME_ID)
            REFERENCES VIDEOGAME (ID)
);

create table STOCK
(
    ID      INT        not null AUTO_INCREMENT,
    AVAILABILITY boolean,
    LAST_UPDATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    VIDEOGAME_ID int        not null,
    PRIMARY KEY (ID),
    CONSTRAINT fk_VIDEOGAME_ID_STOCK
        FOREIGN KEY (VIDEOGAME_ID)
            REFERENCES VIDEOGAME (ID)
);

CREATE TABLE SAGA
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE VIDEOGAME_SAGA
(
    VIDEOGAME_ID INT NOT NULL,
    SAGA_ID INT NOT NULL,
    PRIMARY KEY (VIDEOGAME_ID, SAGA_ID),
    FOREIGN KEY (VIDEOGAME_ID) REFERENCES VIDEOGAME (ID),
    FOREIGN KEY (SAGA_ID) REFERENCES SAGA (id)
);





