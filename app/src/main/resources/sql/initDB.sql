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
    name VARCHAR(255)
);

CREATE TABLE door_state
(
    id   serial PRIMARY KEY,
    state VARCHAR(255),
    item_key INT,
    FOREIGN KEY (item_key)
        REFERENCES item (id)
);

CREATE TABLE door
(
    id   serial PRIMARY KEY,
    room_a INT,
    room_b INT,
    id_state INT,
    FOREIGN KEY (room_a)
        REFERENCES room (id),
    FOREIGN KEY (room_b)
        REFERENCES room (id),
    FOREIGN KEY (id_state)
        REFERENCES door_state (id)
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
CREATE TABLE linked_doors
(
    id_room           INT NOT NULL,
    cardinal_point VARCHAR(255) NOT NULL,
    id_door  INT NOT NULL,
    PRIMARY KEY (id_room, cardinal_point),
    FOREIGN KEY (id_room)
        REFERENCES room (id),
    FOREIGN KEY (id_door)
        REFERENCES door (id)
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

CREATE TABLE save_block
(
    id            serial PRIMARY KEY,
    block_name  VARCHAR(255),
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
