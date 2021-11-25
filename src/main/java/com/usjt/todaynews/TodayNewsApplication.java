package com.usjt.todaynews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TodayNewsApplication {

	
	@Bean
	public WebClient webClientIp(WebClient.Builder builder) {
		return builder
			.baseUrl("https://ipinfo.io/json?token=0f2233018c8472")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}
	
	@Bean
	public WebClient webClientPrevisao(WebClient.Builder builder) {
		return builder
			.baseUrl("https://api.hgbrasil.com/")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}
	
	@Bean
	public WebClient webClientCovid(WebClient.Builder builder) {
		return builder
			.baseUrl("https://covid19-brazil-api.vercel.app/api/report/v1/")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}
	
	@Bean
	public WebClient webClientNews(WebClient.Builder builder) {
		return builder
			.baseUrl("https://newsapi.org/v2/top-headlines?country=br&apiKey=d1fbc54e0f0f49b989884c659d07a8b8")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}
	

	public static void main(String[] args) {
		SpringApplication.run(TodayNewsApplication.class, args);
	}

}
