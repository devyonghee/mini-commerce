CREATE TABLE product
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price       BIGINT       NOT NULL,
    created_at  DATETIME     NOT NULL,
    created_by  VARCHAR(255) NOT NULL,
    updated_at  DATETIME     NOT NULL,
    updated_by  VARCHAR(255) NOT NULL
);

CREATE TABLE member
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    email          VARCHAR(255) NOT NULL,
    phone_number   VARCHAR(11)  NOT NULL,
    name           VARCHAR(255) NOT NULL,
    address        VARCHAR(255) NOT NULL,
    address_detail VARCHAR(255) NOT NULL,
    post_code      VARCHAR(5)   NOT NULL,
    created_at     DATETIME     NOT NULL,
    created_by     VARCHAR(255) NOT NULL,
    updated_at     DATETIME     NOT NULL,
    updated_by     VARCHAR(255) NOT NULL
);

CREATE TABLE order_product
(

    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    price      BIGINT       NOT NULL,
    product_id BIGINT       NOT NULL,
    quantity   BIGINT       NOT NULL,
    order_id   BIGINT       NOT NULL,
    created_at DATETIME     NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    constraint fk_order_product_product_id
        foreign key (product_id) references product (id)
);

CREATE TABLE orders
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    status     VARCHAR(20)  NOT NULL,
    member_id  BIGINT       NOT NULL,
    created_at DATETIME     NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATETIME     NOT NULL,
    updated_by VARCHAR(255) NOT NULL,
    constraint fk_orders_member_id
        foreign key (member_id) references member (id)
);

CREATE TABLE member_role
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    member_id BIGINT      NOT NULL,
    role      VARCHAR(20) NOT NULL,
    constraint unique_idx_member_id_role
        unique (member_id, role)
);

CREATE TABLE email_password_account
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    member_id  BIGINT       NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    constraint fk_email_password_account_member_id
        foreign key (member_id) references member (id)
);