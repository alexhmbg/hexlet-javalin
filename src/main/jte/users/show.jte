@import org.example.hexlet.dto.users.UserPage
@param UserPage page

<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello Hexlet!</title>
</head>
<body>
    <h1>${page.getUser().getId()} ${page.getUser().getName()}</h1>
</body>
</html>