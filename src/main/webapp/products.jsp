<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Наименование</th>
            <th scope="col">Цена</th>
            <th scope="col">Категория</th>
            <th scope="col">Описание</th>
            <th scope="col">Изображение</th>
            <th scope="col">Посмотреть</th>
            <th scope="col">Заказать</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.category.name}</td>
                <td>${product.description}</td>
                <td>${product.imageUrl}</td>
                <td><a href="products/view/${product.id}" class="btn btn-info" role="button">Открыть</a></td>
                <td><a href="#" class="btn btn-success" role="button">В корзину</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:wrapper>