CREATE TABLE if not exists exerciseInTraining(
                                   id SERIAL NOT NULL PRIMARY KEY,
                                   exerciseId SERIAL NOT NULL,
                                   setsIds int[],
                                   description text
);

CREATE TABLE if not exists session(
                        uuid uuid PRIMARY KEY NOT NULL,
                        userId SERIAL NOT NULL,
                        dateExpiring timestamp NOT NULL
);

CREATE TABLE if not exists set(
                    id SERIAL NOT NULL PRIMARY KEY,
                    rest interval,
                    eccentricDuration interval,
                    concentricDuration interval,
                    setDuration interval,
                    reps smallint[]
);

CREATE TABLE if not exists trainingSchedule(
                                 id SERIAL NOT NULL PRIMARY KEY,
                                 name VARCHAR NOT NULL,
                                 description text,
                                 trainingsIds int[] NOT NULL
);

CREATE TABLE if not exists training(
                         id SERIAL NOT NULL PRIMARY KEY,
                         name VARCHAR NOT NULL,
                         description text,
                         exercisesInTrainingIds int[][] NOT NULL
);

CREATE TABLE if not exists "userSubscription"(
                                   id SERIAL NOT NULL PRIMARY KEY,
                                   userId SERIAL NOT NULL,
                                   subscriptionId SERIAL NOT NULL,
                                   entranceDone smallint DEFAULT 0,
                                   startDate date NOT NULL,
                                   endDate date NOT NULL
);

CREATE TABLE if not exists "user" (
                        id SERIAL NOT NULL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        surname VARCHAR(100) NOT NULL,
                        email VARCHAR(100) UNIQUE,
                        fiscalCode VARCHAR(20) UNIQUE,
                        birthday date,
                        password VARCHAR(100),
                        phoneNumber VARCHAR(20),
                        insurances date[],
                        owner boolean DEFAULT false
);

CREATE TABLE if not exists "userTrainingSchedule"(
                                       id     SERIAL  NOT NULL PRIMARY KEY,
                                       userid SERIAL  NOT NULL,
                                       trainingScheduleId SERIAL NOT NULL,
                                       startDate date NOT NULL,
                                       endDate date NOT NULL,
                                       comment text
);

CREATE TABLE if not exists exercise(
                         id SERIAL NOT NULL PRIMARY KEY,
                         urlVideo VARCHAR,
                         imageOrGif VARCHAR,
                         typeOfExercise SMALLINT
);

CREATE TABLE if not exists subscription(
                             id SERIAL NOT NULL PRIMARY KEY,
                             mouthDuration smallint NOT NULL,
                             cost money NOT NULL,
                             maxEntrance smallint
);