$(document).ready(function () {

    var $usernameInput = $('#registerForm').find("input[id=username]");
    var $usernameExistErrorDiv = $('<div/>', {
        class: 'my-lg-4 alert alert-danger',
        id: 'registerAjaxErrors',
        role: 'alert'
    });
    $usernameExistErrorDiv.html('Логин занят');

    var $registrationSubmitButton = $('#registerForm').find("button[type=submit]");

    $usernameInput.on('change', function() {
        $.ajax({
           url: '/rest/user/exist/' + $(this).val(),
           type: "GET",
        }).done(function (data) {
            if(data) {
                $('#registerForm').after($usernameExistErrorDiv);
                $registrationSubmitButton.prop("disabled", true);
            } else {
                $('#registerAjaxErrors').remove();
                $registrationSubmitButton.prop("disabled", false);
            }
            console.log(data);
        })
    })

});