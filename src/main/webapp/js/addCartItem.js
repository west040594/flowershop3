$(document).ready(function () {

    var $addCartItemButtons = $('[role=addCartItem]');
    var $removeCartItemButtons = $('[role=removeCartItem]')
    var $removeAllItemsButtons = $('[role=removeAllCartItems]');
    var $orderSubmitButton = $('#orderForm').find("button[type=submit]");
    var $orderProductsCartBody = $('table#orderProductsCart').find('tbody');

    var itemListCount = 0;

    function checkCartIsEmpty() {
        if($orderProductsCartBody.children().length != 0) {
            $orderSubmitButton.prop("disabled", false);
            $removeAllItemsButtons.prop("disabled", false);
        } else {
            $orderSubmitButton.prop("disabled", true);
            $removeAllItemsButtons.prop("disabled", true);
        }
    }
    checkCartIsEmpty();
    function refreshCartFields(data) {
        $('.cart-quantity').html(data.itemCount);
        $('.cart-total').each(function() {
            $(this).html(data.totalRub);
        });

        //Цикл по tr
        $orderProductsCartBody.find('tr').each(function(index) {
            var findFlag = false;
            //Цикл по предметам корзины
            //Если айдишники совпали меняем значения tr, ставим флаг что нашли и выходим из цикла
            for(var i = 0; i < data.itemList.length; i++) {
                console.log($(this).data("id") + data.itemList[i].product.id);
                if($(this).data("id") == data.itemList[i].product.id) {
                    $(this).find('.ciQuantity').html(data.itemList[i].quantity);
                    $(this).find('.ciTotalRub').html('<del>' + data.itemList[i].cartItemTotalRub + '</del>');
                    $(this).find('.ciTotalDiscountRub').html( data.itemList[i].cartItemTotalDiscountRub);
                    findFlag = true;
                    break;
                }
            }
            //Если совпадения не найдено, то удаляем строку
            if(!findFlag) $(this).remove();
        });
    }

    $addCartItemButtons.each(function () {
        var productId = $(this).attr('data-id');
        $(this).on('click', function () {
            $.ajax({
               type: "POST",
               url: '/rest/shoppingcart/add',
               contentType: "application/json; charset=utf-8",
               data: JSON.stringify({productId: productId}),
               dataType: "json",

            }).done(function (data) {
                refreshCartFields.call(this, data);
            })
        });
    })

    $removeCartItemButtons.each(function () {
            var productId = $(this).attr('data-id');
            $(this).on('click', function () {
                $.ajax({
                   type: "POST",
                   url: '/rest/shoppingcart/remove',
                   contentType: "application/json; charset=utf-8",
                   data: JSON.stringify({productId: productId}),
                   dataType: "json",

                }).done(function (data) {
                    refreshCartFields.call(this, data);
                    checkCartIsEmpty();
                })
            });
        })



    $removeAllItemsButtons.each(function() {
        $(this).on('click', function() {
            $.ajax({
                type: "GET",
                url: '/rest/shoppingcart/removeall',
                contentType: "application/json; charset=utf-8",
                dataType: "json",

            }).done(function(data) {
                $('.cart-quantity').html(data.itemCount);
                $('.cart-total').each(function() {
                    $(this).html(data.totalRub);
                });
                $orderProductsCartBody.empty();
                checkCartIsEmpty();
            })
        })
    })


});