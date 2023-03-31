INSERT INTO item (name, slots_required, description)
VALUES ('Spada di gomma', 1, 'Una spada dall''apparenza minacciosa, ma completamente in gomma!'),
       ('Borsa infinita', 2, 'Una borsa che può contenere qualsiasi cosa, purché sia più piccola della borsa stessa!'),
       ('Anello della trasparenza', 1,'Un anello che ti rende completamente trasparente, ma solo quando sei completamente nudo!'),
       ('Bacchetta del pollo', 1, 'Una bacchetta magica che trasforma chiunque tocchi in un pollo gigante!'),
       ('Scarpe antigravità', 2,'Scarpe speciali che ti permettono di camminare sul soffitto, ma solo quando sei starnutendo!'),
       ('Calzino Puzzolente', 1, 'Un calzino puzzolente che nessuno vuole'),
       ('Salsiccia Magica', 2, 'Una salsiccia magica che ti fa diventare invisibile per 2 secondi'),
       ('Fiasco di Vino Vuoto', 1, 'Un fiasco di vino completamente vuoto'),
       ('Mazza di Gomma', 3, 'Una mazza da baseball di gomma che non fa male a nessuno'),
       ('Spada di Carta', 2, 'Una spada di carta molto affilata'),
       ('Cuffie Antirumore del Vero Silenzio', 1,'Cuffie antirumore che non fanno sentire niente, nemmeno il silenzio'),
       ('Palla di Neve di Fuoco', 1, 'Una palla di neve che si trasforma in fuoco al contatto'),
       ('Calzamaglia di Lattuga', 2, 'Una calzamaglia fatta di lattuga'),
       ('Bomba della Risata', 3, 'Una bomba che fa ridere tutti per 10 minuti'),
       ('Cappello della Confusione', 1, 'Un cappello che ti fa dimenticare chi sei per un po'),
       ('Cucchiaio Telecinetico', 1, 'Un cucchiaio che ti permette di muovere oggetti con la mente');

INSERT INTO room (name)
VALUES ('Sala di Partenza'),
       ('Stanza dei Biscotti'),
       ('Sala del Tormento Matrimoniale'),
       ('Camera da letto di Malgioglio'),
       ('Cucina di Gerry Scotti');

INSERT INTO animal (specie, name, favorite_food, age, join_date, weight, height, tail_length, wingspan, id_room)
VALUES ('tiger', 'Tigger', 'steak', 5, '2018-03-01', 200.0, 1.2, 1.0, null, 1),
       ('tiger', 'Simba', 'chicken', 4, '2019-04-15', 180.0, 1.1, 1.1, null, 2),
       ('tiger', 'Tony', 'beef', 3, '2020-06-21', 150.0, 1.0, 0.9, null, 3),
       ('lion', 'Leo', 'meat', 6, '2017-02-20', 250.0, 1.3, 1.2, null, 1),
       ('lion', 'Simone', 'beef', 5, '2018-01-03', 220.0, 1.2, 1.1, null, 2),
       ('lion', 'Mufasa', 'chicken', 4, '2019-05-12', 200.0, 1.1, 1.0, null, 3),
       ('eagle', 'Aquila', 'fish', 3, '2020-10-07', 3.0, 0.68, null, 1.5, 1),
       ('eagle', 'Gloria', 'meat', 2, '2021-01-09', 2.5, 0.72, null, 1.2, 2),
       ('eagle', 'Thunder', 'fish', 1, '2022-02-14', 2.0, 0.84, null, 1.0, 3),
       ('tiger', 'Raja', 'chicken', 6, '2017-08-04', 240.0, 1.3, 1.1, null, 4),
       ('tiger', 'Khan', 'beef', 5, '2018-05-17', 210.0, 1.2, 1.0, null, 5),
       ('tiger', 'Bengal', 'steak', 4, '2019-02-14', 190.0, 1.1, 0.9, null, 1),
       ('lion', 'Nala', 'meat', 6, '2017-04-30', 240.0, 1.2, 1.1, null, 4),
       ('lion', 'Scar', 'beef', 5, '2018-03-05', 200.0, 1.1, 1.0, null, 5),
       ('lion', 'Zazu', 'chicken', 4, '2019-05-21', 170.0, 1.0, 0.9, null, 3),
       ('eagle', 'Apollo', 'fish', 3, '2020-09-18', 2.8, 0.56, null, 1.3, 4),
       ('eagle', 'Artemis', 'meat', 2, '2021-02-12', 2.3, 0.68, null, 1.1, 5),
       ('eagle', 'Ares', 'fish', 1, '2022-01-01', 1.8, 0.49, null, 0.9, 5);

INSERT INTO items_in_room (id_item, id_room, quantity)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 1),
       (4, 2, 3),
       (5, 2, 1),
       (6, 2, 2),
       (7, 3, 1),
       (8, 3, 3),
       (9, 3, 1),
       (10, 4, 2),
       (11, 4, 1),
       (12, 4, 1),
       (13, 5, 3),
       (14, 5, 2),
       (15, 5, 1),
       (16, 5, 1);

INSERT INTO linked_rooms (id_room, cardinal_point, id_adjacent_room)
VALUES (1, 'north', 2),
       (2, 'south', 1),
       (1, 'west', 3),
       (3, 'east', 1),
       (1, 'east', 4),
       (4, 'west', 1),
       (1, 'south', 5),
       (5, 'north', 1);



