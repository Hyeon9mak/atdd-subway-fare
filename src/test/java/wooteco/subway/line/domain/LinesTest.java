package wooteco.subway.line.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wooteco.subway.exception.DuplicateLineNameException;

class LinesTest {

    @Test
    @DisplayName("중복된 이름을 가진 지하철 노선이 있는지 검증")
    void validateDuplicateName() {
        // given
        List<Line> lineList = Arrays.asList(
            new Line("1호선", "bg-blue-100"),
            new Line("2호선", "bg-red-300"),
            new Line("3호선", "bg-orange-500")
        );

        Lines lines = new Lines(lineList);

        Line duplicateNameLine = new Line("1호선", "bg-black-000");

        // when

        // then
        assertThatThrownBy(() -> lines.validateDuplicate(duplicateNameLine))
            .isInstanceOf(DuplicateLineNameException.class);
    }
}