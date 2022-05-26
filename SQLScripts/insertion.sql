USE TEXT_ADVENTURE;

TRUNCATE MAIN_HISTORY;

INSERT INTO MAIN_HISTORY VALUES(1, 'Uuh... Ooh.. Whats happening?*Everything is dark and you don\'t know where you are', 'Look around', 'Look around', 2, 2, NULL);
INSERT INTO MAIN_HISTORY VALUES(2, 'What is this place, it seems like cell in a dungeon<br>Everything is dark and silent,*the only source of light is a tiny gap in the ceiling', 'Go close to the door', 'Go close to the door', 3, 3, NULL);
INSERT INTO MAIN_HISTORY VALUES(3, 'There\'s no one around, and the door is closed,*maybe there is a rock here that you could use to open the gate', 'Search in the cell', 'Search in the cell', 4, 4, NULL);
INSERT INTO MAIN_HISTORY VALUES(4, 'Yes, got it, you grab a rock and break the padlock that was blocking he door*You look around and spot two paths', 'Go straight', 'Go right', 5, 6, NULL);
INSERT INTO MAIN_HISTORY VALUES(5, 'You end up in a hall, with only a faint light from a few torches*You see another door in front of you', 'Pass the door', 'Look around the room', 7, 8, NULL);
INSERT INTO MAIN_HISTORY VALUES(6, 'Theres nothing here but a wall, mmm how strange', 'Go back, and go the other path', 'Go back, and go the other path', 5, 5, NULL);
INSERT INTO MAIN_HISTORY VALUES(7, 'You pass the door and are now outside', 'Escape', 'Escape', NULL, NULL, NULL);
INSERT INTO MAIN_HISTORY VALUES(8, 'Fuck, a guard enters the room and sees you', 'Fight him', 'Open the door you saw and run', 9, 11, NULL);
INSERT INTO MAIN_HISTORY VALUES(9, 'Combat Node', '', '', 10, 11, NULL);
INSERT INTO MAIN_HISTORY VALUES(10, 'The guard stabs you in the chest and kills you, definitely not a good idea', 'End', 'End', NULL, NULL, NULL);
INSERT INTO MAIN_HISTORY VALUES(11, 'Yes, you see light, time to run as fast as you can', 'Escape', 'Escape', NULL, NULL, NULL);




