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
		$file_db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION); 
	 
		
		echo "Connected successfully";
		
		$username = $_POST['username'];
		$password = $_POST['password'];
		
		$role = 0;
		$active = 0;
		
		if(isset($_POST['role'])){
		
			$role = 1;
		}
		
		if(isset($_POST['active'])){
		
			$active = 1;
		}

		$sql = "INSERT INTO users VALUES (NULL, \"".$username."\", \"".$password."\",\"".$role."\",\"".$active."\")";
		echo $sql;
		$result = $file_db->query($sql);
		
		echo "User added";
		$_SESSION['useraddsuccess'] = 1;
		
		header("location:admin.php");

	}
	catch(PDOException $e) {
	// Print PDOException message
	echo $e->getMessage();
	}


?>

</body>
</html>
