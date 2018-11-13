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
            <th scope="col">Закрыть</th>
            <th scope="col">Открыть</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr data-id = "${order.id}">
                <th scope="row">${order.id}</th>
                <td>${order.customer.firstName} ${order.customer.lastName}</td>
                <td>${order.deliveryAddress}</td>
                <td>${order.totalRub}</td>
                <td class="orderStatus">${order.status}</td>
                <td>${order.createdAt}</td>
                <td>
                    <c:if test="${order.status == 'PAID'}">
                        <btn class="btn btn-danger btn-sm" role="closeOrder" data-id="${order.id}">
                            Закрыть
                        </btn>
                    </c:if>
                </td>
                <td><a href="/orders/view?id=${order.id}" class="btn btn-primary btn-sm">Открыть</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:wrapper>