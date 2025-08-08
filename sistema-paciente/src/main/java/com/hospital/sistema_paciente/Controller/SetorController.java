package com.hospital.sistema_paciente.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hospital.sistema_paciente.Services.RegistroSetorService;


import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/setor")
@RequiredArgsConstructor
public class SetorController {
    private final RegistroSetorService registroSetorService;

   

    @PostMapping("/{nomeSetor}/saida")
public String registrarSaidaSetor(@PathVariable String nomeSetor,
                                  @RequestParam Long pacienteId,
                                  RedirectAttributes redirectAttributes) {
    registroSetorService.registrarSaida(pacienteId, nomeSetor);
    redirectAttributes.addFlashAttribute("mensagem", "Saída registrada com sucesso!");
    return "redirect:/setor/" + nomeSetor;
}


    @GetMapping("/{nomeSetor}")
    public String exibirPaginaSetor(@PathVariable String nomeSetor, Model model) {
        model.addAttribute("setor", nomeSetor);
        return nomeSetor;
    }
    
}
