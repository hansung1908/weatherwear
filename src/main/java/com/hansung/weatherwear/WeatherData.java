package com.hansung.weatherwear;

public record WeatherData(
        double latitude,
        double longitude,
        CurrentWeather current_weather
) {
    public record CurrentWeather(
            double temperature,  // 현재 기온
            double windspeed,    // 풍속
            int weathercode     // 날씨 코드 (맑음, 흐림 등)
    ) {}
}
