<!DOCTYPE html>
<html>
<head>
    <title>DEVICES</title>
</head>
<body>
<a href="/">Вернуться на главную</a>
<hr>
<h3>Редактирование информации о заявке</h3>
<form action="/devices/editing/${device.id}" method="post" enctype="multipart/form-data">
    Название <input type="text" name="name" value="${device.name}"/><br><br>
    Бренд <input type="text" name="brand" value="${device.brand}"/><br><br>
    <#if device.cost??&&device.acceptanceDate??>
        Стоимость <input type="number" required step="0.01" name="cost" value="${device.toStringCost()}" руб./><br><br>
        Дата окончания ремонта <input type="date" value="${device.acceptanceDate}" name="acceptanceDate"/><br><br>
        <#else>
            Стоимость <input type="number" required step="0.01" name="cost" /><br><br>
            Дата окончания ремонта <input type="date" name="acceptanceDate"/><br><br>
    </#if>

    Статус <select name="status">
        <option value="${device.status}">${device.status.valueStr}</option>
        <#list statuses as status>
            <option value="${status}">${status.valueStr}</option>
        </#list>
    </select>
    <input type="hidden" name="user" value="${device.user.id}">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Изменить информацию"/>
</form>
</body>
</html>