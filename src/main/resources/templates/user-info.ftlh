<!DOCTYPE html>
<html>
<head>
    <title>DEVICES</title>
</head>
<body>
<a href="/">Вернуться на главную</a>
<h1>USER</h1><hr>
<h4>Подробная информация о пользователе</h4>
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
<h3>Удаление пользователя</h3>
<form action="/users/${user.id}" method="post" enctype="multipart/form-data">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить пользователя"/>
</form><br>
<a href="/users/edit/${user.id}">Редактирование информации</a>
</body>
</html>