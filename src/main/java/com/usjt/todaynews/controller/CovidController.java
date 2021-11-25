package com.usjt.todaynews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.usjt.todaynews.model.CovidBrasil;
import com.usjt.todaynews.model.CovidEstadual;
import com.usjt.todaynews.service.CovidService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "api/v1/covid")
@Api(value = "Consumir API dados Covid (https://covid19-brazil-api.vercel.app)")
public class CovidController {

	@Autowired
	private CovidService covidService;
	
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Status do Covid no Brasil listado com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado para visualizar este recurso"),
		    @ApiResponse(code = 403, message = "O recurso que você está tentando acessar está inacessível"),
		    @ApiResponse(code = 404, message = "O recurso que você está tentando acessar não existe"),
			@ApiResponse(code = 500, message = "Erro interno no servidor")})
	@GetMapping
	@ApiOperation("Obter status do Covid no Brasil")
	public ResponseEntity<CovidBrasil> obterStatusBrasil() {
		CovidBrasil covidBrasilRq = covidService.statusBrasilAtual();
		return ResponseEntity.status(HttpStatus.OK).body(covidBrasilRq);
	}
	
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Status do Covid no Estado escolhido listado com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado para visualizar este recurso"),
		    @ApiResponse(code = 403, message = "O recurso que você está tentando acessar está inacessível"),
		    @ApiResponse(code = 404, message = "O recurso que você está tentando acessar não existe"),
			@ApiResponse(code = 500, message = "Erro interno no servidor")})
	@GetMapping("/{uf}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Obter status do Covid por Estado")
	public ResponseEntity<CovidEstadual> obterStatusEstado(@PathVariable String uf) {
		CovidEstadual covidEstadualRq = covidService.statusEstadualAtual(uf);
		return ResponseEntity.status(HttpStatus.OK).body(covidEstadualRq);
	}
	
	
}
