package com.example.rk.quarter2;

import java.time.LocalDate;
import java.time.LocalDateTime;
public class Infraction {
  private String infractionName;
  private int infractionDegree;
  private LocalDateTime infractionTime;

  public Infraction(String name, int degree, LocalDateTime time) {
      this.infractionName=name;
      this.infractionDegree=degree;
      this.infractionTime=time;

  }

  public String toString() {
      return "Offense:"+", Degree:"
              + String.valueOf(infractionDegree)+",Time of Recording:"+
              String.valueOf(infractionTime);
  }


}






