package org.example.weatherforecast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("org.example.weatherforecast.mapper")
@SpringBootApplication
public class WeatherForecastApplication {

	public static void main(String[] args) {

		System.out.println("Starting Weather Forecast Application");
		SpringApplication.run(WeatherForecastApplication.class, args);
	}

}
