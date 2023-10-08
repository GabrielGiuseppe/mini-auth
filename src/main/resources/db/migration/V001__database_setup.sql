create table card
(
    id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    number   BIGINT       NOT NULL,
    password VARCHAR(100) NOT NULL,
    balance  BIGINT       NOT NULL
)