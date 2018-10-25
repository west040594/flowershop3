<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:wrapper>
    <table class="table">
      <thead>

      </thead>
      <tbody>
        <tr>
            <td>ID Заказа</td>
            <td>${order.id}</td>
        </tr>
        <tr>
            <td>Имя</td>
            <td>${order.deliveryAddress}</td>
        </tr>
        <tr>
            <td>Статус</td>
            <td>${order.status}</td>
        </tr>
        <tr>
            <td>Создано</td>
            <td>${order.createdAtFormat}</td>
        </tr>
        <tr>
            <td>Общая стоимость</td>
            <td>${order.totalRub}</td>
        </tr>

      </tbody>
      <tfoot>

      </tfoot>
    </table>
</t:wrapper>
