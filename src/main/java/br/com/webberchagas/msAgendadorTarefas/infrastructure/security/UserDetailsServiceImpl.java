package br.com.webberchagas.msAgendadorTarefas.infrastructure.security;


import br.com.webberchagas.msAgendadorTarefas.business.dto.UsuarioDTO;
import br.com.webberchagas.msAgendadorTarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    private final UsuarioClient client;

    public UserDetailsServiceImpl(UsuarioClient client) {
        this.client = client;
    }

    public UserDetails carregaDadosUsuario(String email, String token) {

        UsuarioDTO usuario = client.buscarUsuarioPorEmail(email, token);

        return User
               .withUsername(usuario.getEmail()) // Define o nome de usuário como o e-mail
               .password(usuario.getSenha()) // Define a senha do usuário
               .build(); // Constrói o objeto UserDetails
    }
}
