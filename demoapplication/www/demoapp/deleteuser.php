<?php
// Start the session
session_start();

if (!isset($_SESSION['id'])){

	header("location:index.php");
	exit;
}

if(isset($_SESSION['role'])){
	if($_SESSION['role'] != 1){
		header("location:index.php");
	}
}
else{
	header("location:index.php");

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
		
		$userName = $_POST['username'];
		
		$sql = "SELECT id FROM users WHERE username = \"" . $userName."\"";
		
		echo $sql;
		$result = $file_db->query($sql);
		$resultArray = $result->fetchAll();
		$nbResults =  count($resultArray);
		
		if ($nbResults > 0) {
			
			$userId = $resultArray[0]['id'];
				
			$sql2 = "DELETE FROM users WHERE id = \"" . $userId."\"";
			echo $sql2;
			$result = $file_db->query($sql2);
			$_SESSION["delusersuccess"] = 1;
			
		}
		else{
			$_SESSION["delusersuccess"] = 0;
		}

		header("location:admin.php");
		
	}
	catch(PDOException $e) {
	// Print PDOException message
	echo $e->getMessage();
	}
?>

</body>
</html>