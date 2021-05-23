package controllers;

import models.Station;
import models.Member;
import play.Logger;
import play.mvc.Controller;
import utils.Analytics;
import utils.Conversions;

import java.util.Comparator;
import java.util.List;

public class Dashboard extends Controller {

  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    //Sort stations alphabetically
    stations.sort(Comparator.comparing(Station::getName));

    for (Station station : stations) {
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
    }
    render("dashboard.html", stations);
  }

  public static void deleteStation(Long id) {
    Logger.info("Deleting a Playlist");
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect("/dashboard");
  }

  public static void addStation(String name, double latitude, double longitude) {
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name, latitude, longitude);
    member.stations.add(station);
    member.save();
    Logger.info("Adding station: " + name);
    redirect("/dashboard");
  }
}
