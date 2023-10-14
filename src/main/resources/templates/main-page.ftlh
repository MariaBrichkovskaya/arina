<!DOCTYPE html>
<html>
<head>
    <title>DEVICES</title>

</head>
<body bgcolor="#E6E6FA">
<h1>DEVICES</h1>

<#if user.email??>
    <form action="/logout" method="post">
        <p>Пользователь: <i>${user.email}</i>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" class="b1" value="Выйти"></p>
    </form>
    <p></p>
<#else>
    <h1><a href="/login">Войти</a></h1>
</#if>

<hr>

<#if user.email??>
    <h4>
        <p>
            <a href="/devices">Девайсы</a>
            <a href="/users">Пользователи</a>
            <a href="users/edit/${user.id}">Аккаунт</a>
        </p>
    </h4>
    <hr>
</#if>
<#if user.email??&&user.isUser()>
    <b>Имя </b>${user.name}<br>
    <b>Фамилия </b>${user.surname}<br>
    <b>Почта </b>${user.email}<br>
    <b>Список девайсов:</b><br>
    <#list devices as device>
        <div>
            <p><#if device.name?has_content> ${device.name}| ${device.brand}| ${device.cost} руб.</#if> </p>
        </div>
    <#else>
        <h3>У данного пользователя отсутствуют девайсы</h3>
    </#list><br>
    <p>Итоговая стоимость всех девайсов: ${sum}</p>
    <hr>
</#if>


</body>
</html>