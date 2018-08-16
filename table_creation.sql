CREATE TABLE users (
	username VARCHAR PRIMARY KEY,
	password VARCHAR NOT NULL,
	firstname VARCHAR NOT NULL,
	lastname VARCHAR NOT NULL,
	age INTEGER NOT NULL,
	admin BOOLEAN NOT NULL
);

CREATE TABLE accounts (
    id integer NOT NULL,
    balance integer NOT NULL,
    CONSTRAINT accounts_pkey PRIMARY KEY (id)
);

CREATE TABLE hasaccounts (
	username VARCHAR REFERENCES users(username) ON DELETE CASCADE,
	id INTEGER REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE hastransactionhistory (
	id INTEGER REFERENCES accounts(id) ON DELETE CASCADE,
	history_part VARCHAR NOT NULL
);