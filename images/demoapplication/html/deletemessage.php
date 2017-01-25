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
		// Set session variables

		$messageId = $_POST["messageid"];

		$sql2 = "DELETE FROM messages WHERE id = \"" .$messageId."\" AND receiver = \"" .$_SESSION['id']."\"";
		echo $sql2;
		$result2 = $file_db->query($sql2);
		
		
		echo "Message deleted";
		
		header("location:user.php");
		
	}
	catch(PDOException $e) {
		// Print PDOException message
		echo $e->getMessage();
	}
	
	
	
?>

</body>
</html>
