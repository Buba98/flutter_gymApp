CREATE TABLE "userSubscription"(
    id SERIAL NOT NULL PRIMARY KEY,
    userId SERIAL NOT NULL,
    subscriptionId SERIAL NOT NULL,
    entranceDone smallint DEFAULT 0,
    startDate date NOT NULL,
    endDate date NOT NULL
)