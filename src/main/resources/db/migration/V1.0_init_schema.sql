CREATE TABLE product
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price       BIGINT       NOT NULL,
    created_at  DATETIME     NOT NULL,
    created_at  varchar(255) NOT NULL,
    updated_at  DATETIME     NOT NULL,
    updated_at  varchar(255) NOT NULL,
);