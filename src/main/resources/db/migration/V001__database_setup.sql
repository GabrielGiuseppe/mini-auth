create table card
(
    id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    number   VARCHAR(19)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    balance  FLOAT       NOT NULL
)