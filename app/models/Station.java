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
  public String weatherConditions;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();

  public Station(String name) {
    this.name = name;
  }

  /*I found the subList method in the java api, I though I would use this method for latest reading
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
}