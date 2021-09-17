CREATE TABLE users
(
	id SERIAL UNIQUE,
	name VARCHAR(32) UNIQUE NOT NULL,
	password_hash VARCHAR(256) NOT NULL,
	created_date TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE TABLE games
(
	id SERIAL UNIQUE,
	user_id INT,
	user_choice CHARACTER(1) NOT NULL CHECK (user_choice IN ('r', 'p', 's')),
	result CHARACTER(1) NOT NULL CHECK (result IN ('w', 'l', 'd')),
	created_date TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
	FOREIGN KEY (user_id) REFERENCES users(id)
);