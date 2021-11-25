package com.usjt.todaynews.model;

import java.util.List;

import lombok.Data;

@Data
public class Noticias {
	private List<Artigos> articles;

	@Data
	public static class Artigos {
		private Fonte source;
		private String author;
		private String title;
		private String description;
		private String url;
		private String publishedAt;
	}
	
	@Data
	public static class Fonte {
		private String name;
	}
}
