<!DOCTYPE html>
<html>
<head>
    <title>DEVICES</title>
</head>
<body>
<a href="/">Вернуться на главную</a>
<hr>
<h3>Редактирование аккаунта</h3>
<form action="users/editing/${user.id}" method="post" enctype="multipart/form-data">
    Почта: <input type="text" value="${user.email}" name="login"/><br><br>
    Имя <input type="text" VALUE="${user.name}" name="name"/><br><br>
    Фамилия <input type="text" value="${user.surname}" name="surname"/><br><br>
    <h4>Для изменения пароля подтвердите текущий пароль</h4>
    Старый пароль: <input type="password" name="oldPassword"/><br><br>
    Новый пароль: <input type="password" name="password"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Изменить информацию"/>
</form>
<form action="users/delete/${user.id}" method="post" enctype="multipart/form-data">
    <h4>Для удаления аккаунта подтвердите пароль</h4>
    Пароль: <input type="password" name="password"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить"/>
</form>
</body>
</html>