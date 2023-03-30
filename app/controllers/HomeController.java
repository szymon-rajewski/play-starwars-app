package controllers;

import io.vavr.control.Try;
import models.Planet;
import play.http.HttpEntity;
import play.libs.Json;
import play.mvc.*;
import services.StartWarsClient;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import services.UniverseCreator;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final StartWarsClient client;

    @Inject
    public HomeController(StartWarsClient client) {
        this.client = client;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok("Hello!");
    }

    public CompletionStage<Result> firstPlanet() {
        return this.client.getFirstPlanet().thenApply(jsonPlanet -> {
            Planet planet = Json.fromJson(jsonPlanet, Planet.class);
            return ok(planet.toString());
        });
    }

    public CompletionStage<Result> planetById(Long id) {
        return this.client.getPlanetById(id)
                .thenApply(jsonPlanet -> Json.fromJson(jsonPlanet, Planet.class))
                .thenApply(planet -> new UniverseCreator(this.client).createFrom(planet))
                .thenApply(universeFuture -> Try.of(() -> ok(universeFuture.get().toString())))
                .thenApply(tryResult -> tryResult.getOrElseGet(
                        error -> new Result(500, HttpEntity.fromString(error.getMessage(), "utf-8")))
                );
    }
}
