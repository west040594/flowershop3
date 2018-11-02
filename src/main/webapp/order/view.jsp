<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:wrapper>
    <table class="table">
      <thead>
            <tr>
                <th colspan="1" scope="col">Заказ</th>

                <th scope="col">
                    <c:if test="${order.status == 'CREATED'}">
                        <form method="post" action="view">
                            <input type="hidden" name="orderId" value="${order.id}" />
                            <button type="submit" class="btn btn-success">Оплатить</button>
                        </form>
                    </c:if>
                </th>
           </tr>
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
        <c:if test="${order.status == 'CLOSED'}">
            <tr>
                <td>Закрыто</td>
                <td>${order.closetAtFormat}</td>
            </tr>
        </c:if>
        <tr>
            <td>Общая стоимость</td>
            <td>${order.totalRub}</td>
        </tr>
      </tbody>
    </table>
    <table class="table">
      <thead>
            <tr>
                <th colspan="3" scope="col">Товары</th>
           </tr>
      </thead>
      <tbody>
        <c:forEach var="orderProduct" items="${order.orderProducts}">
            <tr>
              <td scope="row">
                <img style="height:70px; width:110px" class="card-img-top" src="${pageContext.request.contextPath}/image?type=product&img=${orderProduct.product.imageUrl}" alt="${product.imageUrl}">
              </td>
               <td>${orderProduct.product.category.name}</td>
              <td>${orderProduct.product.name}</td>
              <td>${orderProduct.quantity}</td>
            </tr>
        </c:forEach>
      </tbody>
    </table>

</t:wrapper>
