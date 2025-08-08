package com.hospital.sistema_paciente.DTO;

import lombok.Data;
import java.util.List;

@Data
public class ConsultaRequestDTO {
    private Long pacienteId;
    private List<String> especialidades;
}
