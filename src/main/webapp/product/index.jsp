<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper>

    <div class="row">
        <c:forEach var="product" items="${products}">
        <div class="col-sm-6 col-md-4 col-lg-4 mb-lg-4">
            <div class="card">
                <img class="card-img-top" src="${product.imageUrl}" alt="${product.imageUrl}">
                <div class="card-body">
                    <h5 class="card-title">${product.name}
                        <span class="badge badge-warning">${product.category.name}</span>
                        <span class="badge badge-info">${product.price}</span>
                    </h5>
                    <p class="card-text">${product.description}</p>
                    <div class="btn-group d-flex" role="group">
                        <a href="/products/view?id=${product.id}" class="btn btn-info w-100" role="button">Открыть</a>
                        <a href="#" class="btn btn-success w-100" role="button">В корзину</a>
                    </div>
                </div>
                <div class="card-footer">
                    <small class="text-muted">${product.createdAt}</small>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>

</t:wrapper>