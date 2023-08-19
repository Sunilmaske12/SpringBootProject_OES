<!DOCTYPE html>
<html lang="zxx" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Edit Address</title>

<!-- Google Font -->
<link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"	rel="stylesheet">



<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

<script	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">


</head>
<body>

	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	<header th:replace="~{/UserHeaderFooter/header::userHeader}"></header>
	
	<section >
		<div style="padding-bottom: 30px;" class="container">
			<div  style="background: #f5f5f5; " >
			<div style="padding: 20px" >
				<div class="modal-header">
					<h4 >Make Changes</h4>
					
				</div>
				<div class="modal-body">
					<form class="addressForm" id="validAddress" onsubmit="return validAddress()"
						th:action="@{/User/saveAddress}" th:object="${customer}" method="post"
						style="font-weight: bold; color: red;">
						
						<input type="hidden" name="u_id" th:value="${userId}">
						<input type="hidden" name="c_id" th:value="${customer.c_id}">
						
						<input type="hidden" name="_token"
							value="7izNrBqYjX16Icbzz1Vn8FgvFZk2Gjn6Ty3VcVjT"> <input
							type="hidden" value="website-checkout-shipping-address"
							name="page">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Full Name</label> <input type="text" name="c_name"
										class="form-control" th:value="${customer.c_name }" required>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Contact</label> <input type="text" name="c_mobno" id="mobileno"
										class="form-control" th:value="${customer.c_mobno }"
										onkeypress="INGENIOUS.numericInput(event)"  required>
										<small id="contact_error" style="color:red; margin-left:10px;" ></small>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Email</label> <input type="email" name="c_email"
										class="form-control" th:value="${customer.c_email }" required>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<textarea name="c_adderess" placeholder="Enter Your Address"
										class="form-control" cols="30" rows="4" th:value="${customer.c_adderess}"  th:text="${customer.c_adderess}" required></textarea>
								</div>
							</div>
							<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>Landmark</label> <input type="text" name="c_landmark"
												class="form-control" th:value="${customer.c_landmark }"  required>
										</div>
									</div>
									<div class="offset-2 col-md-2">
										<div class="form-group">
											<label>Address Type</label> 
											<select name="c_AddressType" 
												 required>
												<option th:value="${customer.c_AddressType }" th:text="${customer.c_AddressType }"></option>
												<option value="Home">HOME</option>
												<option value="Office">OFFICE</option>
												<option value="Other">OTHER</option>
											</select>
										</div>
									</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>City</label> <input type="text" name="c_city"
										class="form-control" th:value="${customer.c_city }" required>
								</div>
							</div>

							<div class="col-md-4">
								<div class="form-group">
									<label>Pincode</label> <input type="text" id="pincode" name="c_pincode"
										class="form-control"
										onkeypress="INGENIOUS.numericInput(event)" th:value="${customer.c_pincode }" required>
										<small id="pin_error" style="color:red; text-align:center;" ></small>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="col-md-12 text-center">
								<button type="submit" class="btn btn-danger">Change</button>
								<button type="button" class="btn btn-primary"
									data-bs-dismiss="modal">Close</button>
							</div>

						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	</section>
	<!-- Checkout Section End -->
	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
	<script type="text/javascript"
		src="https://checkout.razorpay.com/v1/checkout.js"></script>
	<script type="text/javascript" src="js/Payment.js"></script>
	<script type="text/javascript" src="js/CommonScript.js"></script>
	<script type="text/javascript" src="js/Payment.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</body>

</html>