<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">Имя\Фамилия</th>
            <th scope="col">Адрес</th>
            <th scope="col">Сумма</th>
            <th scope="col">Статус</th>
            <th scope="col">Создан</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <th scope="row">${order.id}</th>
                <td>${order.customer.firstName} ${order.customer.lastName}</td>
                <td>${order.deliveryAddress}</td>
                <td>${order.totalRub}</td>
                <td>${order.createdAt}</td>
                <td>${order.status}</td>
                <td>${order.orderProduct.}</td>
                <c:if test="order.status = 'PAID'">

                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:wrapper>