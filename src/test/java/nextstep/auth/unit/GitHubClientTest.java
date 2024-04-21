package nextstep.auth.unit;

import nextstep.auth.application.GitHubClient;
import nextstep.auth.application.dto.GitHubProfileResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GitHubClientTest {

    @Autowired
    private GitHubClient gitHubClient;

    @DisplayName("GitHub 토큰을 요청한다.")
    @Test
    void requestGitHubAccessToken() {
        // given
        String code = "test code";

        // when
        String accessToken = gitHubClient.requestGitHubAccessToken(code);

        // then
        assertThat(accessToken).isEqualTo("test code github-access-token");
    }

    @DisplayName("GitHub 프로필을 요청한다.")
    @Test
    void requestGithubProfile() {
        // given
        String accessToken = "hyeon9mak github-access-token";

        // when
        GitHubProfileResponse response = gitHubClient.requestGithubProfile(accessToken);

        // then
        assertThat(response.getEmail()).isEqualTo("hyeon9mak@gmail.com");
    }
}
