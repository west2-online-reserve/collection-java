package org.example.pojo;

import java.sql.Time;
import java.util.Date;

public class Weather {
     int id;
     Date fxDate;
     Integer tempMax;
     Integer tempMin;
     String textDay;

     public Weather(){

     }

     public Weather(int id, Date fxDate, Integer tempMax, Integer tempMin, String textDay) {
          this.id = id;
          this.fxDate = fxDate;
          this.tempMax = tempMax;
          this.tempMin = tempMin;
          this.textDay = textDay;
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public Date getFxDate() {
          return fxDate;
     }

     public void setFxDate(Date fxDate) {
          this.fxDate = fxDate;
     }

     public Integer getTempMax() {
          return tempMax;
     }

     public void setTempMax(Integer tempMax) {
          this.tempMax = tempMax;
     }

     public Integer getTempMin() {
          return tempMin;
     }

     public void setTempMin(Integer tempMin) {
          this.tempMin = tempMin;
     }

     public String getTextDay() {
          return textDay;
     }

     public void setTextDay(String textDay) {
          this.textDay = textDay;
     }

     @Override
     public String toString() {
          return "Weather{" +
                  "id=" + id +
                  ", fxDate=" + fxDate +
                  ", tempMax=" + tempMax +
                  ", tempMin=" + tempMin +
                  ", textDay='" + textDay + '\'' +
                  '}';
     }
}
