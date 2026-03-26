$(document).ready(function () {
	debugger;
			$("#uploadForm").submit(function (event) {
				event.preventDefault();

				var userid = $('#userid').val();
				var password = $('#password').val();
alert("userid"+userid);
alert("userid"+password);

				$.ajax({
					url: "reset_password",
					type: "POST",
					data: {
						userid: userid,
						password: password
					},
					success: function (response) {
						$("#modalMessage").text(response);
						$("#responseModal").modal("show");
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
				
				