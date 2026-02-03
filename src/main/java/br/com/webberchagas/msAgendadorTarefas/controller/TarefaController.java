package br.com.webberchagas.msAgendadorTarefas.controller;

import br.com.webberchagas.msAgendadorTarefas.business.TarefaService;
import br.com.webberchagas.msAgendadorTarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravarTarefa(@RequestBody TarefaDTO request,
                                                  @RequestHeader("Authorization") String token){
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvarTarefa(token, request));
    }
}
