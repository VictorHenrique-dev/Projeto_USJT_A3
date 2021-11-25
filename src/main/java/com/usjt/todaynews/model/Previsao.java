package com.usjt.todaynews.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Previsao {

	private String by;
	private Boolean valid_key;
	private PrevisaoResultados results;
	private Boolean from_cache;
	
	@Data
	public static class PrevisaoResultados {

		private Integer temp;
		private String date;
		private String time;
		private String description;
		private String currently;
		private String city;
		private Integer humidity;
		private String wind_speedy;
		private String sunrise;
		private String sunset;
		private List<ProximosDias> forecast;
	}
	
	@Data
	public static class ProximosDias {
		private String date;
		private String weekday;
		private Integer max;
		private Integer min;
		private String description;
		private String condition;
	}

}
