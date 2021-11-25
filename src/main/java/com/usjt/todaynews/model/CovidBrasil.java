package com.usjt.todaynews.model;

import lombok.Data;

@Data
public class CovidBrasil {
	private CovidBrasilRequest data;
	
	@Data
	public static class CovidBrasilRequest {
		private String country;
		private Integer confirmed;
		private Integer deaths;
	}
}
