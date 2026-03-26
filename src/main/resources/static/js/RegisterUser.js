$(document).ready(function () {
            $("#uploadForm").submit(function (event) {
                event.preventDefault();

                // Validate file size and type
                var fileInput = $("#profilePicture")[0];
                if (fileInput.files.length > 0) {
                    var file = fileInput.files[0];
                    var fileSize = file.size / 1024 / 1024; // Size in MB
                    var fileType = file.type;

                    if (!fileType.startsWith("image/")) {
                        alert("Please upload a valid image file.");
                        return;
                    }

                    if (fileSize > 5) { // Limit file size to 5MB
                        alert("File size must be less than 5MB.");
                        return;
                    }
                }

                // Proceed with AJAX request
                var formData = new FormData(this);

                $.ajax({
                    url: "register_user",
                    type: "POST",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        $("#modalMessage").text(response);
                        $("#responseModal").modal("show");

                        setTimeout(function () {
                            $("#responseModal").modal("hide");
                        }, 3000);
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