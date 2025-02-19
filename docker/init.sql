CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

INSERT INTO account (first_name, last_name) VALUES ('Jan', 'Kowalski');
INSERT INTO account (first_name, last_name) VALUES ('Paweł', 'Wolski');
