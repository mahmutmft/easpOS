-- WAITER
CREATE TABLE waiter (
                        id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        username VARCHAR(100) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL
);

-- ITEM
CREATE TABLE item (
                      id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      price NUMERIC(10,2) NOT NULL,
                      description VARCHAR(255),
                      image_path VARCHAR(255)
);