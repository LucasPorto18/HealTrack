package com.hospital.sistema_paciente.Services;

import java.time.LocalDateTime;


import org.springframework.stereotype.Service;

import com.hospital.sistema_paciente.Model.Consulta;
import com.hospital.sistema_paciente.Model.ConsultaSetor;
import com.hospital.sistema_paciente.Model.RegistroPassagem;
import com.hospital.sistema_paciente.Repository.ConsultaRepository;
import com.hospital.sistema_paciente.Repository.ConsultaSetorRepository;
import com.hospital.sistema_paciente.Repository.RegistroPassagemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistroSetorService {
    private final ConsultaSetorRepository consultaSetorRepository;
    private final ConsultaRepository consultaRepository;
    private final RegistroPassagemRepository registroPassagemRepository;

    public void registrarSaida(Long pacienteId, String nomeSetor) {
        Consulta consulta = consultaRepository.findByPaciente_Id(pacienteId)
            .stream().findFirst()
            .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    
        ConsultaSetor consultaSetor = consultaSetorRepository.findByConsultaAndSetorNome(consulta, nomeSetor)
            .orElseThrow(() -> new RuntimeException("Setor não atribuído à consulta"));

            RegistroPassagem registro = new RegistroPassagem();
            registro.setPaciente(consulta.getPaciente()); 
            registro.setSetor(consultaSetor.getSetor());  
            registro.setDataHora(LocalDateTime.now());
    
       
        consultaSetorRepository.save(consultaSetor);
        registroPassagemRepository.save(registro);
    }
    
    
    
}
