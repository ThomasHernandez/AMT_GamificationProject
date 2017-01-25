<?php

/********************************************

Script de creation de la base de donnees et
ajout de donnees de test.

********************************************/
// Set default timezone
  date_default_timezone_set('UTC');
 
  try {
		
		$file_db = new PDO('sqlite:../databases/messengerDatabase.sqlite');
		// Set errormode to exceptions
		$file_db->setAttribute(PDO::ATTR_ERRMODE, 
								PDO::ERRMODE_EXCEPTION); 
	 
		/**************************************
		* Create tables                       *
		**************************************/
	 
		$file_db->exec("
						DROP TABLE IF EXISTS users;
						DROP TABLE IF EXISTS messages;
						
						CREATE TABLE IF NOT EXISTS users (
							id INTEGER PRIMARY KEY,
							username TEXT,
							password TEXT,
							role INTEGER,
							active INTEGER,
							CONSTRAINT username_unique UNIQUE (username)

						);
						
						CREATE TABLE IF NOT EXISTS messages (
							id INTEGER PRIMARY KEY, 
							sender INTEGER NOT NULL, 
							receiver INTEGER NOT NULL,
							subject TEXT,
							message TEXT, 
							sendDate TEXT,
							FOREIGN KEY(sender) REFERENCES users(id),
							FOREIGN KEY(receiver) REFERENCES users(id)
						);
						
						
						
						"); 
		echo "Tables created!\n";

		/**************************************
		* Set initial data                    *
		**************************************/
          
		$users = array(
				  array(
					'username' => 'admin@sti.ch',
					'password' => 'admin',
					'role' => 1,
					'active' => 1),
				  array(
					'username' => 'alice@sti.ch',
					'password' => '1234',
					'role' => 0,
					'active' => 1),
				  array(
					'username' => 'bob@sti.ch',
					'password' => '4567',
					'role' => 0,
					'active' => 0),
				  
				);

		foreach($users as $user){

			$username = $user['username'];
			$password = $user['password'];
			$role = $user['role'];
			$active = $user['active'];
			

			$sql = "INSERT INTO users VALUES (NULL, \"".$username."\", \"".$password."\",\"".$role."\",\"".$active."\")";
			$file_db->exec($sql);
		}
		echo "Initial data has been set";
   
		$messages = array(
				  array('sender' => '1',
					'receiver' => '2',
					'subject' => 'Test!',
				        'body' => 'Just testing...',
				        'time' => 1327301464),
				  array('sender' => '2',
					'receiver' => '1',
					'subject' => 'Answer to Test!',
				        'body' => 'Just answering...',
				        'time' => 1327307777),
				  array('sender' => '1',
					'receiver' => '2',
					'subject' => 'Test seems to work!',
				        'body' => 'Cool story bro...',
				        'time' => 1327309999),
				  
				);

		foreach($messages as $message){

			$senderId = $message['sender'];
			$receiverId = $message['receiver'];
			$subject = $message['subject'];
			$body = $message['body'];
			$date = $message['time'];

			$sql = "INSERT INTO messages VALUES (NULL, \"".$senderId."\", \"".$receiverId."\",\"".$subject."\",\"".nl2br($body)."\",\"".$date."\")";
			$file_db->exec($sql);
		}
		echo "Initial data has been set";

		
	}
	catch(PDOException $e) {
	// Print PDOException message
	echo $e->getMessage();
	}
?>

