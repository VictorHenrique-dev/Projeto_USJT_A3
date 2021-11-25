package com.usjt.todaynews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.usjt.todaynews.model.TodayNews;
import com.usjt.todaynews.service.TodayNewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "api/v1/today")
@Api("API que reúne as informações coletadas pelas outras API")
public class TodayNewsController {
	
	@Autowired
	private TodayNewsService todayNewsService;
	
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Reporte diário listado com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado para visualizar este recurso"),
		    @ApiResponse(code = 403, message = "O recurso que você está tentando acessar está inacessível"),
		    @ApiResponse(code = 404, message = "O recurso que você está tentando acessar não existe"),
			@ApiResponse(code = 500, message = "Erro interno no servidor")})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Obter Reporte Diário")
	public ResponseEntity<TodayNews> obterReporteDiario(){
		TodayNews todayNews = todayNewsService.obterTudo();
		return ResponseEntity.status(HttpStatus.OK).body(todayNews);
	}

}
