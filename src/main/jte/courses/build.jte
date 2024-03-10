<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
<form action="/courses" method="post">
    <div>
        <label>
            Name
            <input type="text" required name="name" />
        </label>
    </div>
    <div>
        <label>
            Description
            <input type="text" required name="description" />
        </label>
    </div>
    <input type="submit" value="Зарегистрировать" />
</form>
</body>
</html>