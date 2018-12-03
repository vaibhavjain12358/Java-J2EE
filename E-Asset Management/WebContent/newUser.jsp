<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- BOOTSTRAP + VALIDATIONS -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NewUserRegistration</title>
<link rel="stylesheet" href="css/bootstrap/bootstrap-3.3.7.css">
<script src="css/bootstrap/jQuery-3.2.1.js"></script>
<script src="css/bootstrap/bootstrap-3.3.7.js"></script>
</head>
<body>
<div align="center">
<div class="well" style="width:70%">
<form name="reg" action="AssetLoginServlet" method="get">
<h2> REGISTRATION FORM </h2>
<table>
<tr>
<td>UserName &nbsp&nbsp&nbsp&nbsp&nbsp</td>
<td> <input type = "text" name = "newUserName" placeholder="Enter your Name" size="35" required>
</td>
 </tr>
 <tr>
 <td>Password &nbsp&nbsp&nbsp&nbsp&nbsp</td>
<td> <input type = "password"  name = "newUserPass" placeholder="Enter your Password" size="35" required>
</td>
 </tr>
  <tr>
 <td>TYPE</td>
 <td><input type="radio" name="newUserType" value="ADMIN" checked/> ADMIN<td>
 <td><input type="radio" name="newUserType" value="USER"/>USER</td>
 </tr>
<tr><td></td>
<td>   <input type="submit" value="SIGNUP" name="btn" class="btn btn-primary" /></td><td></td>
</tr>
</table>
</form>
</div>
</div>
</body>
</html>