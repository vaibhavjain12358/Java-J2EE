
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>E-Asset Management</title>
</head>
<body>
<h1>Add Asset</h1></br>
	<form action="AssetController" method="post">
		<table>
			<tr>
				<td>Category:</td>
				<td><input type="text" value="comp" name="category" /></td>
			</tr>
			<tr>
				<td>Stock:</td>
				<td><input type="text" value="5" name="stock" /></td>
			</tr>
			<tr>
				<td>Availability:</td>
				<td><input type="text" value="5" name="availability" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Asset" name='btn'/></td>
			</tr>
		</table>
	</form>
</body>
</html>
</body>
</html>