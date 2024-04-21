package nextstep.cucumber.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.cucumber.AcceptanceContext;
import nextstep.subway.line.create.LineCreatedResponse;
import nextstep.subway.station.StationResponse;
import org.springframework.beans.factory.annotation.Autowired;

import static nextstep.subway.acceptance.fixture.LineFixture.newLine;

public class LineStepDef implements En {

    @Autowired
    private AcceptanceContext context;

    public LineStepDef() {
        Given("지하철 노선을 생성하고", (DataTable table) ->
                table.asMaps().forEach(data -> {
                    ExtractableResponse<Response> response = newLine(
                            data.get("name"),
                            data.get("color"),
                            ((StationResponse) context.store.get(data.get("upStation"))).getId(),
                            ((StationResponse) context.store.get(data.get("downStation"))).getId(),
                            Integer.parseInt(data.get("distance"))
                    );
                    context.store.put(data.get("name"), (new ObjectMapper()).convertValue(response.jsonPath().get(), LineCreatedResponse.class));
                })
        );
    }
}
