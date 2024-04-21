package nextstep.subway.path;

import nextstep.subway.station.Station;

import java.util.List;

public class PathFoundResponse {

    private List<PathFoundStationResponse> stations;
    private int distance;

    public PathFoundResponse() {
    }

    public PathFoundResponse(List<PathFoundStationResponse> stations, int distance) {
        this.stations = stations;
        this.distance = distance;
    }

    public static PathFoundResponse of(List<Station> stations, int distance) {
        return new PathFoundResponse(PathFoundStationResponse.of(stations), distance);
    }

    public List<PathFoundStationResponse> getStations() {
        return stations;
    }

    public int getDistance() {
        return distance;
    }
}
