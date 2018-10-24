-- Create Companies Table --
CREATE TABLE public.producer_company
(
    company_id integer NOT NULL,
    name text COLLATE pg_catalog."default",
    country text COLLATE pg_catalog."default",
    CONSTRAINT producer_company_pkey PRIMARY KEY (company_id)
)
-- Create Games Table --
CREATE TABLE public.game
(
    id integer NOT NULL ,
    description text COLLATE pg_catalog."default" NOT NULL,
    year integer NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    company_id integer NOT NULL,
    CONSTRAINT "Game_pkey" PRIMARY KEY (id)
)