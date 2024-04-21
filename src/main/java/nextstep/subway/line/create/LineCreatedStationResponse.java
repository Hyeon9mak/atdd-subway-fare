package nextstep.subway.line.create;

import nextstep.subway.station.Station;

public class LineCreatedStationResponse {

    private Long id;
    private String name;

    public LineCreatedStationResponse() {
    }

    public LineCreatedStationResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static LineCreatedStationResponse from(Station station) {
        return new LineCreatedStationResponse(station.getId(), station.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
