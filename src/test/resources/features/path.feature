Feature: 지하철 경로 관련 기능

#  /**
#  * 교대역   --- 2호선, 10 ----    강남역
#  * |                            |
#  * 3호선, 2                   신분당선, 10
#  * |                            |
#  * 남부터미널역  --- 3호선, 3 ---   양재
#  */
  Background: 2호선, 3호선, 신분당선 구간
  Given 지하철 역을 생성하고
    | name   |
    | 교대역    |
    | 강남역    |
    | 양재역    |
    | 남부터미널역 |
  And 지하철 노선을 생성하고
    | name | color  | upStation | downStation | distance |
    | 2호선  | green  | 교대역       | 강남역         | 10       |
    | 3호선  | orange | 교대역       | 남부터미널역      | 2        |
    | 신분당선 | red    | 강남역       | 양재역         | 10       |
  And 지하철 구간을 등록하고
    | lineName | upStation | downStation | distance |
    | 3호선      | 남부터미널역    | 양재역         | 3        |

  Scenario: 지하철역 최단 경로를 조회한다.
    When "교대역" 과 "양재역" 의 경로를 조회하면
    Then 거리가 5인 "교대역, 남부터미널역, 양재역" 경로가 조회된다