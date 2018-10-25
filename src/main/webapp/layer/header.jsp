<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
    <a class="navbar-brand" href="#">Flowershop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/index">Главная <span class="sr-only">(current)</span></a>
            </li>
            <c:if test="${sessionScope.user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="/register">Регистрация</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Вход</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a href="/products/index" class="nav-link">Цветы</a>
            </li>
            <li class="nav-item">
                <a href="/users" class="nav-link">Пользователи</a>
            </li>
            <c:if test="${sessionScope.user != null}">
                <span class="navbar-text"><b>Логин:</b> ${sessionScope.user.username}&nbsp;</span>
                <span class="navbar-text"><b>Баланс</b>: ${sessionScope.user.customer.balanceRub}&nbsp;</span>
                <span class="navbar-text"><b>Скидка:</b> ${sessionScope.user.customer.discountPercent}&nbsp;</span>
                <li class="nav-item">
                    <a class="nav-link btn btn-danger btn-sm" href="/logout">Выйти</a>
                </li>
            </c:if>
        </ul>
        <ul class="navbar-nav ml-auto">
            <c:if test="${sessionScope.cart != null}">
                <%--<span class="glyphicon glyphicon-shopping-cart"></span>--%>
                <div class="navbar-text"><b>Корзина:</b> &nbsp;</div>
                <div class="navbar-text">Количество: <span id ="cart-quantity">${sessionScope.cart.itemCount}</span>&nbsp;</div>
                <div class="navbar-text">Цена: <span id= "cart-total">${sessionScope.cart.totalRub}</span>&nbsp;</div>
                <div class="navbar-text"><a href="/cart" class="btn btn-warning btn-sm">Перейти</a></div>
            </c:if>
        </ul>
    </div>
</nav>