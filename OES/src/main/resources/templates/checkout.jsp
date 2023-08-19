<!DOCTYPE html>
<html lang="zxx" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Checkout</title>

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
<body
<!--	<%if (incompleteinfo != null) {
	if (incompleteinfo.equals("No address")) {%>
	onload="addressrErrorPopUp()"
	<%session4.removeAttribute("incompleteinfo");
	} else {%>
	onload="msgErrorPopUp()"
	<%session4.removeAttribute("incompleteinfo");
	}
}%> --> >

	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	<header th:replace="~{/UserHeaderFooter/header::userHeader}"></header>
	
	<div th:replace="~{/CommonModal::addAddress}"></div>
	
<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg" data-setbg="img/Name-bg.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>Checkout</h2>
						<div class="breadcrumb__option">
							<a th:href="@{/}">Home</a> <span>Checkout</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Checkout Section Begin -->
	<section class="checkout spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h6>
						<span class="icon_tag_alt"></span> Have a coupon? <a href="#">Click
							here</a> to enter your code
					</h6>
				</div>
			</div>
			<div class="checkout__form">
				<h4>Billing Details</h4>
				<form method="post" th:action="@{'/User/makeOrder'+','+${totalAmount }+','+${cartLength}}" id="orderPlaceAlert">
					<div class="row">
						<div class=" col-md-6">

			<!-- Address Show Started -->

							<div class="card mb-3" >
								<div class="card-body">
									<h4 class="text-dark">Select Your Address</h4>
									<div class="row checkout-address-row" th:each="address:${AllAddress }">
										<div class="col-2 col-md-1">
											<div class="radio radio-success">
												<input type="radio" name="address_id"
													th:value="${address.c_id }" id="addressId"
													aria-invalid="false" />
											</div>
											<span class="badge badge-primary"></span>
										</div>
										<div class="col-md-11 col-10">

											<div>
												Mr
												<span th:text="${address.c_name }"></span>
												(<span th:text="${address.c_mobno }" ></span>)
											</div>
											<hr class="mt-2 mb-2">
											<span th:text="${address.c_adderess }"></span>
											, <br>
											<span th:text="${address.c_landmark }+' '+${address.c_city }+' '+${address.c_pincode }"></span>
										<a th:href="@{'/User/editAddressPage'+${address.c_id }}"
												class="badge bg-danger pull-right">Edit</a>
												<hr size="12" width="100%" color="red">
									</div>
									</div>
									<hr size="12" width="100%" color="red">
									<h2 th:if="${AllAddress == null }" style="color: red; top:10px">PLEASE PUT YOUR ADDRESS!</h2>
							
									<div class="row">
										<div class="col-md-12">
											<div class="text-center mt-5">
												<button type="button" class="btn btn-primary"
													data-bs-toggle="modal" data-bs-target="#AddNewAddress">
													<i class="fa fa-plus-circle"></i> Add New Address
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>


						<!-- Address Show Ended -->

						<!-- -Order Form Started -->
						<div style="margin-left: 100px;" class="col-lg-4 col-md-6">
							<div class="checkout__order">
								<h4>Your Order</h4>
								<div class="check">
									<!-- data comming from js file -->

								</div>


								<div class="checkout__order__subtotal  ">
									Subtotal <span class="totalOrder"></span>
								</div>
								<div class="checkout__order__total ">
									Delivery Charges <span style="color: black;" class="charges"></span>
								</div>
								<div class="checkout__order__total">
									Total <span class="totalPrice"></span>
								</div>
								<button type="submit" onclick="setOrderInCart()" class="site-btn"
									style="border-radius: 10px;">MAKE ORDER</button>

							</div>

						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
	<!-- Checkout Section End -->



	<jsp:include page="footer.html" />

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