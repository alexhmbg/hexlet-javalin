@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
    <p>
        @for(var user : page.getUsers())
            <div>${user.getName()}</div>
        @endfor
    </p>
</body>
</html>