$(document)
	.ready(
		function() {
			$("#bulkUploadForm")
				.submit(
					function(event) {
						event.preventDefault();
						showLoader();
debugger;
						var files = $("#fileUpload")[0].files;
						var dateInput = $("#dateInput")
							.val();
						var category = $("#category")
							.val();
							var subcategory ;
						if (category == "RUPAY" || category == "QSPARC" ) {

							subcategory = $(
								"#subcategory2DI").val();
							if (files.length === 0 || !dateInput
								|| !category
								|| !subcategory) {
								alert("Please fill all required fields.");
								return;
							}
						} else 	if (category == "MASTERCARD" ) {

							subcategory = $(
								"#subcategory2AP").val();
							if (files.length === 0 || !dateInput
								|| !category
								|| !subcategory) {
								alert("Please fill all required fields.");
								return;
							}
						}else 	if (category == "NFS" ) {

							subcategory = $(
								"#subcategory4AP").val();
							if (files.length === 0 || !dateInput
								|| !category
								|| !subcategory) {
								alert("Please fill all required fields.");
								return;
							}
						}else {

							subcategory = $(
								"#subcategoryIA").val();

							if (files.length === 0|| !dateInput
								|| !category
								|| !subcategory) {
								alert("Please fill all required fields.");
								return;
							}

						}
						var formData = new FormData();
						for (var i = 0; i < files.length; i++) {
							formData.append("files",
								files[i]);
						}
						formData.append("fileDate",
							dateInput);
						formData.append("category",
							category);
						formData.append("subcategory",
							subcategory);

						$
							.ajax({
								url: "/I-RECON/mainFileUpload",
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


function setfilename(e) {
	debugger;
	//alert(e.value);
	// || e.value=="VISA"
	if (e.value == "RUPAY" || e.value == "QSPARC" ) {

		document.getElementById("subcategory2").style.display = '';
		document.getElementById("subcategory").style.display = 'none';
		document.getElementById("subcategory3").style.display = 'none';
			document.getElementById("subcategory4").style.display = 'none';

	}else if (e.value == "MASTERCARD" ) {

		document.getElementById("subcategory3").style.display = '';
		document.getElementById("subcategory2").style.display = 'none';
		document.getElementById("subcategory").style.display = 'none';
			document.getElementById("subcategory4").style.display = 'none';

	} else if (e.value == "NFS" ) {

		document.getElementById("subcategory4").style.display = '';
		document.getElementById("subcategory2").style.display = 'none';
		document.getElementById("subcategory").style.display = 'none';
			document.getElementById("subcategory3").style.display = 'none';

	}else {

		document.getElementById("subcategory2").style.display = 'none';
		document.getElementById("subcategory3").style.display = 'none';
		document.getElementById("subcategory").style.display = '';
			document.getElementById("subcategory4").style.display = 'none';
	}

}