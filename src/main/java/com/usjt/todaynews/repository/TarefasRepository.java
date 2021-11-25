package com.usjt.todaynews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usjt.todaynews.model.Tarefas;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas, Long>{

}
