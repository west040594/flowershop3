<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper>
    <div class="row">
        <div class="col-7">
            <table class="table">
              <thead>
                <tr>
                  <th colspan="4" scope="col">Корзина</th>
                  <th scope="col"><button class="btn btn-danger btn-sm">Удалить все</button></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="cartItem" items="${sessionScope.cart.itemList}">
                    <tr>
                      <td scope="row">
                        <img style="height:70px; width:110px" class="card-img-top" src="${pageContext.request.contextPath}/image?type=product&img=${cartItem.product.imageUrl}" alt="${product.imageUrl}">
                      </td>
                      <td>${cartItem.product.name}</td>
                      <td>${cartItem.quantity}</td>
                      <td><del>${cartItem.cartItemTotalRub}</del></td>
                      <td>${cartItem.cartItemTotalDiscountRub}</td>
                    </tr>
                </c:forEach>
              </tbody>
              <tfoot>
                <tr>
                    <td colspan="5">
                        <span class="text-warning">Цена с учетом общей скидки</span>
                        <span id="cart-total"<b>${sessionScope.cart.totalRub}</b></span>
                        </td>
                </tr>
              </tfoot>
            </table>
        </div>
        <div class="col-5">
            <form method="post" action="cart">
                  <div class="form-group">
                    <label for="firstName">Имя</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" value="${sessionScope.user.customer.firstName}" placeholder="Введите имя">
                  </div>
                  <div class="form-group">
                    <label for="lastName">Фамилия</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" value="${sessionScope.user.customer.lastName}" placeholder="Введите фамилию">
                  </div>
                  <div class="form-group">
                     <label for="phone">Телефон</label>
                     <input type="text" class="form-control" id="phone" name="phone" placeholder="Введите телефон">
                  </div>
                  <div class="form-group">
                    <label for="street">Улица</label>
                    <input type="text" class="form-control" id="street" name="street" placeholder="Введите адрес">
                  </div>
                  <div class="form-group">
                    <label for="city">Город</label>
                    <input type="text" class="form-control" id="city" name="city" placeholder="Введите город">
                  </div>
                  <div class="form-group">
                     <label for="country">Страна</label>
                     <input type="text" class="form-control" id="country" name="country" placeholder="Введите страну">
                  </div>
                  <button type="submit" class="btn btn-primary">Подтвердить</button>
                </form>
                <c:if test="${error != null}">
                    <div class="my-lg-4 alert alert-danger" role="alert">
                            ${error}
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</t:wrapper>