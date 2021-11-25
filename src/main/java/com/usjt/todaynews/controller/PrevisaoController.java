package com.usjt.todaynews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.usjt.todaynews.model.Previsao;
import com.usjt.todaynews.service.PrevisaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "api/v1/previsao")
@Api("Consumir API de Previsão do Tempo (https://newsapi.org)")
public class PrevisaoController {

	@Autowired
	private PrevisaoService previsaoService;
	
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Previsão do tempo listado com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado para visualizar este recurso"),
		    @ApiResponse(code = 403, message = "O recurso que você está tentando acessar está inacessível"),
		    @ApiResponse(code = 404, message = "O recurso que você está tentando acessar não existe"),
			@ApiResponse(code = 500, message = "Erro interno no servidor")})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Obter previsão do tempo")
	public ResponseEntity<Previsao> obterPrevisaoIp() {
		Previsao previsaoRq = previsaoService.obterPrevisoes();
		return ResponseEntity.status(HttpStatus.OK).body(previsaoRq);
	}
}
