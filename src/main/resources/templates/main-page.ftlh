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

<#if user.email??&&user.isAdmin()>
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
    <h4>
        <p>
            <a href="users/edit/${user.id}">Аккаунт</a>
        </p>
    </h4>
    <hr>
    <b>Имя </b>${user.name}<br>
    <b>Фамилия </b>${user.surname}<br>
    <b>Почта </b>${user.email}<br>
    <b>Список девайсов:</b><br>
    <#list devices as device>
        <div>
            <p><#if device.name?has_content> ${device.name}| ${device.brand}| <#if device.cost?has_content>${device.cost} руб.<#else>Заявка не обработана</#if> </#if> </p>
        </div>
    <#else>
        <h3>У данного пользователя отсутствуют девайсы</h3>
    </#list><br>
    <p>Итоговая стоимость всех девайсов: ${sum}</p>
    <hr>
    <h3>Добавление нового девайса</h3>
    <form action="/users/devices" method="post" enctype="multipart/form-data">
        Название <input type="text" name="name"/><br><br>
        Бренд <input type="text" name="brand"/><br><br>
        <input type="hidden" name="user" value="${user.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить девайс"/>
    </form>
</#if>


</body>
</html>