package br.com.webberchagas.msAgendadorTarefas.infrastructure.repository;

import br.com.webberchagas.msAgendadorTarefas.infrastructure.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {
}
