$(document).ready(function () {
    $closeOrderButtons = $('btn[role=closeOrder]')

    $closeOrderButtons.each(function() {
        var orderId = $(this).data("id");
        var that = this;
        $(this).on('click', function() {
            $.ajax({
                url: '/rest/order/close',
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify({orderId: orderId}),
            }).done(function(data) {
                console.log(data);
                $('tr[data-id=' + data.id + ']').find('.orderStatus').html(data.status);
                $(that).remove();
            });
        })
    })

});