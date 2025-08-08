package com.hospital.sistema_paciente.Services;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.hospital.sistema_paciente.Model.Paciente;
import com.hospital.sistema_paciente.Model.Consulta;
import com.hospital.sistema_paciente.Repository.PacienteRepository;
import com.hospital.sistema_paciente.Repository.ConsultaRepository;



@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final PacienteRepository pacienteRepository;
    private final ConsultaRepository consultaRepository;

    public ByteArrayInputStream gerarRelatorioPacientes() throws IOException {
        List<Paciente> pacientes = pacienteRepository.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Pacientes");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");

        int i = 1;
        for (Paciente p : pacientes) {
            Row row = sheet.createRow(i++);
            row.createCell(0).setCellValue(p.getId());
           
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream gerarRelatorioConsultas() throws IOException {
        List<Consulta> consultas = consultaRepository.findAll();  // Usa a entidade Consulta
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Consultas");
    
        // Cabeçalho
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Consulta ID");
        header.createCell(1).setCellValue("Paciente ID");
        header.createCell(2).setCellValue("Data/Hora Início");
        header.createCell(3).setCellValue("Data/Hora Fim");
    
        // Conteúdo
        int i = 1;
        for (Consulta consulta : consultas) {
            Row row = sheet.createRow(i++);
            row.createCell(0).setCellValue(consulta.getId());
            row.createCell(1).setCellValue(consulta.getPaciente() != null ? consulta.getPaciente().getId() : null);
            row.createCell(2).setCellValue(consulta.getDataHoraInicio() != null ? consulta.getDataHoraInicio().toString() : "");
            row.createCell(3).setCellValue(consulta.getDataHoraFim() != null ? consulta.getDataHoraFim().toString() : "");
        }
    
        // Exporta para ByteArrayInputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
    
}
