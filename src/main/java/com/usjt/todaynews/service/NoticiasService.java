package com.usjt.todaynews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.usjt.todaynews.model.Noticias;

import reactor.core.publisher.Mono;

@Service
public class NoticiasService {

	@Autowired
	private WebClient webClientNews;
	
	public Noticias obterNoticias() {
		Mono<Noticias> monoNoticias = this.webClientNews
				.method(HttpMethod.GET)
				.retrieve()
				.bodyToMono(Noticias.class);
		
		Noticias noticiasRq = monoNoticias.block();
		System.out.println(noticiasRq);
		
		return noticiasRq;
	}
}
