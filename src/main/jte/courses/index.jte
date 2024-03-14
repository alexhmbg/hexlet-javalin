@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello Hexlet!</title>
</head>
<body>
<form action="${NamedRoutes.coursesPath()}" method="get">
    <input type="search" name="term" value="${page.getTerm()}" />
    <input type="submit" value="Search" />
</form>
@if(page.getCourses().isEmpty())
    <p>Courses not found</p>
@else
    @for(var course : page.getCourses())
        <div>
            <h2><a href="${NamedRoutes.coursePath(course.getId())}">${course.getName()}</a></h2>
            <p>${course.getDescription()}</p>
        </div>
    @endfor
@endif
</body>
</html>