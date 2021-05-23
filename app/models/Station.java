package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Station extends Model {

  public String name;
  public double latitude;
  public double longitude;
  public String weatherConditions;
  public String weatherIcon;
  public double minTemperature;
  public double maxTemperature;
  public double minWindSpeed;
  public double maxWindSpeed;
  public int minPressure;
  public int maxPressure;
  public String temperatureTrend;
  public String windSpeedTrend;
  public String pressureTrend;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();

  public Station(String name, double latitude, double longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /*I found the subList method in the java api, I thought I would use this method for displaying the latest reading
  as you if you wished to evolve the app further you could maybe ask the user how many of the last readings
  they would like to see.
   */
  public List<Reading> latestReadings() {
    List<Reading> latestReadings = null;
    if (readings.size() > 0) {
      latestReadings = readings.subList(readings.size() - 1, readings.size());
    }
    return latestReadings;
  }

  public String getName ()    {
    return name;
  }
}