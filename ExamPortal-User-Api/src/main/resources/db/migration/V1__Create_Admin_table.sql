CREATE TABLE admin
(
    id         BIGINT            NOT NULL AUTO_INCREMENT,
    acc_status BIT               NOT NULL,
    address_line1  VARCHAR(50),
    address_line2  VARCHAR(50),
    date_stamp  datetime         NOT NULL,
    email      VARCHAR(40)       NOT NULL,
    mobile     VARCHAR(20),
    name       VARCHAR(100)      NOT NULL,
    password   VARCHAR(100)      NOT NULL,
    pincode     VARCHAR(255),
    role        VARCHAR(20)     NOT NULL,
    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT UNIQUE (email)
);