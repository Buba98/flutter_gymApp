CREATE TABLE subscription(
    id SERIAL NOT NULL PRIMARY KEY,
    mouthDuration smallint NOT NULL,
    cost money NOT NULL,
    maxEntrance smallint
)