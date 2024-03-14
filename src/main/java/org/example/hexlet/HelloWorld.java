package org.example.hexlet;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.render("layout/page.jte");
        });

        app.get(NamedRoutes.usersPath(), ctx -> {
            var page = new UsersPage(UserRepository.getEntities());
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        app.get(NamedRoutes.buildUserPath(), ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/build.jte", Collections.singletonMap("page", page));
        });

        app.post(NamedRoutes.usersPath(), ctx -> {
            var name = ctx.formParam("name");
            var email = ctx.formParam("email");

            try {
                var passwordConfirmation = ctx.formParam("passwordConfirmation");
                var password = ctx.formParamAsClass("password", String.class)
                        .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                        .get();
                var user = new User(name, email, password);
                UserRepository.save(user);
                ctx.redirect(NamedRoutes.usersPath());
            } catch (ValidationException e) {
                var page = new BuildUserPage(name, email, e.getErrors());
                ctx.render("users/build.jte", Collections.singletonMap("page", page));
            }
        });

        app.get(NamedRoutes.coursesPath(), ctx -> {
            var term = ctx.queryParam("term");
            List<Course> resultCourses;

            if (term == null) {
                resultCourses = CourseRepository.getEntities().stream().toList();
            } else {
                resultCourses = CourseRepository.getEntities().stream()
                        .filter(n -> n.getName().equals(term) ||
                                n.getDescription().contains(term))
                        .toList();
            }

            var page = new CoursesPage(resultCourses, term);
            ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });

        app.get(NamedRoutes.buildCoursePath(), ctx -> {
            var page = new BuildCoursePage();
            ctx.render("courses/build.jte", Collections.singletonMap("page", page));
        });

        app.post(NamedRoutes.coursesPath(), ctx -> {
            try {
                var name = ctx.formParamAsClass("name", String.class)
                        .check(value -> value.length() > 2, "Название курса должно быть длиннее 2 символов")
                        .get();
                var description = ctx.formParamAsClass("description", String.class)
                        .check(value ->  value.length() > 10, "Описание курса должно быть длиннее 10 символов")
                        .get();
                var course = new Course(name, description);
                CourseRepository.save(course);
                ctx.redirect(NamedRoutes.coursesPath());
            } catch (ValidationException e) {
                var name = ctx.formParam("name");
                var description = ctx.formParam("description");
                var page = new BuildCoursePage(name, description, e.getErrors());
                ctx.render("courses/build.jte", Collections.singletonMap("page", page));
            }
        });

        app.get(NamedRoutes.coursePath("{id}"), ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).getOrDefault(0L);
            var course = CourseRepository
                    .getEntities()
                    .stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            var page = new CoursePage(course);

            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        });

        app.start(7070);
    }
}