// Function to serialize form key-value data into a JSON format compatible STRING.
// since out API ONLY accepts JSON format with the correct Content-Type headers.
function formToJson() {
    var jsonObj = {};
    var formSerializeArr = $("#text-form").serializeArray();
    jQuery.map( formSerializeArr, function( n, i ) {
        jsonObj[n.name] = n.value;
    });

    return JSON.stringify(jsonObj);  // Turns an JS object into a JSON compliant string.
}


// Only run this JavaScript code when the HTML has been fully loaded.
$(document).ready(function () {   

    var table = $("#text-table").DataTable({
        ajax: {
            url: "/analytics",  // URL from where to get data.
            dataSrc: "",        // If the result is a dictionary, in which key you can find the array.
        },

        // Specifies to which table header which value to give from the response.
        // First column is "Autor" so you assign all "author" keys from the response to that column.
        columns: [
            { data: "author" },
            { data: "title" },
            { data: "category" },
            { data: "text" },
            { data: "uniqueWordCount" },
            { data: "wordOccurances" },
            { data: "characterOccurances"},
            { data: "specialCharOccurances"},
        ]
    });

    
    var form = $("#text-form")
    form.submit(function (event) {  // Add a submit hook to the form.
        event.preventDefault();     // Disable default behaviour of refreshing the page on submit.
        $.ajax({
            url: "/textEntities",
            contentType: "application/json",  // Metadata that specifies our request is JSON compliant.
            method: "POST",
            data: formToJson(),
            success: function(){
                table.ajax.reload();          // Since our table does not update automatically, we do it on a successful submit
                form[0].reset();              // Since preventDefault() blocks the refrest behaviour which clears the form,
                                              // we have to do it manually.
            }
        })
    })

})