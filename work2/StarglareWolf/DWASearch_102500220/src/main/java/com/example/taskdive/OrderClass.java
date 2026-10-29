package com.example.taskdive;


import java.util.List;

// 排序类
public class OrderClass {
   public static  List<Country> CountryOrder(List<Country> countries) {
        int total = countries.size();
        for (int i = 0; i < total; i++) {
            for (int j = i + 1; j < total; j++) {
                if (countries.get(i).getCountryName().compareTo(countries.get(j).getCountryName()) > 0) {
                    Country temp = countries.get(i);
                    countries.set(i, countries.get(j));
                    countries.set(j, temp);
                }
            }
        }
        return countries;
    }

    public static List<Athlete> AthleteOrder(List<Athlete> athletes)
    {
        int total = athletes.size();
        for (int i = 0; i < total; i++) {
            for (int j = i + 1; j < total; j++) {
                if (athletes.get(i).getPreferredLastName().compareTo(athletes.get(j).getPreferredLastName()) > 0) {
                    Athlete temp = athletes.get(i);
                    athletes.set(i, athletes.get(j));
                    athletes.set(j, temp);
                }
                if (athletes.get(i).getPreferredLastName().compareTo(athletes.get(j).getPreferredLastName()) == 0) {
                    if(athletes.get(i).getPreferredFirstName().compareTo(athletes.get(j).getPreferredFirstName()) > 0)
                    {
                        Athlete temp = athletes.get(i);
                        athletes.set(i, athletes.get(j));
                        athletes.set(j, temp);
                    }
                }
            }
        }
        return athletes;
    }
}
