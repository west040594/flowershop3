<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:wrapper>
    <c:if test="${!registered}">
        <form method="post" action="register">
          <div class="form-group">
            <label for="firstName">Имя</label>
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Введите имя">
          </div>
          <div class="form-group">
            <label for="lastName">Фамилия</label>
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Введите фамилию">
          </div>
          <div class="form-group">
             <label for="username">Логин</label>
             <input type="text" class="form-control" id="username" name="username" placeholder="Введите логин">
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="Введите email">
          </div>
          <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Введите пароль">
          </div>
          <div class="form-group">
             <label for="passwordConfirm">Подтвердите пароль</label>
             <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" placeholder="Введите пароль">
          </div>
          <button type="submit" class="btn btn-primary">Подтвердить</button>
        </form>
        <c:if test="${error != null}">
            <div class="my-lg-4 alert alert-danger" role="alert">
                    ${error}
            </div>
        </c:if>
    </c:if>
</t:wrapper>
