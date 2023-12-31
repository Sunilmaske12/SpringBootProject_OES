<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.my_profile {
	color: red;
	position: center;
}

.table table-user-information {
	-webkit-text-size-adjust: 100%;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-size: 14px;
	line-height: 1.42857143;
	color: #333;
	box-sizing: border-box;
	border-spacing: 0;
	border-collapse: collapse;
	background-color: transparent;
	width: 100%;
	max-width: 100%;
	margin-bottom: 0;
}

.form-control {
	display: block;
	width: 100%;
	padding: 0.375rem 0.75rem;
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #212529;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	border-radius: 0.25rem;
	-webkit-transition: border-color 0.15s ease-in-out, -webkit-box-shadow
		0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s
		ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out,
		-webkit-box-shadow 0.15s ease-in-out;
}

.form-control {
	padding: 12px;
	border-radius: 22px;
	font-size: 14px;
}

select {
	word-wrap: normal;
}

button, select {
	text-transform: none;
}

input, button, select, optgroup, textarea {
	margin: 0;
	font-family: inherit;
	font-size: inherit;
	line-height: inherit;
}
</style>
</head>
<body>



<!--===================1.  My Profile modal Start for USER ===================== -->
<!--===================2. My Profile modal Start for ADMIN ===================== -->
<!--===================3 Add address modal Start for user ===================== -->


	<!--===================1. My Profile modal Start for user ===================== -->

	<div th:fragment="myProfile" class="modal fade" id="UserProfile" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div  class="modal-content">
				<div class="modal-header">
					<h3 class="my_profile" id="exampleModalLabel">My Profile</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				
					<div th:if="${userInfo == null }">
						<div class="modal-body">
							<h4>You are not login, login first.</h4>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<a th:href="@{/loginForm}"><button type="button"
									class="btn btn-primary">Login</button></a> <a
								th:href="@{/registrationForm}"><button type="button"
									class="btn btn-primary">Register</button></a>
						</div>
				</div>
				
				<div  th:unless="${userInfo == null }" class="modal-body">
					<div>
						<section style="background-color: #eee;">

							<div class="card mb-4">
			
						<form th:object="${userInfo}" th:action="@{/User/editUser}"   method="POST">
								
								<input type="hidden" name="id" th:value="${userInfo.id}" />
								<input type="hidden" name="user_pass" th:value="${userInfo.user_pass}" />
								<input type="hidden" name="user_emailid" th:value="${userInfo.user_emailid}" />
								<input type="hidden" name="user_type" th:value="${userInfo.user_type}" />
						
								<div>
									<div class="card-body text-center" style="text-align: center;">
										<img src="img/hero/icon.png" alt="avatar"
											class="rounded-circle img-fluid" style="width: 150px;">
									</div>
						<table class="table table-user-information">
											<tbody>
												<tr>
													<td><strong> <span	class="glyphicon glyphicon-asterisk text-primary"></span>
															Full Name
													</strong></td>

													<td class="text-primary">: <input type="text"
														name="user_name" th:value="${userInfo.user_name}" /></td>
												</tr>
												<tr>
													<td><strong> <span
															class="glyphicon glyphicon-asterisk text-primary"></span>
															Contact Details
													</strong></td>
													<td class="text-primary">: <input type="text"
														name="user_mobno" th:value="${userInfo.user_mobno }" /></td>
												</tr>
												<tr>
													<td><strong> <span
															class="glyphicon glyphicon-asterisk text-primary"></span>
															Email Id
													</strong></td>
													<td  th:text="': '+${userInfo.user_emailid }"></td>
												</tr>
												<tr>
													<td><strong> <span
															class="glyphicon glyphicon-asterisk text-primary"></span>
															Addresss
													</strong></td>
													<td class="text-primary">: <input type="text"
														name="user_adderess" th:value="${userInfo.user_adderess }" /></td>
												</tr>
											</tbody>
										</table>



								</div>
								<div class="modal-footer">
								<button  id='closeModal' type="button" class="btn btn-danger"
										data-bs-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary">Update</button>
									
								</div>

								</form>
							</div>
						</section>
					</div>
				</div>
			</div>
		</div>

	</div>

<!--=================== My Profile modal end for user ===================== -->




<!--===================2. My Profile modal Start for Admin ===================== -->

	<div th:fragment="AdminProfile" class="modal fade" id="AdminProfile" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="my_profile" id="exampleModalLabel">Update Profile
						Info</h3>
					<button type="button" class="btn-close" data-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div>
						<section style="background-color: #eee;">

							<div class="card mb-4">
								<form th:action="@{/Admin/updateAdmin}" th:object="${userInfo}" method="post">
									<div>
										<div class="card-body text-center" style="text-align: center;">
											<img src="img/hero/icon.png" alt="avatar"
												class="rounded-circle img-fluid" style="width: 150px;">
										</div>
										
											<input type="hidden" name="id" th:value="${userId}" />
							
										

										<table class="table table-user-information">
											<tbody>
												<tr>
													<td><strong> <span
															class="glyphicon glyphicon-asterisk text-primary"></span>
															Full Name
													</strong></td>

													<td class="text-primary">: <input type="text"
														name="user_name" th:value="${userInfo.user_name }" /></td>
												</tr>
												<tr>
													<td><strong> <span
															class="glyphicon glyphicon-asterisk text-primary"></span>
															Contact Details
													</strong></td>
													<td class="text-primary">: <input type="text"
														name="user_mobno" th:value="${userInfo.user_mobno}" /></td>
												</tr>
												<tr>
													<td><strong> <span
															class="glyphicon glyphicon-asterisk text-primary"></span>
															Email Id
													</strong></td>
													<td class="text-primary" th:text="' : '+${userInfo.user_emailid}">
												</tr>
												<tr>
													<td><strong> <span
															class="glyphicon glyphicon-asterisk text-primary"></span>
															Addresss
													</strong></td>
													<td class="text-primary">: <input type="text"
														name="user_adderess" th:value="${userInfo.user_adderess }" /></td>
												</tr>
											</tbody>
										</table>
													</div>
									<div class="modal-footer">
										<button  id='closeModal' type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
										<button type="submit" class="btn btn-primary" >Update </button>
										
									</div>
								</form>
							</div>
						</section>
					</div>
				</div>
			</div>
		</div>

	</div>

<!--=================== My Profile modal END for ADMIN ===================== -->




<!--=================== 3. Add address modal Start for user ===================== -->


	<!-- Modal -->
	<div th:fragment="addAddress" class="modal fade" id="AddNewAddress" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div style="padding: 20px" class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="exampleModalLabel">Add New Address</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form class="addressForm" id="validAddress" onsubmit="return validAddress()"
						th:action="saveAddress" th:object="${customer}" method="post">
						<input type="hidden" name="_token"
							value="7izNrBqYjX16Icbzz1Vn8FgvFZk2Gjn6Ty3VcVjT"> <input
							type="hidden" value="website-checkout-shipping-address"
							name="page">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Full Name</label> <input type="text" name="c_name"
										class="form-control" value="" required>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Contact</label> <input type="text" name="c_mobno" id="mobileno"
										class="form-control"
										onkeypress="INGENIOUS.numericInput(event)"  required>
										<small id="contact_error" style="color:red; margin-left:10px;" ></small>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Email</label> <input type="email" name="c_email"
										class="form-control" value="" required>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<textarea name="c_adderess" placeholder="Enter Your Address"
										class="form-control" cols="30" rows="4" required></textarea>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Landmark</label> <input type="text" name="c_landmark"
										class="form-control" value="" required>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Address Type</label> <select name="c_AddressType"
										class="form-control" required>
										<option value="Home">Home</option>
										<option value="Office">Office</option>
										<option value="Other">Other</option>
									</select>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>City</label> <input type="text" name="c_city"
										class="form-control" value="" required>
								</div>
							</div>

							<div class="col-md-4">
								<div class="form-group">
									<label>Pincode</label> <input type="text" id="pincode" name="c_pincode"
										class="form-control"
										onkeypress="INGENIOUS.numericInput(event)" value="" required>
										<small id="pin_error" style="color:red; text-align:center;" ></small>
								</div>
							</div>
							<input type="hidden" name="u_id" th:value="${userId}">

						</div>
						<div class="modal-footer">
							<div class="col-md-12 text-center">
								<button type="submit" class="btn btn-danger">Save</button>
								<button type="button" class="btn btn-primary"
									data-bs-dismiss="modal">Close</button>
							</div>

						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<!-- Set address during making orders modal end -->



	<!-- Edit address during making orders modal start -->


	<!-- Modal -->
	<div class="modal fade" id="EditAddress" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div style="padding: 20px" class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="exampleModalLabel">Edit Address</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form class="addressForm"
						action="SaveAddress?user_id=<%=user_id%>" method="post">
						<input type="hidden" name="_token"
							value="7izNrBqYjX16Icbzz1Vn8FgvFZk2Gjn6Ty3VcVjT"> <input
							type="hidden" value="website-checkout-shipping-address"
							name="page">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Full Name</label> <input type="text" name="name"
										class="form-control" value="" required>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Contact</label> <input type="text" name="mobile"
										class="form-control"
										 value="" required>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Email</label> <input type="email" name="email"
										class="form-control" value="" required>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<textarea name="address" placeholder="Enter Your Address"
										class="form-control" cols="30" rows="4" required></textarea>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Landmark</label> <input type="text" name="landmark"
										class="form-control" value="">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Address Type</label> <select name="addresstype"
										class="form-control" required>
										<option value="Home">Home</option>
										<option value="Office">Office</option>
										<option value="Other">Other</option>
									</select>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>City</label> <input type="text" name="city"
										class="form-control" value="" required>
								</div>
							</div>

							<div class="col-md-4">
								<div class="form-group">
									<label>Pincode</label> <input type="text" name="pincode"
										class="form-control"
										 value="" required>
								</div>
							</div>

						</div>
						<div class="modal-footer">
							<div class="col-md-12 text-center">
								<button type="submit" class="btn btn-danger">Save</button>
								<button type="button" class="btn btn-primary"
									data-bs-dismiss="modal">Close</button>
							</div>

						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<!-- Edit address during making orders modal end -->

</body>
</html>