<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <title>Device service</title>
</head>
<body>
<a href="/">Вернуться на главную</a>
<h1>DEVICES</h1><hr>
<#list users as user>
    <div>
        <p><#if user.name?has_content><b>${user.name}</b></#if> ${user.surname}| <a href="/users/${user.id}">Подробнее</a></p>
    </div>
<#else>
    <h3>Пользователей не наблюдается</h3>
</#list>

<h3>Добавление нового пользователя</h3>
<form action="/users" method="post" enctype="multipart/form-data">
    Имя <input type="text" required name="name"/><br><br>
    Фамилия <input type="text" required name="surname"/><br><br>
    Электронная почта <input type="email" required name="email"/><br><br>
    Пароль <input type="text" required name="password"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить пользователя"/>
</form>
</body>
</html>