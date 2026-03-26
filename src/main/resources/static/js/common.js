$(document).ready(function () {
    $("#header-container").load("./fragments.html .header");
    $("#sidebar-container").load("./fragments.html .sidebar", function () {
        initializeSidebar(); // Initialize sidebar after loading
    });
	$("#footer-container").load("./fragments.html .footer"); // Load footer
});

function initializeSidebar() {
    console.log('Initializing Sidebar...');
    restoreSidebarState();
    setActiveSidebarItem(); // Set active state based on the current URL

    $(document).on('click', '[data-bs-toggle="collapse"]', function () {
        var target = $(this).data('bs-target');
        $(target).on('shown.bs.collapse hidden.bs.collapse', function () {
            saveSidebarState();
        });
    });

    $(document).on('click', '.sidebar a', function (e) {
        if ($(this).attr('href') !== '#') {
            saveSidebarState();
        }
    });
}

function saveSidebarState() {
    var sidebarState = {};
    $('.collapse').each(function () {
        var id = $(this).attr('id');
        if (id) {
            sidebarState[id] = $(this).hasClass('show');
        }
    });
    localStorage.setItem('sidebarState', JSON.stringify(sidebarState));
}

function restoreSidebarState() {
    var sidebarState = JSON.parse(localStorage.getItem('sidebarState'));
    if (sidebarState) {
        Object.keys(sidebarState).forEach(function (id) {
            var $target = $('#' + id);
            if ($target.length) {
                if (sidebarState[id]) {
                    $target.collapse('show');
                } else {
                    $target.collapse('hide');
                }
            }
        });
    }
}

// Function to set the active class based on the current URL
function setActiveSidebarItem() {
    // Get the current URL
    const currentUrl = window.location.href;

    // Loop through all sidebar links
    $('.sidebar ul li a').each(function () {
        const linkUrl = $(this).attr('href');

        // Check if the link URL matches the current URL
        if (currentUrl.includes(linkUrl)) {
            // Remove active class from all links
            $('.sidebar ul li a').removeClass('active');

            // Add active class to the current link
            $(this).addClass('active');

            // If the link is inside a dropdown, expand the dropdown
            const parentDropdown = $(this).closest('.collapse');
            if (parentDropdown.length) {
                parentDropdown.collapse('show');
            }
        }
    });
}

// Call the initializeSidebar function when the document is ready
$(document).ready(function () {
    initializeSidebar();
});


//calender

$(document).ready(function () {
    $("#dateInput").flatpickr({
        dateFormat: "Y/m/d",  // Format as YYYY-MM-DD
       /* position: "auto center", // Center the calendar*/
        allowInput: true, // Allow manual input
        placeholder: "YYYY/MM/DD", // Ensures it remains visible until a date is selected
        onReady: function(selectedDates, dateStr, instance) {
            instance.input.value = ""; // Ensures the field remains empty until a date is selected
        },
        onChange: function(selectedDates, dateStr, instance) {
            instance.input.value = dateStr; // Set the input field only after selection
        }
    });
});