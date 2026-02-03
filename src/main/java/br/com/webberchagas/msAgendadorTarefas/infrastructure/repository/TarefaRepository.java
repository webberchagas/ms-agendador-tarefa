package br.com.webberchagas.msAgendadorTarefas.infrastructure.repository;

import br.com.webberchagas.msAgendadorTarefas.infrastructure.entity.StatusNotificacao;
import br.com.webberchagas.msAgendadorTarefas.infrastructure.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {

    List<TarefaEntity> findByDataEventoBetweenAndStatusNotificacao(LocalDateTime dataInicial, LocalDateTime dataFinal, StatusNotificacao statusNotificacao);

    List<TarefaEntity> findByEmailUsuario(String emailUsuario);
}
