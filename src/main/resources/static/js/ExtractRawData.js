$(document).ready(function () {
           // Extract 'category' and 'option' from the URL
           const params = new URLSearchParams(window.location.search);
           const category = params.get('category');
           const option = params.get('option');

           if (category && option) {
               $('#heading').text(`${category} ${option}`);
           } else {
               $('#heading').text("Default Heading");
           }
       });
       
       
       