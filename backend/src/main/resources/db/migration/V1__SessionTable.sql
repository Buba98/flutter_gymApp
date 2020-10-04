CREATE TABLE session(
    uuid uuid PRIMARY KEY NOT NULL,
    userId SERIAL NOT NULL,
    dateExpiring timestamp NOT NULL
)