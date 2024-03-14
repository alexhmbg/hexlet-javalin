package org.example.hexlet.controller;

import java.util.Collections;
import java.util.List;

import io.javalin.validation.ValidationException;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class CoursesController {
    public static void index(Context ctx) {
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
    }
    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new CoursePage(course);

        ctx.render("courses/show.jte", Collections.singletonMap("page", page));
    }
    public static void build(Context ctx) {
        var page = new BuildCoursePage();
        ctx.render("courses/build.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) {
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
    }

    public static void edit(Context ctx) {

    }

    public static void update(Context ctx) {

    }

    public static void destroy(Context ctx) {

    }
}
