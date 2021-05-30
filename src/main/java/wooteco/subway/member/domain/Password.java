package wooteco.subway.member.domain;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import wooteco.subway.exception.InvalidPasswordException;
import wooteco.subway.exception.MismatchIdPasswordException;

public class Password {

    private static final String BLANK = " ";

    private final String value;

    public Password(String value) {
        this.value = value;
        validateNull(this.value);
        validateBlank(this.value);
    }

    private void validateBlank(String value) {
        if (value.contains(BLANK)) {
            throw new InvalidPasswordException();
        }
    }

    private void validateNull(String value) {
        if (Objects.isNull(value)) {
            throw new InvalidPasswordException();
        }
    }

    public String getValue() {
        return value;
    }

    public void check(String password) {
        if (StringUtils.equals(value, password)) {
            return;
        }

        throw new MismatchIdPasswordException();
    }
}
