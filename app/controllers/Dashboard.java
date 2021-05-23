package controllers;

import models.Station;
import play.Logger;
import play.mvc.Controller;
import utils.Analytics;
import utils.Conversions;

import java.util.List;

public class Dashboard extends Controller
{
  public static void index() 
  {
    Logger.info("Rendering Dasboard");
    List<Station> stations = Station.findAll();

    for (Station station : stations) {
      if (station.readings.size() > 0) {
        station.weatherConditions = Conversions.weatherCodeToText(station.readings.get(station.readings.size()-1).code);
        station.weatherIcon = Conversions.weatherCodeToIcon(station.readings.get(station.readings.size()-1).code);
        station.minTemperature = Analytics.minTemperature(station.readings).temperature;
        station.maxTemperature = Analytics.maxTemperature(station.readings).temperature;
        station.minWindSpeed = Analytics.minWindSpeed(station.readings).windSpeed;
        station.maxWindSpeed = Analytics.maxWindSpeed(station.readings).windSpeed;
        station.minPressure = Analytics.minPressure(station.readings).pressure;
        station.maxPressure = Analytics.maxPressure(station.readings).pressure;
      }
    }
    //Member member = Accounts.getLoggedInMember();
    //List<Playlist> playlists = member.playlists;
    render ("dashboard.html", stations);
  }

  /*
  public static void deletePlaylist (Long id)
  {
    Logger.info("Deleting a Playlist");
    Member member = Accounts.getLoggedInMember();
    Playlist playlist = Playlist.findById(id);
    member.playlists.remove(playlist);
    member.save();
    playlist.delete();
    redirect ("/dashboard");
  }
  */

  public static void addStation (String name, double latitude, double longitude)
  {
    //Member member = Accounts.getLoggedInMember();
    Station station = new Station (name, latitude, longitude);
    Logger.info("Adding station: " + name);
    station.save();
    //member.playlists.add(playlist);
    //member.save();
    redirect ("/dashboard");
  }

}
