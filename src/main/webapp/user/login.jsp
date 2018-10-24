<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:wrapper>
    <form method="post" action="login">
      <div class="form-group">
        <label for="username">Логин/Email</label>
        <input type="text" class="form-control" id="username" name="username" placeholder="Введите Логин или Email">
      </div>
      <div class="form-group">
        <label for="password">Пароль</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Введите Пароль">
      </div>
      <button type="submit" class="btn btn-primary">Подтвердить</button>
        <a  class="btn btn-success" href="/register">Регистрация</a>
    </form>
    <c:if test="${error != null}">
      <div class="my-lg-4 alert alert-danger" role="alert">
          ${error}
      </div>
    </c:if>
</t:wrapper>
