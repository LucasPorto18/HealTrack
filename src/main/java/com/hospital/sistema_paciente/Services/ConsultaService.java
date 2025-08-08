package com.hospital.sistema_paciente.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hospital.sistema_paciente.Model.Consulta;
import com.hospital.sistema_paciente.Model.ConsultaSetor;
import com.hospital.sistema_paciente.Model.Paciente;
import com.hospital.sistema_paciente.Model.Setor;
import com.hospital.sistema_paciente.Repository.PacienteRepository;
import com.hospital.sistema_paciente.Repository.ConsultaRepository;
import com.hospital.sistema_paciente.Repository.SetorRepository;
import com.hospital.sistema_paciente.Repository.ConsultaSetorRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    private final PacienteRepository pacienteRepo;
    private final ConsultaRepository consultaRepo;
    private final SetorRepository setorRepo;
    private final ConsultaSetorRepository consultaSetorRepo;

    @Autowired
    public ConsultaService(PacienteRepository pacienteRepo, ConsultaRepository consultaRepo, SetorRepository setorRepo, ConsultaSetorRepository consultaSetorRepo) {
        this.pacienteRepo = pacienteRepo;
        this.consultaRepo = consultaRepo;
        this.setorRepo = setorRepo;
        this.consultaSetorRepo = consultaSetorRepo;
    }
    public Long iniciarConsulta() {
        Paciente paciente = new Paciente();
        paciente = pacienteRepo.save(paciente);
    
        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setDataHoraInicio(LocalDateTime.now());
        consulta.setDataHoraFim(null); // será preenchido depois
        consulta = consultaRepo.save(consulta);
    
        return paciente.getId(); // ID para exibir no modal
    }

    public void finalizarConsulta(Long pacienteId, List<String> nomesSetores) {
        Consulta consulta = consultaRepo.findByPaciente_Id(pacienteId)
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Consulta não encontrada para o paciente ID: " + pacienteId));
    
        consulta.setDataHoraFim(LocalDateTime.now());
        consultaRepo.save(consulta);
    
        for (String nomeSetor : nomesSetores) {
            Setor setor = setorRepo.findByNome(nomeSetor)
                .orElseThrow(() -> new RuntimeException("Setor não encontrado: " + nomeSetor));
    
            ConsultaSetor consultaSetor = new ConsultaSetor();
            consultaSetor.setConsulta(consulta);
            consultaSetor.setSetor(setor);
            consultaSetorRepo.save(consultaSetor);
        }
    }
    

}