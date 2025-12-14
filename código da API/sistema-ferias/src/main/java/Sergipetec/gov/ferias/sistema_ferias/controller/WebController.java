package Sergipetec.gov.ferias.sistema_ferias.controller;

import Sergipetec.gov.ferias.sistema_ferias.dto.FeriasDetalheDTO;
import Sergipetec.gov.ferias.sistema_ferias.dto.FeriasListaDTO;
import Sergipetec.gov.ferias.sistema_ferias.model.Servidor;
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

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam Integer matricula,
                                 @RequestParam String senha,
                                 RedirectAttributes redirectAttributes) {
        try {
            // Buscar peal matricula e apenas para teste da aplicacao
            if (matricula.equals(123456) && senha.equals("senha123")) {
                return "redirect:/ferias/1";
            } else if (matricula.equals(789012) && senha.equals("senha123")) {
                return "redirect:/ferias/2";
            } else {
                redirectAttributes.addFlashAttribute("erro", "Matrícula ou senha inválidos");
                return "redirect:/";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao fazer login");
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
            return "redirect:/"; // Se der erro, volta pro login
        }
    }

}