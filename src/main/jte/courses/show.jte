@import org.example.hexlet.dto.courses.CoursePage
@param CoursePage page

<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello Hexlet!</title>
</head>
<body>
<h1>${page.getCourse().getName()}</h1>
@if(page.getCourse().getId().equals(100L))
    <p>Course not found</p>
@else
        <div>
            <p>${page.getCourse().getDescription()}</p>
        </div>
@endif
</body>
</html>