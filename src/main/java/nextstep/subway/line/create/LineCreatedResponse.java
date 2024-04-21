package nextstep.subway.line.create;

import nextstep.subway.line.Line;

import java.util.List;
import java.util.stream.Collectors;

public class LineCreatedResponse {

    private Long id;
    private String name;
    private String color;
    private List<LineCreatedStationResponse> stations;

    public LineCreatedResponse() {
    }

    public LineCreatedResponse(Long id, String name, String color, List<LineCreatedStationResponse> stations) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.stations = stations;
    }

    public static LineCreatedResponse from(Line line) {
        return new LineCreatedResponse(
                line.getId(),
                line.getName(),
                line.getColor(),
                line.getAllStations().stream()
                        .map(LineCreatedStationResponse::from)
                        .collect(Collectors.toList())
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<LineCreatedStationResponse> getStations() {
        return stations;
    }
}
