<?php
// Start the session
session_start();

if (isset($_SESSION['id'])){
	header("location:user.php");
	exit;
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
            <li class="active"><a href="index.php">Accueil</a></li>

            
          </ul>
		  <form action="connexion.php" method="post" class="navbar-form navbar-right">
		  
			<?php
				if (isset($_SESSION['connerror'])){
				
			?>
			<div class="form-group">
		
				<div class="alert-sm alert-danger" role="alert">
					<strong>Erreur de connexion!</strong>
				</div>
			</div>
			
			<?php
			
					session_unset();
					session_destroy();
				}
			?>
            <div class="form-group">
              <input type="email" name="email" placeholder="Email" class="form-control" required>
            </div>
            <div class="form-group">
              <input type="password" name="password" placeholder="Mot de passe" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-success">Connexion</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>Veuillez vous logguer pour acc&eacuteder à la messagerie</h1>
        
		<!-- <p><a class="btn btn-primary btn-lg" href="inscription.php" role="button">Inscris-toi! &raquo;</a></p> -->
		 
		 
		
		
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
