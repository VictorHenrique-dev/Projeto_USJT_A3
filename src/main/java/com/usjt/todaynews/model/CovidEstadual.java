package com.usjt.todaynews.model;

import lombok.Data;

@Data
public class CovidEstadual {
	private String uf;
	private String state;
	private Integer cases;
	private Integer deaths;
	private Integer suspects;
	private Integer refuses;	
	private String datetime;	
}
