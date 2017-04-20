package com.qianwang.parsejson;

import java.util.List;

/**
 * Created by sky on 2017/4/19.
 */

public class Weather {

    private String error;
    private String status;
    private String date;
    private List<CityWeather> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String data) {
        this.date = data;
    }

    public List<CityWeather> getResults() {
        return results;
    }

    public void setResults(List<CityWeather> results) {
        this.results = results;
    }

    public static class CityWeather {

        private String currentCity;
        private List<WeatherData> weather_data;

        public String getCurrentCity() {
            return currentCity;
        }

        public void setCurrentCity(String currentCity) {
            this.currentCity = currentCity;
        }

        public List<WeatherData> getWeather_data() {
            return weather_data;
        }

        public void setWeather_data(List<WeatherData> weather_data) {
            this.weather_data = weather_data;
        }

        public static class WeatherData {

            private String date;
            private String dayPictureUrl;
            private String neightPictureUrl;
            private String weather;
            private String wind;
            private String temperature;

            public String getDate() {
                return date;
            }

            public void setDate(String data) {
                this.date = data;
            }

            public String getDayPictureUrl() {
                return dayPictureUrl;
            }

            public void setDayPictureUrl(String dayPictureUrl) {
                this.dayPictureUrl = dayPictureUrl;
            }

            public String getNeightPictureUrl() {
                return neightPictureUrl;
            }

            public void setNeightPictureUrl(String neightPictureUrl) {
                this.neightPictureUrl = neightPictureUrl;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }
        }
    }
}
