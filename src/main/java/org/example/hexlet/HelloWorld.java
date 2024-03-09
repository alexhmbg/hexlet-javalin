package org.example.hexlet;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.User;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        List<Course> courses = new ArrayList<>();
        var course1 = new Course(101L, "SQL", "Course about SQL");
        var course2 = new Course(102L,"HTTP", "Course about HTTP");
        var course3 = new Course(103L, "DB", "Course about DB");
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        app.get("/users/{name}", ctx -> {
            var name = ctx.pathParam("name");
            var user = new User(100, name);
            var page = new UserPage(user);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/courses", ctx -> {
            var header = "Курсы по программированию";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).getOrDefault(100L);

            Course result = new Course(100L, "Course", "Not found");

            for (var course : courses) {
                if (course.getId().equals(id)) {
                    result = course;
                }
            }

            var page = new CoursePage(result);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        });


        app.start(7070);
    }
}