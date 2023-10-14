<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ремонт, Строительство и Ко</title>

    </head>
<body>
<h1 style="text-align: center;">Ремонт, Строительство и Ко</h1>
<hr>
<h4 style="text-align: center;">Авторизация</h4>
<br>
<form action="/login" method="post">
    <label for="email">Почта</label><input type="email" required name="email"><br>
    <label for="password">Пароль</label><input type="password" name="password">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input style="margin-top: 10px;margin-bottom: 20px;" type="submit" class="b1" value="Вход"/>
</form>
<div style="text-align: center;">
    <a href="/registration" class="text-dark">Зарегистрироваться</a>
</div>
</body>
</html>