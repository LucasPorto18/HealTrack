package com.hospital.sistema_paciente.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hospital.sistema_paciente.Services.SaidaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/saida")
@RequiredArgsConstructor
public class SaidaController {
    private final SaidaService saidaService;

    @GetMapping
    public String exibirPaginaSaida() {
        return "saida"; // mostra página com input para ID
    }

    @PostMapping
    public String processarSaida(@RequestParam Long pacienteId, Model model, RedirectAttributes redirectAttributes) {

       try{List<String> setoresPendentes = saidaService.verificarSetoresPendentes(pacienteId);
        model.addAttribute("pacienteId", pacienteId);

        if (setoresPendentes.isEmpty()) {
            model.addAttribute("saidaLiberada", true);
        } else {
            model.addAttribute("saidaLiberada", false);
            model.addAttribute("setoresFaltando", setoresPendentes);
        }

        return "saida";

    } catch (IllegalArgumentException e) {
        redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
        return "redirect:/saida";
    }
    }
}
