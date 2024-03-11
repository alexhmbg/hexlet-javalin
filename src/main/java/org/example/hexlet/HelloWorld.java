package org.example.hexlet;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.example.hexlet.dto.courses.CoursePage;
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

        app.get("/users", ctx -> {
            var page = new UsersPage(UserRepository.getEntities());
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });

        app.post("/users", ctx -> {
            var name = ctx.formParam("name").trim();
            var email = ctx.formParam("email").trim().toLowerCase();
            var password = ctx.formParam("password");
            var passwordConfirmation = ctx.formParam("passwordConfirmation");

            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.redirect("/users");
        });

        app.get("/courses", ctx -> {
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

        app.get("/courses/build", ctx -> {
            ctx.render("courses/build.jte");
        });

        app.post("/courses", ctx -> {
            var name = ctx.formParam("name");
            var description = ctx.formParam("description");

            var course = new Course(name, description);
            CourseRepository.save(course);
            ctx.redirect("/courses");
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).getOrDefault(100L);
            var course = CourseRepository.getEntities().stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
            var page = new CoursePage(course);

            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        });

        app.start(7070);
    }
}