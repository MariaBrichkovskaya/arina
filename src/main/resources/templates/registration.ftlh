<!DOCTYPE html>
<html>
<head>

    </head>
<body>
<hr>
<h4 style="position: relative; left: 50%; transform: translate(-50%,0); text-indent: 45%">Регистрация</h4><br>
<form action="/registration" method="post">
    <label for="name">Имя</label><input id="name" type="text" required name="name"><br>
    <label for="surname">Фамилия</label><input id="surname" type="text" required name="surname">
    <label for="email">Эл.почта</label><input id="email" type="email" required name="email"><br>
    <label for="password">Пароль</label><input id="password" type="password" required name="password">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" style="margin-top: 10px;margin-bottom: 20px;" class="b1" value="Зарегистрироваться"/>
</form>
<div style="text-align: center;">
    <a href="/login" class="text-dark">Войти</a>
</div>
<#if errorMessage??>
    <h5 style="color:firebrick; position: relative; left: 500px;">Ошибка регистрации. Проверьте указанную почту</h5>
</#if>
</body>
</html>