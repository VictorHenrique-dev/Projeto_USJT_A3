package com.usjt.todaynews.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.usjt.todaynews.model.CovidBrasil;
import com.usjt.todaynews.model.CovidEstadual;
import com.usjt.todaynews.model.Noticias;
import com.usjt.todaynews.model.Previsao;
import com.usjt.todaynews.model.Previsao.ProximosDias;
import com.usjt.todaynews.model.TodayNews;
import com.usjt.todaynews.model.TodayNews.Covid;
import com.usjt.todaynews.model.TodayNews.Hoje;
import com.usjt.todaynews.model.TodayNews.NoticiasHoje;
import com.usjt.todaynews.model.TodayNews.PrevisaoTempo;

import reactor.core.publisher.Mono;

@Service
public class TodayNewsService {
	
	@Autowired
	private PrevisaoService previsaoService;
	
	@Autowired
	private CovidService covidService;
	
	@Autowired
	private NoticiasService noticiasService;
	
	@Autowired
	private WebClient webClientCovid;

	public TodayNews obterTudo() {
		Previsao previsaoRq = previsaoService.obterPrevisoes();
		CovidBrasil covidBrasilRq = covidService.statusBrasilAtual();
		CovidEstadual covidEstadualRq = covidService.statusEstadualAtual(previsaoRq.getResults().getCity().substring(previsaoRq.getResults().getCity().length() - 2));
		Noticias noticiasRq = noticiasService.obterNoticias();
		
		List<PrevisaoTempo> previsaoDoTempo = new ArrayList<PrevisaoTempo>();		
		for (ProximosDias dia : previsaoRq.getResults().getForecast()) {
			PrevisaoTempo previsaoDia =	new PrevisaoTempo(
					dia.getWeekday() + " - " + dia.getDate(),
					dia.getMax(),
					dia.getMin(),
					dia.getDescription());
			previsaoDoTempo.add(previsaoDia);
		}
		
		List<NoticiasHoje> noticias = new ArrayList<NoticiasHoje>();
		for (Noticias.Artigos artigo : noticiasRq.getArticles()) {
			NoticiasHoje noticia = new NoticiasHoje(
					artigo.getSource().getName(),
					artigo.getTitle(),
					artigo.getUrl(),
					artigo.getPublishedAt());
			noticias.add(noticia);
		}
		
		
		TodayNews todayNews = new TodayNews(
				new Hoje(
						previsaoRq.getResults().getDate() + " " + previsaoRq.getResults().getTime(),
						previsaoRq.getResults().getDescription() + " - " + previsaoRq.getResults().getCurrently(),
						previsaoRq.getResults().getCity(),
						previsaoRq.getResults().getTemp(),
						previsaoRq.getResults().getHumidity(),
						previsaoRq.getResults().getWind_speedy(),
						previsaoRq.getResults().getSunrise(),
						previsaoRq.getResults().getSunset()
						),
				previsaoDoTempo,
				new Covid(
						covidBrasilRq.getData().getConfirmed(),
						covidBrasilRq.getData().getDeaths(),
						covidEstadualRq.getCases(),
						covidEstadualRq.getDeaths()
						),
				noticias
				);
		
		return todayNews;
	}
	
	
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
