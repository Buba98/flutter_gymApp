CREATE TABLE "userTrainingSchedule"(
    id     SERIAL  NOT NULL PRIMARY KEY,
    userid SERIAL  NOT NULL,
    trainingScheduleId SERIAL NOT NULL,
    startDate date NOT NULL,
    endDate date NOT NULL,
    comment text
)