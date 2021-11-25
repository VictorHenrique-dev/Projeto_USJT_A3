package com.usjt.todaynews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.usjt.todaynews.model.CovidBrasil;
import com.usjt.todaynews.model.CovidEstadual;

import reactor.core.publisher.Mono;

@Service
public class CovidService {

	@Autowired
	private WebClient webClientCovid;

	public CovidBrasil statusBrasilAtual() {
		Mono<CovidBrasil> monoCovidBrasil = this.webClientCovid
				.method(HttpMethod.GET)
				.uri("/brazil")
				.retrieve()
				.bodyToMono(CovidBrasil.class);

		CovidBrasil covidBrasilRq = monoCovidBrasil.block();

		System.out.println(covidBrasilRq);

		return covidBrasilRq;
	}
	
	public CovidEstadual statusEstadualAtual(String uf) {
		Mono<CovidEstadual> monoCovidEstadual = this.webClientCovid
				.method(HttpMethod.GET)
				.uri("/brazil/uf/" + uf)
				.retrieve()
				.bodyToMono(CovidEstadual.class);
		
		CovidEstadual covidEstadualRq = monoCovidEstadual.block();
		
		System.out.println(covidEstadualRq);
		
		return covidEstadualRq;
	}
	
}
