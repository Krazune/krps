/*
	MIT License

	Copyright (c) 2021 Miguel Sousa

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
*/
CREATE EXTENSION IF NOT EXISTS citext;

CREATE OR REPLACE FUNCTION kprs_compute_outcome(choice1 CHARACTER(1), choice2 CHARACTER(1))
RETURNS CHARACTER(1)
LANGUAGE PLPGSQL
IMMUTABLE
AS
$$
BEGIN
	IF choice1 ILIKE 'r' THEN
		IF choice2 ILIKE 'r' THEN
			RETURN 'd';
		END IF;

		IF choice2 ILIKE 'p' THEN
			RETURN 'l';
		END IF;

		IF choice2 ILIKE 's' THEN
			RETURN 'w';
		END IF;

		RAISE EXCEPTION 'invalid parameter: %', choice2;
	END IF;

	IF choice1 ILIKE 'p' THEN
		IF choice2 ILIKE 'r' THEN
			RETURN 'w';
		END IF;

		IF choice2 ILIKE 'p' THEN
			RETURN 'd';
		END IF;

		IF choice2 ILIKE 's' THEN
			RETURN 'l';
		END IF;

		RAISE EXCEPTION 'invalid parameter: %', choice2;
	END IF;

	IF choice1 ILIKE 's' THEN
		IF choice2 ILIKE 'r' THEN
			RETURN 'l';
		END IF;

		IF choice2 ILIKE 'p' THEN
			RETURN 'w';
		END IF;

		IF choice2 ILIKE 's' THEN
			RETURN 'd';
		END IF;

		RAISE EXCEPTION 'invalid parameter: %', choice2;
	END IF;

	RAISE EXCEPTION 'invalid parameter: %', choice1;
END;
$$;

CREATE TABLE IF NOT EXISTS users(
	id SERIAL PRIMARY KEY,
	username CITEXT UNIQUE NOT NULL,
	password_hash VARCHAR(256) NOT NULL,
	creation_date TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS games(
	id SERIAL PRIMARY KEY,
	user_id INT,
	user_choice CHARACTER(1) NOT NULL CHECK(user_choice IN('r', 'p', 's')),
	computer_choice CHARACTER(1) NOT NULL CHECK(computer_choice IN('r', 'p', 's')),
	outcome CHARACTER(1) GENERATED ALWAYS AS (kprs_compute_outcome(user_choice, computer_choice)) STORED,
	creation_date TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
	FOREIGN KEY(user_id) REFERENCES users(id)
);