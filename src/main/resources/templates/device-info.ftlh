<!DOCTYPE html>
<html>
<head>
    <title>DEVICES</title>
</head>
<body>
<h1>DEVICES</h1><hr>
<h4>Подробная информация о девайсе</h4>
<b>Название девайса </b>${device.name}<br>
<b>Бренд </b>${device.brand}<br>
<b>Дата заявки </b>${device.issueDate}<br>
<b>Статус </b>${device.status.name()}<br>
<b>Стоимость заказа </b>${device.cost}<br>
<b>Дата окончания ремонта </b>${device.acceptanceDate}<br>
<hr>
    <h3>Удаление девайса</h3>
    <form action="/devices/${device.id}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Удалить девайс"/>
    </form><br>
<a href="/devices/edit/${device.id}">Редактирование информации</a>
</body>
</html>