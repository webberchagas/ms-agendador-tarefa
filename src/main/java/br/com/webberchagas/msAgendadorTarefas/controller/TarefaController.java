package br.com.webberchagas.msAgendadorTarefas.controller;

import br.com.webberchagas.msAgendadorTarefas.business.TarefaService;
import br.com.webberchagas.msAgendadorTarefas.business.dto.TarefaDTO;
import br.com.webberchagas.msAgendadorTarefas.infrastructure.entity.StatusNotificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravarTarefa(@RequestBody TarefaDTO request,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvarTarefa(token, request));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscarTarefaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
    ) {
        return ResponseEntity.ok(tarefaService.buscaTarefasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscarTarefaPorPeriodo(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscarTarefasPorEmailUsuario(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id) {
        tarefaService.deletaTarefaPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<TarefaDTO> alteraStatusNotificacao(@RequestParam("status") StatusNotificacao status,
                                                             @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.alteraStatus(status,id));
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> atualizarTarefa(@RequestBody TarefaDTO request,
                                                     @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.updateTarefas(request,id));
    }
}
