package pl.maciejszymanek.currentweather;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

@Rest(rootUrl = "http://api.openweathermap.org/data/2.5", converters = { GsonHttpMessageConverter.class })
public interface WeatherService {
    @Get("/weather?lat={lat}&lon={lon}")
    MainModel getWeather(double lat, double lon);
}
