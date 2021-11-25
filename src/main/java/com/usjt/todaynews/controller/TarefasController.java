package com.usjt.todaynews.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.usjt.todaynews.model.Tarefas;
import com.usjt.todaynews.repository.TarefasRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "api/v1/tarefas")
@Api("Consumir API de Previsão do Tempo (https://newsapi.org)")
public class TarefasController {

	@Autowired
	private TarefasRepository tarefasRepository;

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Tarefas cadastradas listadas com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado para visualizar este recurso"),
			@ApiResponse(code = 403, message = "O recurso que você está tentando acessar está inacessível"),
			@ApiResponse(code = 404, message = "O recurso que você está tentando acessar não existe"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Obter listagem de tarefas cadastradas")
	public ResponseEntity<List<Tarefas>> todasTarefas() {
		List<Tarefas> listaTarefas = tarefasRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listaTarefas);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Status do Covid no Estado escolhido listado com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado para visualizar este recurso"),
			@ApiResponse(code = 403, message = "O recurso que você está tentando acessar está inacessível"),
			@ApiResponse(code = 404, message = "O recurso que você está tentando acessar não existe"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Obter tarefa por Id")
	public ResponseEntity<Optional<Tarefas>> obterTarefaId(@ApiParam("Id da tarefa a ser retornada. Não pode ser vazio") @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(tarefasRepository.findById(id));
	}

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Tarefa criada com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado para visualizar este recurso"),
			@ApiResponse(code = 403, message = "O recurso que você está tentando acessar está inacessível"),
			@ApiResponse(code = 404, message = "O recurso que você está tentando acessar não existe"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Criar tarefa")
	public ResponseEntity<Tarefas> criarTarefa(@RequestBody Tarefas tarefa) {
		return ResponseEntity.status(HttpStatus.OK).body(tarefasRepository.save(tarefa));
	}

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Tarefa atualizada com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado para visualizar este recurso"),
			@ApiResponse(code = 403, message = "O recurso que você está tentando acessar está inacessível"),
			@ApiResponse(code = 404, message = "O recurso que você está tentando acessar não existe"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Atualizar tarefa")
	public ResponseEntity<Tarefas> atualizarTarefa(@ApiParam("Id da tarefa a ser atualizada. Não pode ser vazio") @PathVariable("id") long id, @RequestBody Tarefas tarefaAtualizada) {
		return tarefasRepository.findById(id).map(tarefa -> {
			tarefa.setNome(tarefaAtualizada.getNome());
			tarefa.setDescricao(tarefaAtualizada.getDescricao());
			tarefa.setPrazo(tarefaAtualizada.getPrazo());
			tarefa.setPrioridade(tarefaAtualizada.getPrioridade());
			Tarefas updated = tarefasRepository.save(tarefa);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());

	}

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Tarefa apagada com sucesso"),
			@ApiResponse(code = 401, message = "Você não está autorizado para visualizar este recurso"),
			@ApiResponse(code = 403, message = "O recurso que você está tentando acessar está inacessível"),
			@ApiResponse(code = 404, message = "O recurso que você está tentando acessar não existe"),
			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Apagar tarefa")
	public ResponseEntity<?> apagarTarefa(@ApiParam("Id da tarefa a ser apagada. Não pode ser vazio") @PathVariable("id") long id) {
		return tarefasRepository.findById(id).map(record -> {
			tarefasRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
