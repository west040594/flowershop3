$(document).ready(function () {

    var $labelMinPrice = $('label[for=minPrice]');
    var $labelMaxPrice = $('label[for=maxPrice]');

    var $inputMaxPrice = $('input#maxPrice');
    var $inputMinPrice = $('input#minPrice');

    $inputMinPrice.on('change input', function() {
        $labelMinPrice.html("Мин: " + $(this).val());
    });

    $inputMaxPrice.on('change input', function() {
        $labelMaxPrice.html("Макс: " + $(this).val());
    });

    $inputMaxPrice.change();
    $inputMinPrice.change();
});