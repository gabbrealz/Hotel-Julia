CREATE SCHEMA users;

CREATE TABLE users."user" (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    phone_number TEXT NOT NULL,
    address TEXT,
    role TEXT NOT NULL,
    CHECK (role = ANY(ARRAY[
        'GUEST'::TEXT,'MANAGER'::TEXT,'RECEPTIONIST'::TEXT,'HOUSEKEEPING'::TEXT,'MAINTENANCE'::TEXT
    ]))
);

CREATE TABLE users.staff (
    id BIGSERIAL PRIMARY KEY,
    salary NUMERIC(11,2),
    hire_date DATE NOT NULL,
    CONSTRAINT staff_id_fkey
        FOREIGN KEY (id)
        REFERENCES users."user"(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE users.guest (
    id BIGSERIAL PRIMARY KEY,
    pay_method TEXT,
    billing_address TEXT,
    creation_date DATE NOT NULL DEFAULT CURRENT_DATE,
    CONSTRAINT guest_id_fkey
        FOREIGN KEY (id)
        REFERENCES users."user"(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);