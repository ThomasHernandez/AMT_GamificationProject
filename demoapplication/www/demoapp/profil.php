<?php
// Start the session
session_start();

if (!isset($_SESSION['id'])){

	header("location:index.php");
	exit;
}

//--- AUTH ---

$data = array('appName'=>'demo2','appPassword'=>'toor');
$data_json = json_encode($data);
$url = "http://localhost:8080/api/auth";

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
//$responses = curl_getinfo($ch, CURLINFO_HTTP_CODE);
//$errors = curl_getinfo($ch, CURLINFO_HEADER_OUT);

$header_size = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
$header = substr($response, 0, $header_size);
$token = substr($response, $header_size);


curl_close($ch);
//var_dump($responses);
//echo "token = $token" ;
//var_dump($header);

//--- /users ---

//$data = array('name'=>'demo2','password'=>'toor');
//$data_json = json_encode($data);
$id = $_SESSION["id"];

//echo "/users";

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, "http://localhost:8080/api/users/" . $id);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Accept: application/json','authToken: ' . $token));
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
//curl_setopt($ch, CURLOPT_POSTFIELDS,$data_json);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_VERBOSE, 1);
curl_setopt($ch, CURLOPT_HEADER, 1);
$response  = curl_exec($ch);
//$responses = curl_getinfo($ch, CURLINFO_HTTP_CODE);

$header_size = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
$header = substr($response, 0, $header_size);
$body = substr($response, $header_size);


curl_close($ch);
//var_dump($responses);
var_dump(json_decode($header));
var_dump(json_decode($body));
$obj = json_decode($body);

if(!empty($obj)){
	$data = $obj->{'awardedBadges'};
	$ps = $obj->{'currentPoints'};
} else {
	$data = "";
	$ps = "";
}

$badgeName = "";
$psName = "";
if (!empty($data)) {
     foreach ($data as $value) {
		if($value->{'name'}) {
			$badgeName = $value->{'name'};
		}
		if($value->{'imageURI'}) {
			$badgeValue = $value->{'imageURI'};
		}
		if($value->{'description'}) {
			$badgeDescription = $value->{'description'};
		}
		
	}
}

if (!empty($ps)) {
     foreach ($ps as $val) {
		if($val->{'pointScaleName'}) {
			$psName = $val->{'pointScaleName'};
		}
		if($val->{'currentValue'}) {
			$psValue = $val->{'currentValue'};
		}
		
	}
}





?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>STI-Messenger</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/jumbotron.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.php">STI-Messenger</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
		  <ul class="nav navbar-nav">
            <li><a href="index.php">Accueil</a></li>

            <li class="active"><a href="user.php">Réception</a></li>
            <li><a href="writemessage.php">Envoi</a></li>
			<li><a href="account.php">Compte</a></li>
			<li><a href="profil.php">Profil</a></li>
			<?php
				if(isset($_SESSION['role'])){
				
					if($_SESSION['role'] == 1){
			?>
			<li><a href="admin.php">Admin</a></li>
			<?php
					}
				}
				
			?>
			
			
          </ul>
		  <form action="deconnexion.php" method="post" class="navbar-form navbar-right">
						<button type="submit" class="btn btn-success disabled">Connecté!</button>
						<button type="submit" class="btn btn-danger ">Deconnexion</button>
		  </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>Bienvenue dans la messagerie</h1>
        
		 
		 
		
		
      </div>
    </div>
	
	<div class="container">
		<div class="row">
		
			<h3>Profil</h3>
			
		
		</div>
		<div class="row ">

			<div class="col-md-4">
			<?php
	

			// Set default timezone
			date_default_timezone_set('UTC');

			try {
				
				$file_db = new PDO('sqlite:../databases/messengerDatabase.sqlite');
				// Set errormode to exceptions
				$file_db->setAttribute(PDO::ATTR_ERRMODE, 
										PDO::ERRMODE_EXCEPTION);
			 
				
				//echo "Connected successfully";
				// Set session variables
				$myID = $_SESSION["id"];
				
				$sql2 = "SELECT username FROM users WHERE id = " .$myID;
				//echo $sql2;
				$result2 = $file_db->query($sql2);
				$resultArray2 = $result2->fetchAll();
				$nbResults2 =  count($resultArray2);
				$receiverId = $resultArray2[0]['username'];
				echo "<b>Username</b>: ";
				echo $receiverId;
				echo "<br/>";
				if (!empty($psName)) {
					echo "<b>PointScale Name</b>: ";
					echo $psName;
					echo "<br/>";
					echo "<b>Current number of points</b>: ";
					echo $psValue;
					echo "<br/>";
				}else{
					echo "<b>No Point Scales yet</b>";
					
					echo "<br/>";
				}
				if (!empty($badgeName)) {
					echo "<b>Badge Name</b>: ";
					echo $badgeName;
					echo "<br/>";
					echo "<b>Badge Description</b>: ";
					echo $badgeDescription;
					echo "<br/>";
					echo "<b>Badge Images</b>: ";
					echo "<img src=\"".$badgeValue."\" alt=\"badge\" height=\"60\" width=\"60\">";
					echo "<br/>";
				}else{
					echo "<b>No badges awarded yet!</b>";
					
					echo "<br/>";
				}
				
				echo "<br/>";
				echo "<br/>";
				echo "<br/>";
				echo "<br/>";
				
			}
			catch(PDOException $e) {
				// Print PDOException message
				echo $e->getMessage();
			}
			
		?>
			
		
		</div>
		
	</div>
	
	
	
    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-4">
		  <img src="images/greenbubble.png" class="img-responsive" alt="bubble">
        </div>
        <div class="col-md-4">
		  <img src="images/greenbubble.png" class="img-responsive" alt="bubble">
        </div>
		<div class="col-md-4">
		  <img src="images/greenbubble.png" class="img-responsive" alt="bubble">
        </div>
        
      </div>

      <hr>

      <footer>
        <p>&copy; 2016 HEIG-VD, CIANI Antony, HERNANDEZ Thomas</p>
      </footer>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
