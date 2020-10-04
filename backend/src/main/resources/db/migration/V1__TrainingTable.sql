CREATE TABLE training(
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL,
    description text,
    exercisesInTrainingIds SERIAL[][] NOT NULL
)