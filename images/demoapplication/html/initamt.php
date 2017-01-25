<?php
//--- Registrations ---

$data = array('name'=>'demo2','password'=>'toor');
$data_json = json_encode($data);
$url = "http://gamificationserver:8080/api/registrations";

echo "/registrations";

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json','Accept: */*'));
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS,$data_json);
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
var_dump($header);

//--- AUTH ---

$data = array('appName'=>'demo2','appPassword'=>'toor');
$data_json = json_encode($data);
$url = "http://gamificationserver:8080/api/auth";

echo "/auth";
echo "<br/>";
echo "<br/>";

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

echo $token


curl_close($ch);
//var_dump($responses);
echo "token = $token" ;
var_dump($header);


//--- POST /BADGE ---

$data = array('description'=>'yolo!','imageURI'=>'http://www.needmail.net/mail.jpg','name'=>'mail');
$data_json = json_encode($data);
$url = "http://gamificationserver:8080/api/badges";

echo "/badges";

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json','Accept: application/json','authToken: ' . $token));
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
$body = substr($response, $header_size);

curl_close($ch);
//var_dump($responses);
var_dump($header);
var_dump($body);

//--- POST /pointscales ---

$data = array('description'=>'echelle','name'=>'scale','unit'=>'point');
$data_json = json_encode($data);
$url = "http://gamificationserver:8080/api/pointscales";

echo "/pointscales";

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json','Accept: application/json','authToken: ' . $token));
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
$body = substr($response, $header_size);

curl_close($ch);
//var_dump($responses);
var_dump($header);
var_dump($body);

//--- POST /rules add point on PS ---

$data = array('badgeName'=>'','description'=>'1point','eventType'=>'plus1','name'=>'myRule','pointScaleName'=>'scale','pointsToAdd'=>1,'valueToReach'=>0);
$data_json = json_encode($data);
$url = "http://gamificationserver:8080/api/rules";

echo "/rules add point on PS";

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
//$responses = curl_getinfo($ch, CURLINFO_HTTP_CODE);
//$errors = curl_getinfo($ch, CURLINFO_HEADER_OUT);

$header_size = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
$header = substr($response, 0, $header_size);
$body = substr($response, $header_size);

curl_close($ch);
//var_dump($responses);
var_dump($header);
var_dump($body);

//--- POST /rules give badge when nb point reach ---

$data = array('badgeName'=>'mail','description'=>'giveBadge','eventType'=>'winMail','name'=>'myRule2','pointScaleName'=>'scale','pointsToAdd'=>0,'valueToReach'=>3);
$data_json = json_encode($data);
$url = "http://gamificationserver:8080/api/rules";

echo "/rules give badge when nb point reach";

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
//$responses = curl_getinfo($ch, CURLINFO_HTTP_CODE);
//$errors = curl_getinfo($ch, CURLINFO_HEADER_OUT);

$header_size = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
$header = substr($response, 0, $header_size);
$body = substr($response, $header_size);

curl_close($ch);
//var_dump($responses);
var_dump($header);
var_dump($body);


?>