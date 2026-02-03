package br.com.webberchagas.msAgendadorTarefas.business.mapper;

import br.com.webberchagas.msAgendadorTarefas.business.dto.TarefaDTO;
import br.com.webberchagas.msAgendadorTarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    TarefaEntity toEntity(TarefaDTO tarefaDTO);

    TarefaDTO toDto(TarefaEntity tarefaEntity);
}
