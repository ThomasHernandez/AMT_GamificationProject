<?php
// Start the session
session_start();

// Set default timezone
  date_default_timezone_set('UTC');
 
  try {
		
		$file_db = new PDO('sqlite:../databases/messengerDatabase.sqlite');
		// Set errormode to exceptions
		$file_db->setAttribute(PDO::ATTR_ERRMODE, 
								PDO::ERRMODE_EXCEPTION); 
	 
		
		// Set session variables
		$email = $_POST["email"];
		$password = $_POST["password"];
		$sql = "SELECT id,role,active FROM users WHERE username = \"" . $email."\" AND password = \"". $password."\"";

		$result = $file_db->query($sql);
		
		$resultArray = $result->fetchAll();
		$nbResults =  count($resultArray);
		
		if ($nbResults > 0) {
			
			session_unset();
			if($resultArray[0]["active"] == 1){
			
				$_SESSION['id'] = $resultArray[0]["id"];
				$_SESSION['role'] = $resultArray[0]["role"];
					

				header('Location: user.php');
			
			}
			else{
				
				$_SESSION["connerror"] = true;
				header('Location: index.php');	
			
			}
			
		} else {

			$_SESSION["connerror"] = true;
			header('Location: index.php');

		}


	}
	catch(PDOException $e) {
	// Print PDOException message
	//echo $e->getMessage();
	}
?>

