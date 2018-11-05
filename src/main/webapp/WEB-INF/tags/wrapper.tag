<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Flowershop</title>
        <link rel="stylesheet"
                  href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css" />

        <link rel = "stylesheet"
        href="/webjars/bootstrap-glyphicons/bdd2cbfba0/css/bootstrap-glyphicons.css" />

        <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    </head>
    <body>
        <jsp:include page="/layer/header.jsp"/>
        <div class="my-md-4 container">
            <jsp:doBody/>
        </div>

    <script src="/js/cart.js"></script>
    <script src="/js/usernameValidation.js"></script>
    <script src="/js/order.js"></script>
    <script src="/js/product.js"></script>
    </body>
</html>