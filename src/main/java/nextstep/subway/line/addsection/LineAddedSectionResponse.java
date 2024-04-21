package nextstep.subway.line.addsection;

import java.util.List;

public class LineAddedSectionResponse {

    private Long id;
    private String name;
    private String color;
    private List<LineAddedSectionStationResponse> stations;

    public LineAddedSectionResponse() {
    }

    public LineAddedSectionResponse(Long id, String name, String color, List<LineAddedSectionStationResponse> stations) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.stations = stations;
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

    public List<LineAddedSectionStationResponse> getStations() {
        return stations;
    }
}
