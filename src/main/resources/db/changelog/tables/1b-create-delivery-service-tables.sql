CREATE TABLE IF NOT EXISTS delivery_service.ds_address (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    postal_code VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS delivery_service.ds_customer (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(255) NOT NULL,
    address_id BIGINT NOT NULL,
    FOREIGN KEY (address_id) REFERENCES delivery_service.ds_address(id)
);

CREATE TABLE IF NOT EXISTS delivery_service.ds_time_slot (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    delivery_method VARCHAR(255) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    is_booked BOOLEAN NOT NULL,
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES delivery_service.ds_customer(id)
);

CREATE TABLE IF NOT EXISTS delivery_service.ds_delivery (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    description VARCHAR(255) NOT NULL,
    method VARCHAR(255) NOT NULL,
    time_slot_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    FOREIGN KEY (time_slot_id) REFERENCES delivery_service.ds_time_slot(id),
    FOREIGN KEY (customer_id) REFERENCES delivery_service.ds_customer(id)
);