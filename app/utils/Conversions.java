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

  public static String degreeToCompass(double degree) {
    if (degree > 0 && degree <= 11.25) {
      return "N";
    } else if (degree > 11.25 && degree <= 33.75) {
      return "NNE";
    } else if (degree > 33.75 && degree <= 56.25) {
      return "NE";
    } else if (degree > 56.25 && degree <= 78.75) {
      return "ENE";
    } else if (degree > 78.75 && degree <= 101.25) {
      return "E";
    } else if (degree > 101.25 && degree <= 123.75) {
      return "ESE";
    } else if (degree > 123.75 && degree <= 146.25) {
      return "SE";
    } else if (degree > 146.25 && degree <= 168.75) {
      return "SSE";
    } else if (degree > 168.75 && degree <= 191.25) {
      return "S";
    } else if (degree > 191.25 && degree <= 213.75) {
      return "SSW";
    } else if (degree > 213.75 && degree <= 236.25) {
      return "SW";
    } else if (degree > 236.25 && degree <= 258.75) {
      return "WSW";
    } else if (degree > 258.75 && degree <= 281.25) {
      return "W";
    } else if (degree > 281.25 && degree <= 303.75) {
      return "WNW";
    } else if (degree > 303.75 && degree <= 326.25) {
      return "NW";
    } else if (degree > 326.25 && degree <= 348.75) {
      return "NNW";
    } else if (degree > 348.75 && degree <= 360.00) {
      return "N";
    } else {
      return "Entry not in range";
    }
  }

  public static double calculateWindChill(double temperature, double windSpeed) {
    double calc = 13.12 + (0.6215 * temperature)
        - 11.37 * (Math.pow(windSpeed, 0.16))
        + 0.3965 * temperature * (Math.pow(windSpeed, 0.16));

    return Math.round(calc * 100.0) / 100.0;
  }
}
