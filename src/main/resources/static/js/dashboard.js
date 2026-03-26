
	document.getElementById("filterLink").addEventListener(
			"click",
			function(e) {
				e.preventDefault(); 
				var dateVal = document.getElementById("fileDate").value;

				if (!dateVal) {
					alert("Please enter a date!");
					return;
				}

			
				window.location.href = "dashboardFilter?fileDate="
						+ encodeURIComponent(dateVal);
			});


	document
			.getElementById("filterLink2")
			.addEventListener(
					"change",
					function() {

						const fileDate = document.getElementById("fileDate2").value;

					
						const targetUrl = `dashboardFilter2?fileDate=${encodeURIComponent(fileDate)}`;
						window.location.href = targetUrl;
					});

	function filterlink() {
		debugger;
		var dateVal = document.getElementById("fileDate").value;

		if (!dateVal) {
			alert("Please enter a date!");
			return;
		}

		
		window.location.href = "dashboardFilter?fileDate="
				+ encodeURIComponent(dateVal);
	}

	function filterByDate() {
		debugger;
		const date = document.getElementById('fileDate2').value;
		if (date) {
		
			console.log("Filtering by date:", date);
			window.location.href = "dashboardFilter2?fileDate="
					+ encodeURIComponent(date);
		}

	}

	function filterByDate3() {
		debugger;
		const dateInput = document.getElementById('fileDate3').value;
		const dateDisplay = document.getElementById('dateDisplay');
		if (dateInput) {
			dateDisplay.textContent = `(${dateInput})`;
		} else {
			dateDisplay.textContent = "(No date selected)";
		}

		console.log("Filtering by date:", dateInput);
		window.location.href = "dashboardFilter3?fileDate="
				+ encodeURIComponent(dateInput);
		if (dateInput) {
			dateDisplay.textContent = `(${dateInput})`;
		} else {
			dateDisplay.textContent = "(No date selected)";
		}

	}
	

function showLoader(location) {

	$("#Loader").show();
}

function hideLoader(location) {

	$("#Loader").hide();
}		