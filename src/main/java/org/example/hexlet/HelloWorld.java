package org.example.hexlet;

import io.javalin.Javalin;

import java.util.Collections;

import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.UsersController;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            var page = new MainPage(ctx.sessionAttribute("currentUser"));
            ctx.render("index.jte", Collections.singletonMap("page", page));
        });

        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.get(NamedRoutes.userPath("{id}"), UsersController::show);
        app.post(NamedRoutes.usersPath(), UsersController::create);
//      app.get("/users/{id}/edit", UsersController::edit);
//      app.patch("/users/{id}", UsersController::update);
//      app.delete("/users/{id}", UsersController::destroy);

        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);
//      app.get("/courses/{id}/edit", CoursesController::edit);
//      app.patch("/courses/{id}", CoursesController::update);
//      app.delete("/courses/{id}", CoursesController::destroy);

        // Отображение формы логина
        app.get("/sessions/build", SessionsController::build);
        // Процесс логина
        app.post("/sessions", SessionsController::create);
        // Процесс выхода из аккаунта
        app.delete("/sessions", SessionsController::destroy);

        app.start(7070);
    }
}