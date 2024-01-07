CREATE TABLE CHARACTERS (
    ID UUID PRIMARY KEY,
    ACCOUNT_ID UUID NOT NULL,
    NAME VARCHAR(12) NOT NULL,
    CREATION_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    UPDATE_DATE TIMESTAMP NOT NULL
);

CREATE UNIQUE INDEX ON CHARACTERS(LOWER(NAME));