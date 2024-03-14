@import org.example.hexlet.NamedRoutes
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
        @if(page.getUsers().isEmpty())
            <p>Users not found</p>
        @else
            @for(var user : page.getUsers())
                <a href="${NamedRoutes.userPath(user.getId())}">${user.getName()}</a>
            @endfor
        @endif
    </p>
</body>
</html>