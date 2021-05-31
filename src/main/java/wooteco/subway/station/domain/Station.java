package wooteco.subway.station.domain;

import java.util.Objects;

public class Station {

    private Id id;
    private Name name;

    public Station() {
    }

    public Station(String name) {
        this(null, new Name(name));
    }

    public Station(Long id, String name) {
        this(new Id(id), new Name(name));
    }

    public Station(Id id, Name name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Station station = (Station) o;
        return name.equals(station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
