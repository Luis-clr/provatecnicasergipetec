package Sergipetec.gov.ferias.sistema_ferias.service;

import Sergipetec.gov.ferias.sistema_ferias.model.Servidor;
import Sergipetec.gov.ferias.sistema_ferias.model.Usuario;
import Sergipetec.gov.ferias.sistema_ferias.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;

    public Servidor autenticar(String login, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(login);
        if (usuarioOpt.isEmpty()) {
            return null;
        }
        Usuario usuario = usuarioOpt.get();

        if (usuario.getSenha().equals(senha)) {
            return usuario.getServidor();
        }

        return null;
    }
}