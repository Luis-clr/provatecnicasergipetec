package Sergipetec.gov.ferias.sistema_ferias.controller;

import Sergipetec.gov.ferias.sistema_ferias.dto.FeriasDetalheDTO;
import Sergipetec.gov.ferias.sistema_ferias.dto.FeriasListaDTO;
import Sergipetec.gov.ferias.sistema_ferias.model.Servidor;
import Sergipetec.gov.ferias.sistema_ferias.service.AutenticacaoService;
import Sergipetec.gov.ferias.sistema_ferias.service.FeriasService;
import Sergipetec.gov.ferias.sistema_ferias.service.ServidorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final ServidorService servidorService;
    private final FeriasService feriasService;
    private final AutenticacaoService autenticacaoService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam String login,
                                 @RequestParam String senha,
                                 RedirectAttributes redirectAttributes) {
        try {
            Servidor servidor = autenticacaoService.autenticar(login, senha);
            if (servidor != null) {
                return "redirect:/ferias/" + servidor.getIdServidor();
            } else {
                redirectAttributes.addFlashAttribute("erro", "Login ou senha inválidos");
                return "redirect:/";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao fazer login: " + e.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("/ferias/{servidorId}")
    public String listarFerias(@PathVariable Integer servidorId, Model model) {
        try {
            Servidor servidor = servidorService.buscarPorId(servidorId);
            List<FeriasListaDTO> ferias = servidorService.listarFeriasDoServidor(servidorId);

            model.addAttribute("servidor", servidor);
            model.addAttribute("ferias", ferias);

            return "lista-ferias";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar férias");
            return "redirect:/";
        }
    }

    @GetMapping("/ferias/detalhes/{feriasId}")
    public String detalhesFerias(@PathVariable Integer feriasId,
                                 @RequestParam Integer servidorId,
                                 Model model) {
        try {
            Servidor servidor = servidorService.buscarPorId(servidorId);
            FeriasDetalheDTO detalhes = feriasService.buscarDetalhes(feriasId);

            model.addAttribute("servidor", servidor);
            model.addAttribute("detalhes", detalhes);

            return "detalhes-ferias";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar detalhes");
            return "redirect:/ferias/" + servidorId;
        }
    }

    @GetMapping("/ferias/nova")
    public String formNovaSolicitacao(@RequestParam Integer servidorId, Model model) {
        try {
            Servidor servidor = servidorService.buscarPorId(servidorId);
            model.addAttribute("servidor", servidor);
            return "nova-solicitacao";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
}