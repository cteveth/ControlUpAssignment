package weather.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import weather.api.endpoints.WeatherApiEndpoints;
import io.restassured.response.Response;
import weather.api.entities.CurrentWeather;

public class WeatherApiUtil {

    private final String api_key = "2a993793b4e94df2bf3180405240105";
    private final static Logger logger = LogManager.getLogger(WeatherApiUtil.class);

    /**
     * This method create the start of the API request with the Headers
     *
     * @return {@link RequestSpecification}
     */
    public RequestSpecification weatherApiRequest() {
        return RestAssured.given().log().ifValidationFails(LogDetail.ALL)
                .urlEncodingEnabled(false)
                .queryParam("key", api_key)
                .contentType(ContentType.JSON);
    }

    public CurrentWeather getCurrentWeather(String zip_code) {
        try {
            Response response = weatherApiRequest().
                    queryParam("q=" + zip_code).
                    get(WeatherApiEndpoints.GET_CURRENT_WEATHER.uri());
            response.then().assertThat().statusCode(HttpStatus.SC_OK);
            return response.as(CurrentWeather.class);
        } catch (Exception e) {
            logger.error("Failed to get current weather for zip code [" + zip_code + "]", e);
            throw new RuntimeException(e);
        }
    }
}