package weather.api.endpoints;

public enum WeatherApiEndpoints {

    GET_CURRENT_WEATHER("/current.json");

    WeatherApiEndpoints(String _path) {
        path = _path;
    }

    private String path;

    public String uri() {
        return "http://api.weatherapi.com/v1" + path;
    }
}
