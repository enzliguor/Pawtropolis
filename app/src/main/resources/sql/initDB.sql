CREATE TABLE item
(
    id             serial PRIMARY KEY,
    name           VARCHAR(255),
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
    name        VARCHAR(255) UNIQUE
);

CREATE TABLE room
(
    id   serial PRIMARY KEY,
    type VARCHAR(255),
    name VARCHAR(255),
    key INT,
    FOREIGN KEY (key)
        REFERENCES item (id)
);

CREATE TABLE items_in_bag
(
    id_item INT NOT NULL,
    id_bag  INT NOT NULL,
    quantity  INT,
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
    quantity  INT,
    PRIMARY KEY (id_item, id_room),
    FOREIGN KEY (id_item)
        REFERENCES item (id),
    FOREIGN KEY (id_room)
        REFERENCES room (id)
);
CREATE TABLE linked_rooms
(
    id_room           INT NOT NULL,
    cardinal_point VARCHAR(255) NOT NULL,
    id_adjacent_room  INT NOT NULL,
    PRIMARY KEY (id_room, cardinal_point),
    FOREIGN KEY (id_room)
        REFERENCES room (id),
    FOREIGN KEY (id_adjacent_room)
        REFERENCES room (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
CREATE TABLE animal
(
    id            serial PRIMARY KEY,
    species        VARCHAR(255),
    name          VARCHAR(255),
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
    id_animal     INT,
    id_room       INT,
    PRIMARY KEY (id_animal, id_room),
    FOREIGN KEY (id_room)
        REFERENCES room (id),
    FOREIGN KEY (id_animal)
        REFERENCES animal (id)
);

CREATE TABLE game_session
(
    id            serial PRIMARY KEY,
    session_name  VARCHAR(255),
    id_player     INT,
    id_room       INT,
    id_bag      INT NOT NULL,
    life_points INT,
    last_save     TIMESTAMP,
    FOREIGN KEY (id_room)
        REFERENCES room (id),
    FOREIGN KEY (id_player)
        REFERENCES player (id),
    FOREIGN KEY (id_bag)
        REFERENCES bag (id)
);
