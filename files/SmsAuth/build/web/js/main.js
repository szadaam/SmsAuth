function redirect(url) {
    window.location.replace(url);
}

function redirect_timeout(url, milliseconds) {
    setTimeout(function() { 
        window.location.replace(url);
    }, milliseconds);
}

$(document).on("submit", "#login", function(e) {
    e.preventDefault(); 
    
    var post_data = {
        tel: $("#tel").val(),
        code: $("#code").val()
    };
    
    $.ajax({
        type: "post",
        url: "ajax/authentication.jsp",
        data: post_data,
        success: function(response) {
            response = response.trim();
            console.log(response);
            login_callback(response);
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
        }  
    });
});

function login_callback(response) {
    if(response == "success") {
        redirect("loggedin.jsp");
    } else {
        alert("Hiba: Rossz jelszót adott meg.");
    }
}