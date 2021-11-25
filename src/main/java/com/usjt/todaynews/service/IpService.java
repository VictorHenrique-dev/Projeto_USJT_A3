package com.usjt.todaynews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.usjt.todaynews.model.Ip;

import reactor.core.publisher.Mono;

@Service
public class IpService {

	@Autowired
	private WebClient webClientIp;
	
	public Ip obterIp() {
		Mono<Ip> monoIP = this.webClientIp
				.method(HttpMethod.GET)
				.retrieve()
				.bodyToMono(Ip.class);
		
		Ip ipRq = monoIP.block();
		System.out.println(ipRq);
		
		return ipRq;
	}
}
