CREATE TABLE trainingSchedule(
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL,
    description text,
    trainingsIds SERIAL[] NOT NULL
)