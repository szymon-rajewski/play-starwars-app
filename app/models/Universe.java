package models;

import java.util.List;
import java.util.stream.Collectors;

public class Universe {

    private final Planet planet;
    private final List<Person> people;

    public Universe(Planet planet, List<Person> people) {
        this.planet = planet;
        this.people = people;
    }

    public Planet getPlanet() {
        return planet;
    }

    public List<Person> getPeople() {
        return people;
    }

    @Override
    public String toString() {
        return "Universe{" +
                "planet=" + planet +
                ", people=" + people.stream().map(Person::getName).collect(Collectors.joining(", ")) +
                '}';
    }
}
