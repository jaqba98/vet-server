CREATE DATABASE vet_db;

\connect vet_db;

CREATE TABLE recordings (
  id UUID PRIMARY KEY,
  file_name TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  transcript TEXT
);
