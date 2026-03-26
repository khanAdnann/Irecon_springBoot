$(document)
	.ready(
		function() {
			debugger;
			$("#ntslUpload")
				.submit(
					function(event) {
						event.preventDefault();
						showLoader();

						var files = $("#fileUpload")[0].files;
						var dateInput = $("#dateInput")
							.val();
					


						if (files.length === 0
							|| !dateInput
						) {
							alert("Please fill all required fields.");
							return;
						}

						var formData = new FormData();
						for (var i = 0; i < files.length; i++) {
							formData.append("files",
								files[i]);
						}
						formData.append("fileDate",
							dateInput);
					

						$
							.ajax({
								url: "/I-RECON/icdNtslFilesUpload",
								type: "POST",
								data: formData,
								processData: false,
								contentType: false,
								success: function(
									response) {
									hideLoader();
									// Parse the response and format it
									var modalMessage = response
										.map(
											function(
												message) {
												return message
													.replace(
														/"/g,
														''); // Remove double quotes
											})
										.join(
											"<br>");

									$(
										"#modalMessage")
										.html(
											modalMessage);
									var responseModal = new bootstrap.Modal(
										document
											.getElementById("responseModal"));
									responseModal
										.show();

									// Optionally, hide the modal after 7 seconds
									setTimeout(
										function() {
											responseModal
												.hide();
										}, 9000000);
								},
								error: function(
									xhr) {
									hideLoader();
									// Handle error response and display in the modal
									var errorMessage = xhr.responseText
										|| "An error occurred. Please try again.";
									$(
										"#modalMessage")
										.html(
											errorMessage
												.replace(
													/"/g,
													'')); // Remove double quotes
									var responseModal = new bootstrap.Modal(
										document
											.getElementById("responseModal"));
									responseModal
										.show();

									// Optionally, hide the modal after 7 seconds
									setTimeout(
										function() {
											responseModal
												.hide();
										}, 9000000);
								}
							});
					});
		});

function showLoader(location) {

	$("#Loader").show();
}

function hideLoader(location) {

	$("#Loader").hide();
}		