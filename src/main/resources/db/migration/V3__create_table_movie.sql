CREATE TABLE movie (
  id serial PRIMARY KEY,
  title varchar(255) NOT NULL,
  description text,
    rating numeric,
    release_date date,
    created_at timestamp,
    updated_at timestamp
);