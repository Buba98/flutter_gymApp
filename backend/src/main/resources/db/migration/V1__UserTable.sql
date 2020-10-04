CREATE TABLE "user" (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    fiscalCode VARCHAR(20) UNIQUE,
    birthday date,
    password VARCHAR(100),
    phoneNumber VARCHAR(20)
)