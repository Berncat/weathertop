package controllers;

import models.Member;
import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import utils.Analytics;
import utils.Conversions;

import java.util.List;

public class StationCtrl extends Controller {

  public static void index(Long id) {
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;

    //FlagBasedLoop to check if station id is in the memebers station list
    boolean test = false;
    for (Station station : stations) {
      if (station.id == id) {
        test = true;
      }
    }
    if (test) {
      Station station = Station.findById(id);
      if (station.readings.size() > 0) {
        station.weatherConditions = Conversions.weatherCodeToText(station.readings.get(station.readings.size() - 1).code);
        station.weatherIcon = Conversions.weatherCodeToIcon(station.readings.get(station.readings.size() - 1).code);
        station.minTemperature = Analytics.minTemperature(station.readings).temperature;
        station.maxTemperature = Analytics.maxTemperature(station.readings).temperature;
        station.minWindSpeed = Analytics.minWindSpeed(station.readings).windSpeed;
        station.maxWindSpeed = Analytics.maxWindSpeed(station.readings).windSpeed;
        station.minPressure = Analytics.minPressure(station.readings).pressure;
        station.maxPressure = Analytics.maxPressure(station.readings).pressure;
        station.temperatureTrend = Analytics.temperatureTrend(station.readings);
        station.windSpeedTrend = Analytics.windSpeedTrend(station.readings);
        station.pressureTrend = Analytics.pressureTrend(station.readings);
      }
      render("station.html", station);
    } else {
      redirect("/dashboard");
    }
  }

  public static void deleteReading(Long id, Long readingid) {
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);
    Logger.info("Removing " + reading.timeStamp);
    station.readings.remove(reading);
    station.save();
    reading.delete();
    redirect("/station/" + id);
    render("station.html", station);
  }

  public static void addReading(Long id, int code, double temperature, double windSpeed, int pressure, double windDirection) {
    Reading reading = new Reading(code, temperature, windSpeed, pressure, windDirection);
    Station station = Station.findById(id);
    station.readings.add(reading);
    station.save();
    redirect("/station/" + id);
  }
}
