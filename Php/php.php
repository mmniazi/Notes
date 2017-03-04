<?php
// Defining variables, notice the semicolon
$a = 1;
$b = 2 * $a;

// Printing them to page
echo("<h1>" . $a . "</h1>");

// Defining constants
define("ONE", "first thing");
echo ONE . "</br>";

// Conditional statements
$d = date("D");

if ($d == "Fri")
    echo "Have a nice weekend!";

elseif ($d == "Sun")
    echo "Have a nice coding";
else
    echo "Have a nice day with chinese";

echo "</br>";

switch ($d) {
    case "Mon":
        echo "Today is Monday";
        break;

    case "Tue":
        echo "Today is Tuesday";
        break;

    default:
        echo "Wonder which day is this ?";
}

echo "</br>";

// Loops
for ($i = 0; $i < 5; $i++) {
    $a += 10;
    $b += 5;
}

echo("$a+$b=" . ($a + $b) . "</br>");

echo $i . "</br>";

while ($i < 10) {
    $i++;
}

echo $i . "</br>";

do {
    $i++;
} while ($i < 10);

echo $i . "</br>";

$array = array(1, 2, 3, 4, 5);

foreach ($array as $value) {
    echo "Value is $value <br />";
}
// Maps or associative arrays
$salaries = array("mohammad" => 2000, "qadir" => 1000, "zara" => 500);
echo "Salary of mohammad is " . $salaries['mohammad'] . "<br />";
$salaries['mohammad'] = "high";
echo "Salary of mohammad is " . $salaries['mohammad'] . "<br />";

// Strings, single qouted strings escapes variable names
echo '$a\\';
echo "$a";
?>
</br>

<form action="<?php echo $_PHP_SELF ?>" method="POST">
    Name: <input type="text" name="name"/>
    Age: <input type="text" name="age"/>
    <input type="submit"/>
</form>

<!--html forms-->
<?php
if ($_POST["name"] || $_POST["age"]) {
    if (preg_match("/[^A-Za-z'-]/", $_POST['name'])) {
        die ("invalid name and name should be alpha");
    }

    echo "Welcome " . $_POST['name'] . "<br />";
    echo "You are " . $_POST['age'] . " years old.";
}
?>

<?
// Require and include both are used to copy code from one file to another
// But require create fatal error if file fails to load while include creates warning
//require("xxmenu.php");
//include("xxmenu.php");
?>

<?php
/* Defining a PHP Function */
function writeMessage($a) {
    return "You are really a nice person, Have a nice time!" . $a;
}
// passing by reference
function writeMessageRef(&$a) {
    $a = "b";
}
// passing by reference
function defVal($a = "a") {
    return $a;
}

/* Calling a PHP Function */
$a = "a";
echo writeMessage($a);
writeMessageRef($a);
echo $a;
echo defVal();

// Dynamically calling function

function sayHello() {
    echo "Hello<br />";
}

$function_holder = "sayHello";
$function_holder();

// Cookies
//setcookie(name, value, expire, path, domain, security);

setcookie("name", "John Watkin", time()+3600, "/","", 0);

echo $_COOKIE["name"]. "<br />";

/* is equivalent to */
echo $HTTP_COOKIE_VARS["name"]. "<br />";

if( isset($_COOKIE["name"]))
    echo "Welcome " . $_COOKIE["name"] . "<br />";

else
    echo "Sorry... Not recognized" . "<br />";

// Session, an alternative way is to create session in server
session_start();

if( isset( $_SESSION['counter'] ) ) {
    $_SESSION['counter'] += 1;
}else {
    $_SESSION['counter'] = 1;
}

$msg = "You have visited this page ".  $_SESSION['counter'];
$msg .= "in this session.";
echo $msg;

// Deleting sessions
//session_destroy();
?>
