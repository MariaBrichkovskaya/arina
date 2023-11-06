<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <title>Device service</title>
    <script>
        function setValues() {
            document.getElementById("mc1").checked = ${sortedByDate?c};
            document.getElementById("mc2").checked = ${sortedByStatus?c};
                const select = document.getElementById("select-status");
                if(${currentStatus} === 0) select.options[${currentStatus}].selected = true;
                else select.options[${currentStatus}].selected = true;
            }
        function updateCheckedAndSubmit() {
            if(document.getElementById("mc1").checked != ${sortedByDate?c}) document.getElementById("mc2").checked = null;
            else if (document.getElementById("mc2").checked != ${sortedByStatus?c}) document.getElementById("mc1").checked = null;

            document.getElementById("form").submit();
        }
    </script>
</head>
<body onload="setValues()">
<a href="/">Вернуться на главную</a>
<h1>DEVICES</h1><hr>
<form action="/devices" method="get" id="form">
    Поиск по статусу <select name="status" onchange="form.submit()" id="select-status">
        <option value="">Выберите статус</option>
        <#list statuses as status>
            <option value="${status.valueOf(status.name())}">${status.name()}</option>
        </#list>
        <option value="">Отменить поиск</option>
    </select>
        <input type="checkbox" name="sortedByDate" id="mc1" onchange="updateCheckedAndSubmit()">
        <label for="mc1">Сортировка по дате</label>

        <input type="checkbox" name="sortedByStatus" id="mc2" onchange="updateCheckedAndSubmit()">
        <label for="mc2">Сортировка по статусу</label>
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
        Название <input type="text" required name="name"/><br><br>
        Бренд <input type="text" required name="brand"/><br><br>
        Стоимость <input type="number" required step="0.01" name="cost" руб./><br><br>
        Дата окончания ремонта <input type="date" required pattern="yyyy-MM-dd" name="acceptanceDate"/><br><br>

        Пользователь <select required name="user">
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