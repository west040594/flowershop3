<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper>
    <div class="card mb-3">
        <div class="card-header">
            <span>
                <a href="${pageContext.request.contextPath}/products/index">Продукты</a> /
                ${product.name}
            </span>
        </div>
        <img class="card-img-top" src="${pageContext.request.contextPath}/image?type=product&img=${product.imageUrl}" alt="${product.imageUrl}">
        <div class="card-body">
            <h4 class="card-title">${product.name}
                <span class="badge badge-warning">${product.category.name}</span>
            </h4>
            <p class="card-text">${product.description}</p>
            <h5>В наличии: <span class="badge badge-primary">${product.inStock}</span></h5>
            <h5>Цена: <span class="badge badge-info">${product.priceRub}</span></h5>
            <a href="#" class="btn btn-success w-100" role="button">В корзину</a>
        </div>
        <div class="card-footer">
            <small class="text-muted">${product.createdAtFormat}</small>
        </div>
    </div>
</t:wrapper>