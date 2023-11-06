<!DOCTYPE html>
<html>
<head>
    <title>DEVICES</title>
</head>
<body>
<a href="/">Вернуться на главную</a>
<hr>
<h3>Редактирование аккаунта</h3>
<form action="/users/editInfo/${user.id}" method="post" enctype="multipart/form-data">
    Почта: <input type="text" required value="${user.email}" name="login"/><br><br>
    Имя <input type="text" required value="${user.name}" name="name"/><br><br>
    Фамилия <input type="text" required value="${user.surname}" name="surname"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button>Изменить информацию</button>
</form>

<form action="/users/editPassword/${user.id}" method="post" enctype="multipart/form-data">
    <h4>Для изменения пароля подтвердите текущий пароль</h4>
    Старый пароль: <input type="password" required name="oldPassword"/><br><br>
    Новый пароль: <input type="password" required name="password"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Изменить пароль"/>
</form>
<form action="/users/delete/${user.id}" method="post" enctype="multipart/form-data">
    <h4>Для удаления аккаунта подтвердите пароль</h4>
    Пароль: <input type="password" name="password"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить"/>
</form>
</body>
</html>