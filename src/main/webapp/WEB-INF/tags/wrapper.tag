<%@tag description="tag1" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <link rel="stylesheet"
              href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css" />
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand" href="#">Flowershop</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/index">Главная <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/register">Регистрация</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">Вход</a>
            </li>
            <li class="nav-item">
                <a href="/products" class="nav-link">Цветы</a>
            </li>
            <li class="nav-item">
                <a href="/users" class="nav-link">Пользователи</a>
            </li>
        </ul>
      </div>
    </nav>
    <div class="my-lg-4 container">
        <jsp:doBody/>
    </div>
</body>
</html>