CREATE TABLE item
(
    id             serial PRIMARY KEY,
    name           VARCHAR(50),
    slots_required INT,
    description    VARCHAR(255)
);

CREATE TABLE bag
(
    id             serial PRIMARY KEY,
    available_slot INT
);

CREATE TABLE player
(
    id          serial PRIMARY KEY,
    name        VARCHAR(50),
    life_points INT,
    id_bag      INT,
    FOREIGN KEY (id_bag)
        REFERENCES bag(id)
);

CREATE TABLE room
(
    id   serial PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE cardinal_point
(
    id   serial PRIMARY KEY,
    name VARCHAR(20)
);

CREATE TABLE items_in_bag
(
    id_item INT NOT NULL,
    id_bag  INT NOT NULL,
    PRIMARY KEY (id_item, id_bag),
    FOREIGN KEY (id_item)
        REFERENCES item (id),
    FOREIGN KEY (id_bag)
        REFERENCES bag (id)
);

CREATE TABLE items_in_room
(
    id_item INT NOT NULL,
    id_room INT NOT NULL,
    PRIMARY KEY (id_item, id_room),
    FOREIGN KEY (id_item)
        REFERENCES item (id),
    FOREIGN KEY (id_room)
        REFERENCES room (id)
);
CREATE TABLE linked_room
(
    id_room           INT NOT NULL,
    id_cardinal_point INT NOT NULL,
    id_adjacent_room  INT NOT NULL,
    PRIMARY KEY (id_room, id_cardinal_point),
    FOREIGN KEY (id_room)
        REFERENCES room (id),
    FOREIGN KEY (id_cardinal_point)
        REFERENCES cardinal_point (id),
    FOREIGN KEY (id_adjacent_room)
        REFERENCES room (id)
);
CREATE TABLE animal
(
    id            serial PRIMARY KEY,
    specie        VARCHAR(50),
    name          VARCHAR(50),
    favorite_food VARCHAR(255),
    age           INT,
    join_date     DATE,
    weight        FLOAT,
    height        FLOAT,
    tail_length   FLOAT,
    wingspan      FLOAT
);

CREATE TABLE animals_in_room
(
    id_animal INT NOT NULL,
    id_room   INT NOT NULL,
    PRIMARY KEY (id_animal, id_room),
    FOREIGN KEY (id_animal)
        REFERENCES animal (id),
    FOREIGN KEY (id_room)
        REFERENCES room (id)
);