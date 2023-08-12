<!DOCTYPE html>
<html lang="zxx"   xmlns:th="http://www.thymeleaf.org">

<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Payment- PlaceOrder</title>

<!-- Google Font -->
<link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"	rel="stylesheet">

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
<input type="hidden" value="<%= orderId%>" id="orderId"> 
<body<!--  <%if(incompleteinfo!=null){
	if(incompleteinfo.equals("No address")){
		%>
		  onload="addressrErrorPopUp()"
		<%session4.removeAttribute("incompleteinfo"); }
	else{
	%>
  onload="msgErrorPopUp()"
<%session4.removeAttribute("incompleteinfo"); }}
 %> -->

>

	<!-- Page Preloder -->
		<!-- Checkout Section Begin -->
	<section class="checkout spad">
		<div class="container">
		
			<div class="checkout__form">
				<h4 style="text-align:center;">Billing Details</h4>
				<form method="post" th:action="@{/User/orderPlace}" id="orderPlaceAlert">
					<div class="row">
						
						<!-- -Order Form Started -->
						<div style="margin-left: 36%;" class="col-lg-4 col-md-6">
							<div class="checkout__order">
								<h4>Your Order</h4>
								

								<div class="checkout__order__subtotal  ">
									Subtotal <span class="totalOrder"></span>
								</div>
								<div class="checkout__order__total ">
									Delivery Charges <span style="color: black;" class="charges"></span>
								</div>
								<div class="checkout__order__total">
									Total <span class="totalPrice"></span>
								</div>

						<div id="orderPayment">
							
								<div class="checkout__order__total">
									<div class="radio radio-success">
										<input onclick="choosePayment('cash')" type="button" name="payment" id="paymentMode" value="Cash"
											aria-invalid="false" />

										<span style="color: black;">CASH ON DELIVERY</span>
									</div>
								</div>
								<div class="checkout__order__total">
									<div class="radio radio-success">
															
										<a ><input   onclick="onlinePayment('online')" type="button" name="payment" id="paymentMode" value="online"
											aria-invalid="false" /></a>

										<span style="color: black;">ONLINE PAYMENT</span>
									</div>
								</div>
								
								<a class="paymentOrder"></a>
					</div>	
							
								
								<input type="hidden" name="paymentId" id="paymentId">
								<input type="hidden" name="razorpayOrdertId" id="ROrderId">
								<input type="hidden" name="user_id" th:value="${userId }">
								<input type="hidden" id="totalPrice" name="tprice">
								<input type="hidden" id="totalPricePay"> 
								
								
							</div>
						<input type="hidden" id="orderId"  name="productPriceO">
						<input type="hidden" th:value="${address_id }" name="addressId">
						
					</div>
					</div>
				</form>
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
	<script type="text/javascript" src="https://checkout.razorpay.com/v1/checkout.js"></script>
	<script type="text/javascript" src="js/Payment.js"></script>
	<script type="text/javascript" src="js/CommonScript.js"></script>
	<script type="text/javascript" src="js/Payment.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</body>

</html>