CREATE SEQUENCE IF NOT EXISTS car_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    CACHE 1;

CREATE TABLE IF NOT EXISTS cars
(
    id            INTEGER NOT NULL DEFAULT nextval('car_id_seq') PRIMARY KEY,
    brand         VARCHAR(40) NOT NULL,
    model         VARCHAR(40) NOT NULL,
    fuel_type     VARCHAR(20) NOT NULL,
    num_of_doors  INTEGER,
    price         NUMERIC(10,2) NOT NULL,
    creation_date TIMESTAMP,
    last_modified TIMESTAMP
);