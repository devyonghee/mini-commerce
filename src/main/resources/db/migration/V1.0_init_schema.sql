CREATE TABLE product
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price       BIGINT       NOT NULL,
    created_at  DATETIME     NOT NULL,
    created_by  varchar(255) NOT NULL,
    updated_at  DATETIME     NOT NULL,
    updated_by  varchar(255) NOT NULL,
);

CREATE TABLE orders
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    created_at DATETIME     NOT NULL,
    created_by varchar(255) NOT NULL,
    created_at DATETIME     NOT NULL,
    created_by varchar(255) NOT NULL,
    updated_at DATETIME     NOT NULL,
    updated_by varchar(255) NOT NULL,
);

CREATE TABLE order_product
(
    product_id BIGINT       NOT NULL,
    quantity   BIGINT       NOT NULL,
    order_id   BIGINT       NOT NULL,
    created_at DATETIME     NOT NULL,
    created_by varchar(255) NOT NULL,
)


CREATE TABLE member
(
    product_id BIGINT       NOT NULL,
    quantity   BIGINT       NOT NULL,
    order_id   BIGINT       NOT NULL,
    created_at DATETIME     NOT NULL,
    created_by varchar(255) NOT NULL,
)