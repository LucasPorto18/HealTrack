package com.hospital.sistema_paciente.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hospital.sistema_paciente.Model.Consulta;
import com.hospital.sistema_paciente.Repository.ConsultaRepository;
import com.hospital.sistema_paciente.Repository.RegistroPassagemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaidaService {
    private final ConsultaRepository consultaRepository;
    private final RegistroPassagemRepository registroSetorRepository;

    public List<String> verificarSetoresPendentes(Long pacienteId) {
        // Consulta o paciente e suas especialidades registradas
        var consulta = consultaRepository.findByPaciente_Id(pacienteId);
        if (consulta.isEmpty()) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }
        
        Consulta consultas = consulta.get(0); // Pega a primeira consulta
        
        List<String> setoresObrigatorios = consultas.getSetores()
            .stream()
            .map(cs -> cs.getSetor().getNome())
            .toList();
        // já é lista de strings

        // Recupera setores que o paciente já passou
        List<String> setoresVisitados = registroSetorRepository.findByPacienteId(pacienteId)
        .stream()
        .map(registro -> registro.getSetor().getNome())
        .toList();
    

        // Retorna a lista de setores obrigatórios que ainda não foram visitados
        return setoresObrigatorios.stream()
            .filter(setor -> !setoresVisitados.contains(setor))
            .toList();
    }
}
