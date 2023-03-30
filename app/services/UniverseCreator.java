package services;

import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import models.Person;
import models.Planet;
import models.Universe;
import play.libs.Json;

public class UniverseCreator {
    private final StartWarsClient client;

    public UniverseCreator(StartWarsClient client) {
        this.client = client;
    }

    public Future<Universe> createFrom(Planet planet) {
        final List<Future<Person>> futureResidents = List.ofAll(planet.getResidents())
                .map(url -> this.client
                        .getResidentByUrl(url)
                        .thenApply(resident -> Json.fromJson(resident, Person.class))
                        .toCompletableFuture()
                )
                .map(Future::fromCompletableFuture);

        return Future.sequence(futureResidents).map(people -> new Universe(planet, people.asJava()));
    }
}
