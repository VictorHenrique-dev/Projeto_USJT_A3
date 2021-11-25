package com.usjt.todaynews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.usjt.todaynews.model.Previsao;

import reactor.core.publisher.Mono;

@Service
public class PrevisaoService {

	@Autowired
	private WebClient webClientPrevisao;
	
	@Autowired
	private IpService ipService;
	
	public Previsao obterPrevisoes() {
		Mono<Previsao> monoPrevisaoDTO = this.webClientPrevisao
				.method(HttpMethod.GET)
				.uri("weather?key=99d9c7af&city_name="+ ipService.obterIp().getCity())
				.retrieve()
				.bodyToMono(Previsao.class);
		
		Previsao previsaoRq = monoPrevisaoDTO.block();
		System.out.println(previsaoRq);
		
		return previsaoRq;
	}
}
