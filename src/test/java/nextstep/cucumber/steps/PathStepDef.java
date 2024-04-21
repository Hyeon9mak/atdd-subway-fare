package nextstep.cucumber.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.cucumber.AcceptanceContext;
import nextstep.subway.line.addsection.LineAddedSectionResponse;
import nextstep.subway.line.create.LineCreatedResponse;
import nextstep.subway.path.PathFoundResponse;
import nextstep.subway.path.PathFoundStationResponse;
import nextstep.subway.station.StationResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static nextstep.subway.acceptance.fixture.LineFixture.addSection;
import static nextstep.subway.acceptance.fixture.PathFixture.findPath;
import static org.assertj.core.api.Assertions.assertThat;

public class PathStepDef implements En {

    @Autowired
    private AcceptanceContext context;

    public PathStepDef() {
        Given("지하철 구간을 등록하고", (DataTable table) ->
                table.asMaps().forEach(data -> {
                    ExtractableResponse<Response> response = addSection(
                            ((LineCreatedResponse) context.store.get(data.get("lineName"))).getId(),
                            ((StationResponse) context.store.get(data.get("upStation"))).getId(),
                            ((StationResponse) context.store.get(data.get("downStation"))).getId(),
                            Integer.parseInt(data.get("distance"))
                    );
                    context.store.put(data.get("name"), (new ObjectMapper()).convertValue(response.jsonPath().get(), LineAddedSectionResponse.class));
                })
        );


        When("{string} 과 {string} 의 경로를 조회하면", (String source, String target) -> {
            context.response = findPath(
                    (((StationResponse) context.store.get(source)).getId()),
                    (((StationResponse) context.store.get(target)).getId())
            );
        });


        Then("거리가 {int} 인 {string} 경로가 조회된다", (Integer distance, String stationNames) -> {
            String[] names = stationNames.split(", ");
            Long[] ids = Arrays.stream(names)
                    .map(name -> ((StationResponse) context.store.get(name)).getId())
                    .toArray(Long[]::new);

            assertThat(context.response.jsonPath().getList("stations.id", Long.class)).containsExactly(ids);
            assertThat(context.response.jsonPath().getInt("distance")).isEqualTo(distance);
        });
    }
}
