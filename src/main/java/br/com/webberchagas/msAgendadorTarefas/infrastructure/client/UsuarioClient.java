package br.com.webberchagas.msAgendadorTarefas.infrastructure.client;

import br.com.webberchagas.msAgendadorTarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/api/v1/usuario")
    UsuarioDTO buscarUsuarioPorEmail(@RequestParam String email,
                                     @RequestHeader("Authorization") String token);
}
