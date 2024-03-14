@import org.example.hexlet.NamedRoutes

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
<p>
    <a href="${NamedRoutes.usersPath()}">Users</a>
</p>
<p>
    <a href="${NamedRoutes.buildUserPath()}">Add User</a>
</p>
<p>
    <a href="${NamedRoutes.coursesPath()}">Courses</a>
</p>
<p>
    <a href="${NamedRoutes.buildCoursePath()}">Add Course</a>
</p>
</body>
</html>