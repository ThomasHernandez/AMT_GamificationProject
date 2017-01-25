<?php
// Start the session
session_start();
if (!isset($_SESSION['id'])){

	header("location:index.php");
	exit;
}

?>
<!DOCTYPE html>
<html>
<body>
<?php
	// Set default timezone
	  date_default_timezone_set('UTC');
 
  	try {
		
		$file_db = new PDO('sqlite:../databases/messengerDatabase.sqlite');
		// Set errormode to exceptions
		$file_db->setAttribute(PDO::ATTR_ERRMODE, 
								PDO::ERRMODE_EXCEPTION); 
	 
		
		echo "Connected successfully";
		
		$userId = $_SESSION["id"];
		
		$oldPassword = $_POST["oldpassword"];
		$newPassword = $_POST["newpassword1"];
		$confirmation = $_POST["newpassword2"];
		
		$sql = "SELECT password FROM users WHERE id = \"" . $userId."\"";
		
		echo $sql;
		$result = $file_db->query($sql);
		$resultArray = $result->fetchAll();
		$nbResults =  count($resultArray);
		
		if ($nbResults > 0) {
			
			$password = $resultArray[0]["password"];
			
			if($oldPassword == $password && $newPassword == $confirmation){
			
				$sql2 = "UPDATE users SET password = \"" . $newPassword."\" WHERE id = \"" . $userId."\"";
				echo $sql2;
				$result = $file_db->query($sql2);
				$_SESSION["passchangesuccess"] = 1;
				
			}
			else{
			
				$_SESSION["passchangesuccess"] = 0;
			
			}

		}
		else{
			
				$_SESSION["passchangesuccess"] = 0;
			
		}
		header('Location: account.php');

		exit();
		
	}
	catch(PDOException $e) {
	// Print PDOException message
	echo $e->getMessage();
	}
?>




</body>
</html>
