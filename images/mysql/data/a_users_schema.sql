DROP SCHEMA IF EXISTS users;
CREATE SCHEMA users;

USE users;

CREATE TABLE user (

	user_id int(15) NOT NULL AUTO_INCREMENT,
	user_username VARCHAR(20) UNIQUE NOT NULL,
	user_password VARCHAR(20) NOT NULL,
	user_first_name VARCHAR(50) NOT NULL,
    user_last_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id)
	
);


