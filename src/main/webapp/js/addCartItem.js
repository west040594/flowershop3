$(document).ready(function () {

    var addCartButtons = $('[role=addCartItem]');
    
    addCartButtons.each(function () {
        var productId = $(this).attr('data-id');
        $(this).on('click', function () {
            $.ajax({
               url: '/addCartItem',
               type: "POST",
               data: {productId: productId}
                
            }).done(function (data) {
                $('#cart-quantity').html(data.quantity);
                $('#cart-total').html(data.totalRub);
            })
        });
    })


});