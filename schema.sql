CREATE TABLE person (
    p_id int,
    fname varchar(15) NOT NULL,
    mname varchar(15),
    lname varchar(15),
    dob DATE NOT NULL,
    phone1 bigint NOT NULL,
    phone2 bigint,
    PRIMARY KEY (p_id)
);

CREATE TABLE car (
    c_id int,
    owner_id int NOT NULL,
    brand varchar(15) NOT NULL,
    model varchar(15) NOT NULL,
    make_year smallint,
    engine_no int NOT NULL UNIQUE,
    chasis_no int NOT NULL UNIQUE,
    PRIMARY KEY(c_id),
    FOREIGN KEY (owner_id) REFERENCES person(p_id)
);

CREATE TABLE workshop (
    ws_id int,
    ws_name varchar(15) NOT NULL,
    plot_no int NOT NULL UNIQUE,
    street varchar(15) NOT NULL,
    city varchar(15) NOT NULL,
    PRIMARY KEY (ws_id)
);

CREATE TABLE insurance (
    policy_no int AUTO_INCREMENT,
    c_id int NOT NULL UNIQUE,
    owner_id int NOT NULL,
    end_date DATE NOT NULL,
    renew boolean DEFAULT false,
    PRIMARY KEY (policy_no),
    FOREIGN KEY (c_id) REFERENCES car(c_id),
    FOREIGN KEY (owner_id) REFERENCES person(p_id)
);

CREATE TABLE driver (
    p_id int,
    p_name varchar(15) NOT NULL,
    phone bigint NOT NULL,
    PRIMARY KEY (p_id)
);

CREATE TABLE accident (
    claim_no int AUTO_INCREMENT,
    c_id int NOT NULL,
    owner_driven boolean NOT NULL,
    driver_id int,
    driver_age int,
    accident_date DATE,
    repair_cost int NOT NULL,
    ws_id int NOT NULL,
    PRIMARY KEY (claim_no)
);
