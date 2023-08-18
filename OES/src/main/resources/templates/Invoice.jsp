<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Invoice</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>

<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">
</head>

<body id="invoiceDownload">

<script type="text/javascript">
//========================Download Invoice===============
function downloadInvoice() {
	  console.log("Invoive download working")
          html2canvas($('#invoiceDownload')[0], {
              onrendered: function (canvas) {
                  var data = canvas.toDataURL();
                  var docDefinition = {
                      content: [{
                          image: data,
                          width: 500
                      }]
                  };
                  pdfMake.createPdf(docDefinition).download("invoice.pdf");
              }
          });
      }
      

</script>

	<div  style="margin-left: 15%; margin-right:15%; top:20px;" class="card">
		<div  class="card-body">
			<div class="container mb-5 mt-3">
				<div class="row d-flex align-items-baseline">
					<div class="col-xl-9">
						<p style="color: #7e8d9f; font-size: 20px;">
							Invoice >> <strong>ID: #123-123</strong>
						</p>
					</div>
					<div class="col-xl-2">
				<button onclick="downloadInvoice()">Download</button></div>
					<hr>
				</div>

				<div class="container">
					<div class="col-md-12">
						<div class="text-center">
							<i class="fab fa-mdb fa-4x ms-0" style="color: #5d9fc5;"></i>
							<p class="pt-0">OnlineElectricalShopee.com</p>
						</div>

					</div>


					<div class="row">
						<div  class="col-xl-8">
				
							<ul th:object="${customer}" class="list-unstyled">
								<li class="text-muted">To: <span style="color: #5d9fc5;" th:text="${customer.c_name }"> </span></li>
								<li class="text-muted" th:text="${customer.c_landmark }"></li>
								<li class="text-muted" th:text="${customer.c_city }+', Maharashtra'"></li>
								<li class="text-muted" th:text="${customer.c_mobno }"><i class="fas fa-phone"></i></li>
								<li class="text-muted" th:text="${customer.c_email }"><i class="fas fa-phone"></i></li>
							</ul>
						</div>
						
						<div class="col-xl-4">
							<p class="text-muted">Invoice</p>
							<ul class="list-unstyled">
								<li class="text-muted"><i class="fas fa-circle"
									style="color: #84B0CA;"></i> <span class="fw-bold">ID:</span>#123-123</li>
								<li class="text-muted"><i class="fas fa-circle"
									style="color: #84B0CA;"></i> <span class="fw-bold">Order
										Date: </span></li>
								<li class="text-muted"><i class="fas fa-circle"
									style="color: #84B0CA;"></i> <span class="me-1 fw-bold">Status:</span><span
									class="badge bg-warning text-black fw-bold"> Paid</span></li>
							</ul>
						</div>
					</div>

					<div class="row my-2 mx-1 justify-content-center">
						<table class="table table-striped table-borderless">
							<thead style="background-color: #84B0CA;" class="text-white">
								<tr>
									<th scope="col">#</th>
									<th scope="col">Description</th>
									<th scope="col">Unit Price</th>
									<th scope="col">Quantity</th>
									<th scope="col">Amount</th>
								</tr>
							</thead>
							<tbody>
			
					<tr th:each="orderDetails, status:${orderDetails}">
						<td scope="row"></td>
						<td th:text="${orderDetails.Product_Name }"></td>
						<td th:text="${orderDetails.Product_Price }"></td>
						<td th:text="${orderDetails.Product_Quantity }"></td>
						<td th:text="${tPrice }"></td>
						
					</tr>
				</tbody>

						</table>
					</div>
					<div class="row">
						<div class="col-xl-8">
							

						</div>
						<div class="col-xl-3">
							<ul class="list-unstyled">
								<li class="text-muted ms-3"><span class="text-black me-4">SubTotal</span><span th:text="'Rs. ' +${tPrice }"></span></li>
								<li class="text-muted ms-3 mt-2"><span 
									class="text-black me-4">Charges </span><span th:text="'Rs. ' +${charges }"></span></li>
							</ul>
							<p class="text-black float-start">
								<span class="text-black me-3"> Total Amount</span>
								<span style="font-size: 25px;" th:text="'Rs.'+${tPrice + charges}"></span>
							</p>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-xl-10">
							<p>Thank you for your purchase</p>
							<h2>Signature</h2>
						</div>
						<div class="col-xl-2">
							<button type="button" class="btn btn-primary text-capitalize"
								style="background-color: #60bdf3;">PAID</button>
								
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
		<script type="text/javascript" src="js/CommonScript.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
</body>
</html>