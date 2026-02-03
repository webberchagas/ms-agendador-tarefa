package br.com.webberchagas.msAgendadorTarefas.business;

import br.com.webberchagas.msAgendadorTarefas.business.dto.TarefaDTO;
import br.com.webberchagas.msAgendadorTarefas.business.mapper.TarefaMapper;
import br.com.webberchagas.msAgendadorTarefas.infrastructure.entity.StatusNotificacao;
import br.com.webberchagas.msAgendadorTarefas.infrastructure.entity.TarefaEntity;
import br.com.webberchagas.msAgendadorTarefas.infrastructure.repository.TarefaRepository;
import br.com.webberchagas.msAgendadorTarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final JwtUtil jwtUtil;

    public TarefaDTO salvarTarefa(String token, TarefaDTO dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setEmailUsuario(email);
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacao(StatusNotificacao.PENDENTE);
        TarefaEntity entity = tarefaMapper.toEntity(dto);

        return tarefaMapper.toDto(tarefaRepository.save(entity));
    }

    public List<TarefaDTO> buscaTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        List<TarefaEntity> tarefasEntity = tarefaRepository.findByDataEventoBetweenAndStatusNotificacao(dataInicial, dataFinal, StatusNotificacao.PENDENTE);
        List<TarefaDTO> listDtos = tarefaMapper.toDTOList(tarefasEntity);
        return listDtos;
    }

    public List<TarefaDTO> buscarTarefasPorEmailUsuario(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        List<TarefaEntity> tarefasEntity = tarefaRepository.findByEmailUsuario(email);
        return tarefaMapper.toDTOList(tarefasEntity);
    }
}
