package com.hospital.sistema_paciente.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.sistema_paciente.Services.RelatorioService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final RelatorioService relatorioService;

    @GetMapping
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/relatorio/pacientes")
    public ResponseEntity<Resource> baixarPacientes() throws IOException {
        ByteArrayInputStream stream = relatorioService.gerarRelatorioPacientes();
        InputStreamResource file = new InputStreamResource(stream);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=pacientes.xlsx")
            .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            .body(file);
    }

    @GetMapping("/relatorio/setores")
    public ResponseEntity<Resource> baixarConsultaSetores() throws IOException {
        ByteArrayInputStream stream = relatorioService.gerarRelatorioConsultas();
        InputStreamResource file = new InputStreamResource(stream);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=consulta_setores.xlsx")
            .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            .body(file);
    }
}

