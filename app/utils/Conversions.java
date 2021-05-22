package utils;

import java.util.HashMap;

public class Conversions {

  public static double celsiusToFahrenheit(double temperature) {
    return temperature * 9 / 5 + 32;
  }

  public static int kmToBeaufort(double windSpeed) {
    if (windSpeed <= 1) {
      return 0;
    } else if (windSpeed > 1 && windSpeed <= 5) {
      return 1;
    } else if (windSpeed >= 6 && windSpeed <= 11) {
      return 2;
    } else if (windSpeed >= 12 && windSpeed <= 19) {
      return 3;
    } else if (windSpeed >= 20 && windSpeed <= 28) {
      return 4;
    } else if (windSpeed >= 29 && windSpeed <= 38) {
      return 5;
    } else if (windSpeed >= 39 && windSpeed <= 49) {
      return 6;
    } else if (windSpeed >= 50 && windSpeed <= 61) {
      return 7;
    } else if (windSpeed >= 62 && windSpeed <= 74) {
      return 8;
    } else if (windSpeed >= 75 && windSpeed <= 88) {
      return 9;
    } else if (windSpeed >= 89 && windSpeed <= 102) {
      return 10;
    } else if (windSpeed >= 103 && windSpeed <= 117) {
      return 11;
    } else {
      return 11;
    }
  }

  public static String weatherCodeToText(int weatherCode) {

    HashMap<Integer, String> weatherCodeMap = new HashMap<Integer, String>();

    weatherCodeMap.put(100, "Clear");
    weatherCodeMap.put(200, "Partial clouds");
    weatherCodeMap.put(300, "Cloudy");
    weatherCodeMap.put(400, "Light Showers");
    weatherCodeMap.put(500, "Heavy Showers");
    weatherCodeMap.put(600, "Rain");
    weatherCodeMap.put(700, "Snow");
    weatherCodeMap.put(800, "Thunder");

    String response = weatherCodeMap.get(weatherCode);

    return response;
  }

}
