-- Create Companies Table --
CREATE TABLE producer_company
(
    id serial NOT NULL PRIMARY KEY,
    name varchar(25),
    country varchar(20)
)
-- Create Games Table --
CREATE TABLE game
(
    id serial NOT NULL PRIMARY KEY,
    description text  NOT NULL,
    year integer NOT NULL,
    name varchar(25) NOT NULL,
    company_id integer NOT NULL,
    genre smallint NOT NULL
)