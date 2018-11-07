<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper>
<div class="row" >
    <div class="col-sm-2">
        <form method="get" action="/products/index">
          <div class="form-group">
            <label for="minPrice">Мин: </label>
            <input type="range" min="10" max="500" step="10" value="${param.minPrice}"
            class="form-control custom-range" id="minPrice" name="minPrice" placeholder="min">
          </div>
          <div class="form-group">
            <label for="maxPrice">Макс: </label>
            <input type="range" min="10" max="500" step="10" value="${param.maxPrice}"
            class="form-control custom-range" id="maxPrice" name="maxPrice" placeholder="max">
          </div>
          <div class="form-group">
                <label class="mr-sm-2" for="inlineFormCustomSelect">Категория</label>
                <select class="custom-select mr-sm-2" id="category" name="category">
                  <option disabled selected value>Выберите...</option>
                  <c:forEach var="category" items="${categoryList}">
                    <option value="${category.id}">${category.name}</option>
                  </c:forEach>
                </select>
          </div>
          <button type="submit" class="btn btn-sm btn-success">Применить</button>
        </form>
    </div>
    <div class="col-sm-10">
        <form method="get" action="/products/index">
          <div class="input-group">
            <input type="text" name="productname" id="productname"
            value="${searchProductName}" class="form-control" placeholder="Поиск">
            <div class="input-group-btn">
              <button class="btn btn-default" type="submit">
                <i class="glyphicon glyphicon-search"></i>
              </button>
            </div>
          </div>
        </form>
        <div class="row">
            <c:forEach var="product" items="${products}">
            <div class="col-sm-6 col-md-4 col-lg-4 mb-lg-4">
                <div class="card">
                    <img class="card-img-top" src="${pageContext.request.contextPath}/image?type=product&img=${product.imageUrl}" alt="${product.imageUrl}">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}
                            <span class="badge badge-warning">${product.category.name}</span>
                            <span class="badge badge-info">${product.priceRub}</span>
                        </h5>
                        <p>В наличии: <span class="badge badge-primary">${product.inStock}</span> </p>
                        <p class="card-text">${product.description}</p>
                        <div class="btn-group d-flex" role="group">
                            <a href="/products/view?id=${product.id}" class="btn btn-info w-100" role="button">Открыть</a>
                            <c:if test="${sessionScope.user != null}">
                                <btn class="btn btn-success w-100" role="addCartItem" data-id="${product.id}">В корзину</btn>
                            </c:if>
                            <c:if test="${sessionScope.user == null}">
                                <a href="/login" class="btn btn-success w-100">В корзину</a>
                            </c:if>
                        </div>
                    </div>
                    <div class="card-footer">
                        <small class="text-muted">${product.createdAtFormat}</small>
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>

</div>
</t:wrapper>