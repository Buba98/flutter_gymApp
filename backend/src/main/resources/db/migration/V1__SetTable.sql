CREATE TABLE set(
    id SERIAL NOT NULL PRIMARY KEY,
    rest interval,
    eccentricDuration interval,
    concentricDuration interval,
    setDuration interval,
    reps smallint[]
)