<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- BOOTSTRAP + VALIDATIONS -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<link rel="stylesheet" href="css/bootstrap/bootstrap-3.3.7.css">
<script src="css/bootstrap/jQuery-3.2.1.js"></script>
<script src="css/bootstrap/bootstrap-3.3.7.js"></script>


</head>
<body>
	<div align="center">
		<div class="well" style="width:70%">
			<h1>LOG IN</h1>
			<form name="login" action="AssetLoginServlet" method="get">
				<table border="0" cellspacing="2" cellpadding="2">

					<tbody>

						<tr>

							<td><div class="input-group">
									<span class="input-group-addon" id="basic-addon1">USERNAME: &nbsp&nbsp&nbsp&nbsp&nbsp</span>
								</div></td>
							<td><input type="text" name="userName" class="form-control" size="35"
								required></td>
						</tr>

						<tr>
							<td><div class="input-group">
									<span class="input-group-addon" id="basic-addon1">PASSWORD: &nbsp&nbsp&nbsp&nbsp&nbsp</span>
								</div></td>
							<td><input type="password" name="userPass"
								class="form-control" size="35" required></td>
						</tr>
						<tr>
							<td><div class="input-group">
									<span class="input-group-addon" id="basic-addon1">TYPE:&nbsp&nbsp&nbsp&nbsp&nbsp</span>
								</div></td>
							<td><div class="row">
									<div class="col-lg-6">
										<div class="input-group">
											<span class="input-group-addon"> <input
												type="radio" name="userType" value="ADMIN" ></span>
											ADMIN
										</div>
									</div></div>
									</td>
									<td>
									<div class="col-lg-6">
										<div class="input-group">
											<span class="input-group-addon"> <input type="radio"
												name="userType" value="USER"></span> USER<br>
										</div>
									</div>
								</td>
						</tr>
				</table>
				<br> <input type="submit" value="Login" name="btn"
					class="btn btn-success" />


			</form>
		</div>
	</div>
	
</body>
</html>