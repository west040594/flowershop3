<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Пользователь</th>
            <th scope="col">Email</th>
            <th scope="col">Имя</th>
            <th scope="col">Баланс</th>
            <th scope="col">Адрес</th>
            <th scope="col">Статус</th>
            <th scope="col">Роль</th>
            <th scope="col">Создан</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <th scope="row">${user.id}</th>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.customer.firstName} ${user.customer.lastName}</td>
                <td>${user.customer.balance}</td>
                <td>
                    ${user.customer.street} /
                    ${user.customer.city} /
                    ${user.customer.country}
                </td>
                <td>${user.status}</td>
                <td>${user.role}</td>
                <td>${user.createdAt}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:wrapper>