<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper>

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
                        <btn class="btn btn-success w-100" role="addCartItem" data-id="${product.id}">В корзину</btn>
                    </div>
                </div>
                <div class="card-footer">
                    <small class="text-muted">${product.createdAtFormat}</small>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>

</t:wrapper>