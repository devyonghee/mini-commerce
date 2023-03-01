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

    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id BIGINT       NOT NULL,
    quantity   BIGINT       NOT NULL,
    order_id   BIGINT       NOT NULL,
    created_at DATETIME     NOT NULL,
    created_by varchar(255) NOT NULL,
)


CREATE TABLE member
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    email          varchar(255) NOT NULL,
    phone_number   varchar(11)  NOT NULL,
    name           varchar(255) NOT NULL,
    address        varchar(255) NOT NULL,
    address_detail varchar(255) NOT NULL,
    post_code      varchar(5)  NOT NULL,
    created_at     DATETIME     NOT NULL,
    created_by     varchar(255) NOT NULL,
    updated_at     DATETIME     NOT NULL,
    updated_by     varchar(255) NOT NULL,
)

CREATE TABLE member_role
(
    member_id BIGINT      NOT NULL,
    role      varchar(20) NOT NULL
)

CREATE TABLE email_password_account
(
    member_id  BIGINT       NOT NULL,
    email      varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    created_at DATETIME     NOT NULL,
    created_by varchar(255) NOT NULL,
)