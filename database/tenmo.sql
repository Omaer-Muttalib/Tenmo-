BEGIN TRANSACTION;
<<<<<<< HEAD

DROP TABLE IF EXISTS transfer, account, tenmo_user;

DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id, seq_transfer_id;

=======
DROP TABLE IF EXISTS transfer, account, tenmo_user;
DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id, seq_transfer_id;
>>>>>>> 6ff00168a9449d83dfb9e6835fb154f016e50cbb
-- Sequence to start user_id values at 1001 instead of 1
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;
CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);
-- Sequence to start account_id values at 2001 instead of 1
-- Note: Use similar sequences with unique starting values for additional tables
CREATE SEQUENCE seq_account_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;
CREATE TABLE account (
	account_id int NOT NULL DEFAULT nextval('seq_account_id'),
	user_id int NOT NULL,
	balance numeric(13, 2) NOT NULL,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_tenmo_user FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id)
);
CREATE SEQUENCE seq_transfer_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE;
  
CREATE TABLE transfer (
	transfer_id int NOT NULL DEFAULT nextval('seq_transfer_id'),
	date_and_time timestamp NOT NULL,
	from_username varchar (50) NOT NULL,
	to_username varchar (50) NOT NULL,
	transfer_amount numeric NOT NULL,
	status varchar NOT NULL,
	CONSTRAINT PK_transfer PRIMARY KEY (transfer_id),
	CONSTRAINT FK_to_username FOREIGN KEY (to_username) REFERENCES tenmo_user(username),
	CONSTRAINT FK_from_username FOREIGN KEY (from_username) REFERENCES tenmo_user(username)
	);

CREATE SEQUENCE seq_transfer_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE;

CREATE TABLE transfer (
	transfer_id int NOT NULL DEFAULT nextval('seq_transfer_id'), 
	date_and_time date NOT NULL, 
	from_username varchar (50) NOT NULL, 
	from_user_id int NOT NULL, 
	to_username varchar (50) NOT NULL, 
	to_user_id int NOT NULL, 
	transfer_amount numeric NOT NULL, 
	status varchar NOT NULL, 
	CONSTRAINT PK_transfer PRIMARY KEY (transfer_id),
	CONSTRAINT FK_to_user_id FOREIGN KEY (to_user_id) REFERENCES tenmo_user(user_id), 
	CONSTRAINT FK_to_username FOREIGN KEY (to_username) REFERENCES tenmo_user(username), 
	CONSTRAINT FK_from_user_id FOREIGN KEY (from_user_id) REFERENCES tenmo_user(user_id), 
	CONSTRAINT FK_from_username FOREIGN KEY (from_username) REFERENCES tenmo_user(username)
	);

SELECT * FROM tenmo_user;

COMMIT;

<<<<<<< HEAD

=======
>>>>>>> 6ff00168a9449d83dfb9e6835fb154f016e50cbb
