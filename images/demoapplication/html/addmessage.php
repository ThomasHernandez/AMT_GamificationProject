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
	

	$serverAdress = "192.168.99.100";
	
	// Set default timezone
  	date_default_timezone_set('UTC');

	try {
		
		$file_db = new PDO('sqlite:../databases/messengerDatabase.sqlite');
		// Set errormode to exceptions
		$file_db->setAttribute(PDO::ATTR_ERRMODE, 
								PDO::ERRMODE_EXCEPTION);
	 
		
		//echo "Connected successfully";
		// Set session variables
		$sender = $_SESSION["id"];
		$receiverName = $_POST["destinataire"];
		$subject = $_POST["subject"];
		$message = $_POST["message"];
		$date = new DateTime();
		
		$sql2 = "SELECT id FROM users WHERE username = \"" .$receiverName."\"";
		//echo $sql2;
		$result2 = $file_db->query($sql2);
		$resultArray2 = $result2->fetchAll();
		$nbResults2 =  count($resultArray2);
		
		if($nbResults2 > 0){
		
			$receiverId = $resultArray2[0]['id'];
			
			$sql = "INSERT INTO messages VALUES (NULL, \"".$sender."\", \"".$receiverId."\",\"".$subject."\",\"".nl2br($message)."\",\"".$date->getTimestamp()."\")";
			echo $sql;
			$result = $file_db->query($sql);
			
			echo "message sent";
			$_SESSION['messagesent'] = 1;
			
			// s'identifer pour récupérer token
			
			$data = array('appName'=>'demo2','appPassword'=>'toor');
			$data_json = json_encode($data);
			$url = "http://".$serverAdress.":8080/api/auth";

			//echo "/auth";
			//echo "<br/>";
			//echo "<br/>";
			
			

			$ch = curl_init();
			curl_setopt($ch, CURLOPT_URL, $url);
			curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json','Accept: text/plain'));
			curl_setopt($ch, CURLOPT_POST, 1);
			curl_setopt($ch, CURLOPT_POSTFIELDS,$data_json);
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			curl_setopt($ch, CURLOPT_VERBOSE, 1);
			curl_setopt($ch, CURLOPT_HEADER, 1);
			curl_setopt($ch, CURLINFO_HEADER_OUT, true);
			$response  = curl_exec($ch);
			$header_size = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
			$header = substr($response, 0, $header_size);
			$token = substr($response, $header_size);
			curl_close($ch);
			
			//echo "token = $token" ;
			//var_dump($header);
			
			// Ajout event pour comptage envoi messages
			$data = array('appUserId'=>$sender,'eventType'=>'plus1');
			$data_json = json_encode($data);
			$url = "http://".$serverAdress.":8080/api/events";

			$ch = curl_init();
			curl_setopt($ch, CURLOPT_URL, $url);
			curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json','Accept: */*','authToken: ' . $token));
			curl_setopt($ch, CURLOPT_POST, 1);
			curl_setopt($ch, CURLOPT_POSTFIELDS,$data_json);
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			curl_setopt($ch, CURLOPT_VERBOSE, 1);
			curl_setopt($ch, CURLOPT_HEADER, 1);
			curl_setopt($ch, CURLINFO_HEADER_OUT, true);
			
			$response  = curl_exec($ch);
			$header_size = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
			$header = substr($response, 0, $header_size);
			$body = substr($response, $header_size);

			curl_close($ch);
			
			//var_dump($header);
			//var_dump($body);
			
			// ajout event pour obtenir badge (le bade sera recu que quand la règle sera effectivement atteint)
			$data = array('appUserId'=>$sender,'eventType'=>'winMail');
			$data_json = json_encode($data);
			$url = "http://".$serverAdress.":8080/api/events";

			$ch = curl_init();
			curl_setopt($ch, CURLOPT_URL, $url);
			curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json','Accept: */*','authToken: ' . $token));
			curl_setopt($ch, CURLOPT_POST, 1);
			curl_setopt($ch, CURLOPT_POSTFIELDS,$data_json);
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			curl_setopt($ch, CURLOPT_VERBOSE, 1);
			curl_setopt($ch, CURLOPT_HEADER, 1);
			curl_setopt($ch, CURLINFO_HEADER_OUT, true);
			
			$response  = curl_exec($ch);
			$header_size = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
			$header = substr($response, 0, $header_size);
			$body = substr($response, $header_size);

			curl_close($ch);
			
			//var_dump($header);
			//var_dump($body);
			
			
			header("location:writemessage.php");
		
		}
		else{
		
			echo "user doestn exist";
			$_SESSION['messagesent'] = 0;
			
			header("location:writemessage.php");
		
		}
		
	}
	catch(PDOException $e) {
		// Print PDOException message
		echo $e->getMessage();
	}
	
	
	
?>




</body>
</html>
