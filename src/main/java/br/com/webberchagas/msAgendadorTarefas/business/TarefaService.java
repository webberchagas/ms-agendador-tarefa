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

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final JwtUtil jwtUtil;

    public TarefaDTO salvarTarefa(String token, TarefaDTO dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setEmailUsuario(email);
        dto.setDataCraicao(LocalDateTime.now());
        dto.setStatusNotificacao(StatusNotificacao.PENDENTE);
        TarefaEntity entity = tarefaMapper.toEntity(dto);

        return tarefaMapper.toDto(tarefaRepository.save(entity));
    }
}
