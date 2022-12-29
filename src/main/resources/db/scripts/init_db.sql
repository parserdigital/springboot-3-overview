CREATE TABLE IF NOT EXISTS cars
(
    id            VARCHAR(40) NOT NULL,
    brand         VARCHAR(40) NOT NULL,
    fuel_type     VARCHAR(40) NOT NULL,
    num_of_doors  INTEGER,
    price         BIGINT,
    creation_date TIMESTAMP,
    last_modified TIMESTAMP,
    PRIMARY KEY (id)
);