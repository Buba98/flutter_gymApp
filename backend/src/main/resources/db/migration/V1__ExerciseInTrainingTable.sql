CREATE TABLE exerciseInTraining(
    id SERIAL NOT NULL PRIMARY KEY,
    exerciseId SERIAL NOT NULL,
    setsIds SERIAL[],
    description text
)