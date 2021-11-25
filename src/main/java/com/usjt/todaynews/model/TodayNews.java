package com.usjt.todaynews.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodayNews {
	private Hoje hoje;
	private List<PrevisaoTempo> proximosDias;
	private Covid covid;
	private List<NoticiasHoje> principaisNoticias;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Hoje {
		private String data;
		private String tempo;
		private String cidade;
		private Integer temperatura;
		private Integer umidadeAr;
		private String velocidadeVento;
		private String horarioAmanhecer;
		private String horarioAnoitecer;
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PrevisaoTempo {
		private String data;
		private Integer temperaturaMax;
		private Integer temperaturaMin;
		private String descricao;
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Covid {
		private Integer casosBrasil;
		private Integer mortesBrasil;
		private Integer casosEstado;
		private Integer mortesEstado;
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class NoticiasHoje {
		private String fonte;
		private String titulo;
		private String url;
		private String dataPublicacao;
	}
}
