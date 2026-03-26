<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>


<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="./js/common.js"></script>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="stylesheet" href="./css/styles.css">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script src="./js/dashboard.js"></script>

</head>
<body>

	<!-- Placeholder for the sidebar -->
	<div id="header-container"></div>

	<div class="d-flex">
		<!-- Placeholder for Sidebar -->
		<div id="sidebar-container"></div>

		<!-- Main Content -->
		<main class="content flex-grow-1 p-4" style="margin-left: 350px;">
			<div class="container-fluid">
				<h1 class="mb-4">Welcome to the Dashboard</h1>

				<!-- Statistics Cards -->
				<div class="row">
					<div class="col-lg-4 col-12 mb-4">
						<div class="card h-100">
							<div class="card-body">
								<div class="d-flex justify-content-between align-items-center">
									<h4 class="mb-0">Earnings</h4>
									<div
										class="icon-shape bg-light-danger text-danger rounded-circle p-3">
										<i class="fas fa-dollar-sign"></i>
									</div>
								</div>
								<h1 class="mb-2 fw-bold">$93,438.78</h1>
								<span>Monthly revenue</span>
							</div>
						</div>
					</div>

					<div class="col-lg-4 col-12 mb-4">
						<div class="card h-100">
							<div class="card-body">
								<div class="d-flex justify-content-between align-items-center">
									<h4 class="mb-0">Orders</h4>
									<div
										class="icon-shape bg-light-warning text-warning rounded-circle p-3">
										<i class="fas fa-shopping-cart"></i>
									</div>
								</div>
								<h1 class="mb-2 fw-bold">42,339</h1>
								<span>35+ New Sales</span>
							</div>
						</div>
					</div>

					<div class="col-lg-4 col-12 mb-4">
						<div class="card h-100">
							<div class="card-body">
								<div class="d-flex justify-content-between align-items-center">
									<h4 class="mb-0">Customers</h4>
									<div
										class="icon-shape bg-light-info text-info rounded-circle p-3">
										<i class="fas fa-users"></i>
									</div>
								</div>
								<h1 class="mb-2 fw-bold">39,354</h1>
								<span>30+ new in 2 days</span>
							</div>
						</div>
					</div>
				</div>

				<!-- Revenue Chart -->
				<div class="row">
					<div class="col-xl-8 col-lg-6 col-12 mb-4">
						<div class="card h-100">
							<div class="card-body">
								<div class="d-flex justify-content-between">
									<h3 class="mb-1">Revenue</h3>
									<small>(+63% than last year)</small> <select
										class="form-select w-auto">
										<option selected>2019</option>
										<option>2020</option>
										<option>2021</option>
										<option>2022</option>
										<option>2023</option>
									</select>
								</div>
								<div id="revenueChart" class="mt-4 text-center">
									<p class="text-muted">Upcoming Data</p>
								</div>
							</div>
						</div>
					</div>

					<!-- Total Sales -->
					<div class="col-xl-4 col-lg-6 col-12 mb-4">
						<div class="card h-100">
							<div class="card-body text-center">
								<h3 class="mb-3">Total Sales</h3>
								<div id="totalSale" class="mt-3">
									<p class="text-muted">Upcoming data</p>
								</div>
								<ul class="list-unstyled text-start mt-4">
									<li><i class="fas fa-circle text-primary"></i> Shipping:
										$32.98 (2%)</li>
									<li><i class="fas fa-circle text-warning"></i> Refunds:
										$11 (11%)</li>
									<li><i class="fas fa-circle text-danger"></i> Orders:
										$14.87 (1%)</li>
									<li><i class="fas fa-circle text-info"></i> Income: 3,271
										(86%)</li>
								</ul>
							</div>
						</div>
					</div>
				</div>

				<!-- Sales Overview and Notifications -->
				<div class="row">
					<div class="col-xl-6 col-lg-6 col-md-12 col-12 mb-6">
						<!-- card -->
						<div class="card h-100 card-lg">
							<!-- card body -->
							<div class="card-body p-6">
								<h3 class="mb-0 fs-5">Sales Overview</h3>
								<div class="mt-6">
									<!-- text -->
									<div class="mb-5">
										<div class="d-flex align-items-center justify-content-between">
											<h5 class="fs-6">Total Profit</h5>
											<span> <span class="me-1 text-dark">$1,619</span>
												(8.6%)
											</span>
										</div>
										<!-- main wrapper -->
										<div>
											<!-- progressbar -->
											<div class="progress bg-light-primary" style="height: 6px">
												<div class="progress-bar bg-primary" role="progressbar"
													aria-label="Example 1px high" style="width: 25%"
													aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</div>
									</div>
									<div class="mb-5">
										<!-- text -->
										<div class="d-flex align-items-center justify-content-between">
											<h5 class="fs-6">Total Income</h5>
											<span> <span class="me-1 text-dark">$3,571</span>
												(86.4%)
											</span>
										</div>
										<div>
											<!-- progressbar -->
											<div class="progress bg-info-soft" style="height: 6px">
												<div class="progress-bar bg-info" role="progressbar"
													aria-label="Example 1px high" style="width: 88%"
													aria-valuenow="88" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</div>
									</div>
									<div>
										<!-- text -->
										<div class="d-flex align-items-center justify-content-between">
											<h5 class="fs-6">Total Expenses</h5>
											<span> <span class="me-1 text-dark">$3,430</span>
												(74.5%)
											</span>
										</div>
										<div>
											<!-- progressbar -->
											<div class="progress bg-light-danger" style="height: 6px">
												<div class="progress-bar bg-danger" role="progressbar"
													aria-label="Example 1px high" style="width: 45%"
													aria-valuenow="45" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-12 col-12 mb-6">
						<div class="position-relative h-100">
							<!-- card -->
							<div class="card card-lg mb-6">
								<!-- card body -->
								<div class="card-body px-6 py-8">
									<div class="d-flex align-items-center">
										<div>
											<!-- svg -->
											<svg xmlns="http://www.w3.org/2000/svg" width="32"
												height="32" fill="currentColor"
												class="bi bi-bell text-warning" viewBox="0 0 16 16">
                                                <path
													d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z" />
                                            </svg>
										</div>
										<!-- text -->
										<div class="ms-4">
											<h5 class="mb-1">Start your day with New Notification.</h5>
											<p class="mb-0">
												You have <a class="link-info" href="#!">2 new
													notification</a>
											</p>
										</div>
									</div>
								</div>
							</div>
							<!-- card -->
							<div class="card card-lg mt-4">
								<!-- card body -->
								<div class="card-body px-6 py-8">
									<div class="d-flex align-items-center">
										<!-- svg -->
										<div>
											<svg xmlns="http://www.w3.org/2000/svg" width="32"
												height="32" fill="currentColor"
												class="bi bi-lightbulb text-success" viewBox="0 0 16 16">
                                                <path
													d="M2 6a6 6 0 1 1 10.174 4.31c-.203.196-.359.4-.453.619l-.762 1.769A.5.5 0 0 1 10.5 13a.5.5 0 0 1 0 1 .5.5 0 0 1 0 1l-.224.447a1 1 0 0 1-.894.553H6.618a1 1 0 0 1-.894-.553L5.5 15a.5.5 0 0 1 0-1 .5.5 0 0 1 0-1 .5.5 0 0 1-.46-.302l-.761-1.77a1.964 1.964 0 0 0-.453-.618A5.984 5.984 0 0 1 2 6zm6-5a5 5 0 0 0-3.479 8.592c.263.254.514.564.676.941L5.83 12h4.342l.632-1.467c.162-.377.413-.687.676-.941A5 5 0 0 0 8 1z" />
                                            </svg>
										</div>
										<!-- text -->
										<div class="ms-4">
											<h5 class="mb-1">Monitor your Sales and Profitability</h5>
											<p class="mb-0">
												<a class="link-info" href="#!">View Performance</a>
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Recent Orders -->
				<div class="row mt-4">
					<div class="col-xl-12 col-lg-12 col-md-12 col-12 mb-6">
						<div class="card h-100 card-lg">
							<!-- heading -->
							<div class="p-6">
								<h3 class="mb-0 fs-5">Recent Order</h3>
							</div>
							<div class="card-body p-0">
								<!-- table -->
								<div class="table-responsive">
									<table
										class="table table-centered table-borderless text-nowrap table-hover">
										<thead class="bg-light">
											<tr>
												<th scope="col">Order Number</th>
												<th scope="col">Product Name</th>
												<th scope="col">Order Date</th>
												<th scope="col">Price</th>
												<th scope="col">Status</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="Common_data_validation" items="${uploadDtl}">
												<tr>
													<td style="width: 190px; text-align: center;"><span
														class="badge bg-green">${uploadDtl.filename}</span></td>
													<td style="width: 190px; text-align: center;"><span
														class="badge bg-green">${uploadDtl.filename}</span></td>
													<td style="width: 210px; text-align: center;"><span
														class="badge bg-green">${uploazzdDtl.filename}</span></td>
													<td style="width: 210px; text-align: center;"><span
														class="badge bg-green">${uploadDtl.filename}</span></td>

													<td style="width: 190px; text-align: center;">${uploadDtl.filename}${UploadBean.filename
														}</td>



												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>

	<div id="footer-container"></div>

	<div align="center" id="Loader"
		style="background-color: #ffffff; position: absolute; opacity: 0.9; z-index: 0; height: 100%; width: 100%; left: 0px; top: 0px; display: none">

		<img style="margin-left: 100px; margin-top: 130px;"
			src="./images/loader.gif" alt="loader">
	</div>


</body>
</html>