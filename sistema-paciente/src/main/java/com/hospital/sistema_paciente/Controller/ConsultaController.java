package com.hospital.sistema_paciente.Controller;
import com.hospital.sistema_paciente.DTO.ConsultaRequestDTO;
import com.hospital.sistema_paciente.Services.ConsultaService;


import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/consulta")
@RequiredArgsConstructor
public class ConsultaController {

  
    private final ConsultaService consultaService;
    

    @GetMapping
    public String mostrarPaginaConsulta(Model model) {
        model.addAttribute("consulta", new ConsultaRequestDTO());
        return "consulta";
    }

    @PostMapping("/iniciar")
    public String iniciarConsulta(RedirectAttributes redirectAttributes) {
        Long pacienteId = consultaService.iniciarConsulta();
        redirectAttributes.addFlashAttribute("pacienteId", pacienteId);
        redirectAttributes.addFlashAttribute("mostrarModalId", true);
        return "redirect:/consulta";
    }

    @PostMapping("/finalizar")
    public String finalizarConsulta(@RequestParam Long pacienteId, @RequestParam List<String> setoresObrigatorios, RedirectAttributes redirectAttributes) {
        consultaService.finalizarConsulta(pacienteId, setoresObrigatorios);
        redirectAttributes.addFlashAttribute("mensagem", "Consulta finalizada com sucesso.");
        return "redirect:/consulta";
    }



   
}
