CREATE TABLE session(
    uuid uuid PRIMARY KEY NOT NULL,
    userid SERIAL NOT NULL,
    dateExpiring timestamp NOT NULL
)