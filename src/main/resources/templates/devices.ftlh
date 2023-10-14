<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <title>Device service</title>
</head>
<body>
<h1>DEVICES</h1><hr>
<form action="/devices" method="get" onchange="this.submit()">
    Поиск по статусу <select name="status">
        <option value="">Выберите статус</option>
        <#list statuses as status>
            <option value="${status.valueOf(status.name())}">${status.name()}</option>
        </#list>
        <option value="">Отменить поиск</option>
    </select>
    <form>
        <input type="radio" name="sortedByDate" id="mc1" <#if sortedByDate>checked="checked"</#if>>
        <label for="mc1">Сортировка по дате</label>

        <input type="radio" name="sortedByStatus" id="mc2" <#if sortedByStatus>checked="checked"</#if>>
        <label for="mc2">Сортировка по статусу</label>

    </form>


</form><br>
<#list devices as device>
    <div>
        <p><#if device.name?has_content><b>${device.name}</b></#if> ${device.issueDate} |${device.status.name()}| <a href="/devices/${device.id}">Подробнее</a></p>
    </div>
<#else>
    <h3>Девайсов не наблюдается</h3>
</#list>

    <h3>Добавление нового девайса</h3>
    <form action="/devices" method="post" enctype="multipart/form-data">
        Название <input type="text" name="name"/><br><br>
        Бренд <input type="text" name="brand"/><br><br>
        Стоимость <input type="number" required step="0.01" name="cost" руб./><br><br>
        Дата окончания ремонта <input type="date" required pattern="yyyy-MM-dd" name="acceptanceDate"/><br><br>

        Пользователь <select name="user">
            <option value="">Выберите пользователя</option>
            <#list users as user>
                <option value="${user.id}">${user.name} ${user.surname}</option>
            </#list>
        </select>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить девайс"/>
    </form>
</body>
</html>