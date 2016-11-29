SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE users;


SET AUTOCOMMIT=0;

INSERT INTO user VALUES (NULL, "admin", "admin", "administrator", "administrator"), 
						(NULL, "user", "user", "firstname", "lastname"), 
						(NULL, "thomas", "1234", "Thomas", "Hernandez"),
						(NULL, "tony", "4567", "Antony", "Ciani");
COMMIT;

SET AUTOCOMMIT=1;