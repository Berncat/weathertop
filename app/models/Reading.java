package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
import utils.Conversions;

@Entity
public class Reading extends Model
{
  public int code;
  public double temperature;
  public double windSpeed;
  public int pressure;
  
  public Reading(int code, double temperature, double windSpeed, int pressure)
  {
    this.windSpeed = windSpeed;
    this.code = code;
    this.temperature = temperature;
    this.pressure = pressure;
  }

  public double fahrenheit () {
    return Conversions.celsiusToFahrenheit(temperature);
  }

  public int beaufort () { return Conversions.kmToBeaufort(windSpeed);}

  public String weatherConditions ()  { return Conversions.weatherCodeToText(code); }

}
