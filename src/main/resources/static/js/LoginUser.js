$(document).ready(function () {
	debugger;
			$("#uploadForm").submit(function (event) {
				event.preventDefault();

				var userid = $('#userid').val();
				var password = $('#password').val();

				$.ajax({
					url: "/I-RECON/auth/login",
					type: "POST",
					data: {
						userid: userid,
						password: password
					},
					success: function (response) {/*
					    if (response.trim() === "Login Successfull") { // Trim to remove spaces
					       // $("#modalMessage").text(response);  // Show the success message in modal
					      //  $("#responseModal").modal("show"); 

					        setTimeout(function () {
					            window.location.href = "/I-RECON/dashboard";
					        }, 3000);  // Wait 3 seconds before redirecting
					    } else {
					        $("#modalMessage").text(response);
					        $("#responseModal").modal("show");
					    }*/
						
						localStorage.setItem("token", response.token);
						window.location.href = "/I-RECON/dashboard";
					},
					error: function () {
						$("#modalMessage").text("An error occurred. Please try again.");
						$("#responseModal").modal("show");

						setTimeout(function () {
							$("#responseModal").modal("hide");
						}, 3000);
					}
				});
			});
		});